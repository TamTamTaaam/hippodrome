
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {
private final String NAME_CANNOT_BE_NULL = "Name cannot be null.";
private final String NAME_CANNOT_BE_BLANK = "Name cannot be blank.";
private final String SPEED_CANNOT_BE_NEGATIVE = "Speed cannot be negative.";


    @Test
    public void HorseConstructorNameTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Horse myObject = new Horse(null, 1, 1);
            throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
        });
        assertEquals(NAME_CANNOT_BE_NULL, exception.getMessage());

    }
    @ParameterizedTest
    @ValueSource(strings={"", " ", "\t"})
    public void HorseConstructorNameParam(String argumentName) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Horse myObject = new Horse(argumentName, 1, 1);
            throw new IllegalArgumentException(NAME_CANNOT_BE_BLANK);
        });
        assertEquals(NAME_CANNOT_BE_BLANK, exception.getMessage());

    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -99.99, -0.000001})
    public void HorseConstructorSpeedParam(double argumentSpeed) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Horse myObject = new Horse("Name", argumentSpeed, 1);
            throw new IllegalArgumentException(SPEED_CANNOT_BE_NEGATIVE);
        });
        assertEquals(SPEED_CANNOT_BE_NEGATIVE, exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -99.99, -0.000001})
    public void HorseConstructorDistanceParam(double argumentDistance) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Horse myObject = new Horse("Name", 1, argumentDistance);
            throw new IllegalArgumentException("Distance cannot be negative.");
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void testGetName() {
        String expectedName = "Test Name";
        Horse testHorse = new Horse(expectedName, 1, 1);
        assertEquals(expectedName, testHorse.getName());
    }
    @Test
    void testGetSpeed() {
        double expectedSpeed = 1.0;
        Horse testHorse = new Horse("name", expectedSpeed, 1.0);
        assertEquals(expectedSpeed, testHorse.getSpeed());
    }
    @Test
    void testGetDistance() {
        double expectedDistance = 1.0;
        Horse testHorse = new Horse("name", 1.0, expectedDistance);
        assertEquals(expectedDistance, testHorse.getDistance());
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
