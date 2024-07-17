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
           new Hippodrome(null);
        });
        assertEquals(HORSES_CANNOT_BE_NULL, exception.getMessage());
    }
    @Test
    public void hippodromeConstructorListTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            new Hippodrome(new ArrayList<>());
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
        assertIterableEquals(horsesListTest, hippodromeTest.getHorses());
    }
    @Test
    public void moveTest() throws Exception {
        List<Horse> mockHorses = new ArrayList<>();
        for(int i=0; i<50; i++) {
            mockHorses.add(mock(Horse.class));
        }
        Hippodrome hippodromeTest = new Hippodrome(mockHorses);

        hippodromeTest.move();

        for (Horse horse : hippodromeTest.getHorses()) {
            verify(horse).move();
        }

    }

    // Проверить, что метод вызывает метод move у всех лошадей.
    // При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify
    //   public void move() {
    //       horses.forEach(Horse::move);
    //   }
//     <dependency>
//            <groupId>net.bytebuddy</groupId>
//            <artifactId>byte-buddy</artifactId>
//            <version>1.14.17</version>
//        </dependency>
}
