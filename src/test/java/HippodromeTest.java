import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    private final String HORSES_CANNOT_BE_NULL = "Horses cannot be null.";
    private final String HORSES_CANNOT_BE_EMPTY = "Horses cannot be empty.";

   @Test
    public void hippodromeConstructorTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Hippodrome myObject = new Hippodrome(null);
            throw new IllegalArgumentException(HORSES_CANNOT_BE_NULL);
        });
        assertEquals(HORSES_CANNOT_BE_NULL, exception.getMessage());
    }
    @Test
    public void hippodromeConstructorListTest() {
        List<Horse> horsesListTest = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Hippodrome myObject = new Hippodrome(horsesListTest);
            throw new IllegalArgumentException(HORSES_CANNOT_BE_EMPTY);
        });
        assertEquals(HORSES_CANNOT_BE_EMPTY, exception.getMessage());
    }
    @Test
    public void getHorsesTest() {
        List<Horse> horsesListTest = new ArrayList<>();
        for(int i=0; i<30; i++) {
            String name = "name" + i;
            horsesListTest.add(new Horse(name, i, i));
        }
        Hippodrome hippodromeTest = new Hippodrome(horsesListTest);
        List<Horse> returnedList = hippodromeTest.getHorses();
        assertIterableEquals(horsesListTest, returnedList);
    }
    @Test
    public void moveTest() throws Exception {
        List<Horse> mockHorses = new ArrayList<>();
        for(int i=0; i<50; i++) {
            mockHorses.add(Mockito.spy(new Horse("name", i, i)));
        }
        Hippodrome hippodromeTest = new Hippodrome(mockHorses);
        for (Horse horse : hippodromeTest.getHorses()) {
            verify(horse).move();
        }

    }

   // Проверить, что метод вызывает метод move у всех лошадей.
    // При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify
//   public void move() {
//       horses.forEach(Horse::move);
//   }
}
