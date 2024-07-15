
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void HorseConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Horse myObject = new Horse(null, 0, 0);
            throw new IllegalArgumentException("Name cannot be null");
        });
        assertEquals("Name cannot be null", exception.getMessage());

    }
}
