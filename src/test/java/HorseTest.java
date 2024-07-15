
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void HorseConstructorName() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Horse myObject = new Horse(null, 1, 1);
            throw new IllegalArgumentException("Name cannot be null.");
        });
        assertEquals("Name cannot be null.", exception.getMessage());

    }
    @ParameterizedTest
    @ValueSource(strings={"", " ", "\t"})
    public void HorseConstructorNameParam(String argumentName) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Horse myObject = new Horse(argumentName, 1, 1);
            throw new IllegalArgumentException("Name cannot be blank.");
        });
        assertEquals("Name cannot be blank.", exception.getMessage());

    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -99.99, -0.000001})
    public void HorseConstructorSpeedParam(double argumentSpeed) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Horse myObject = new Horse("Name", argumentSpeed, 1);
            throw new IllegalArgumentException("Speed cannot be negative.");
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
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
}
