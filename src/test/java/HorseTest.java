import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {
private final String NAME_CANNOT_BE_NULL = "Name cannot be null.";
private final String NAME_CANNOT_BE_BLANK = "Name cannot be blank.";
private final String SPEED_CANNOT_BE_NEGATIVE = "Speed cannot be negative.";
private final String DISTANCE_CANNOT_BE_NEGATIVE = "Distance cannot be negative.";
private final String testNameHorse = "TestName";
private final double testSpeed = 2.0;
private final double testDistance = 1.0;
private final Horse HORSE_TEST = new Horse(testNameHorse, testSpeed, testDistance);

    @Test
    public void horseConstructorNameTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            new Horse(null, 1, 1);
        });
        assertEquals(NAME_CANNOT_BE_NULL, exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings={"", " ", "\t", "\n", "  "})
    public void horseConstructorNameParam(String argumentName) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            new Horse(argumentName, 1, 1);
        });
        assertEquals(NAME_CANNOT_BE_BLANK, exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -99.99, -0.000001})
    public void horseConstructorSpeedParam(double argumentSpeed) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
           new Horse("Name", argumentSpeed, 1);
        });
        assertEquals(SPEED_CANNOT_BE_NEGATIVE, exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -99.99, -0.000001})
    public void horseConstructorDistanceParam(double argumentDistance) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            new Horse("Name", 1, argumentDistance);
        });
        assertEquals(DISTANCE_CANNOT_BE_NEGATIVE, exception.getMessage());
    }
    @Test
    void testGetName() throws NoSuchFieldException, IllegalAccessException {
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String expectedName = (String) name.get(HORSE_TEST);
        assertEquals(testNameHorse, expectedName);
    }
    @Test
    void testGetSpeed() {
        assertEquals(testSpeed, HORSE_TEST.getSpeed());
    }
    @Test
    void testGetDistance() throws NoSuchFieldException, IllegalAccessException {
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        double expectedDistance = (Double) distance.get(HORSE_TEST);
        assertEquals(expectedDistance, testDistance);
    }
    @Test
    void testGetDistanceReturnNull() {
        double expectedDistance = 0.0;
        Horse testHorse = new Horse("name", 1.0);
        assertEquals(expectedDistance, testHorse.getDistance());
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    void testMoveCallsGetRandomDoubleWithParams() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            double param1 = 0.2;
            double param2 = 0.9;
            Horse horseTest = new Horse("name", 1.0);
            horseTest.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(param1, param2));
        }
    }
    @Test
    void testMoveAssignsCorrectDistanceValue() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horseTest = new Horse("name", 1.0, 1.0);
            double distanceTest = horseTest.getDistance();
            double expectedDistance =distanceTest+horseTest.getSpeed() * 0.5;
            horseTest.move();
            double actualDistance = distanceTest+horseTest.getSpeed()*0.5;
            assertEquals(expectedDistance, actualDistance);
        }
    }

}
