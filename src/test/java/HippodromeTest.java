import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
   @Test
    public void HippodromeConstructorTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Hippodrome myObject = new Hippodrome(null);
            throw new IllegalArgumentException("Horses cannot be null.");
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    public void HippodromeConstructorListTest() {
        List<Horse> horsesListTest = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> {
            Hippodrome myObject = new Hippodrome(horsesListTest);
            throw new IllegalArgumentException("Horses cannot be empty.");
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
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
        verify(hippodromeTest, times(50)).move();

        for (Horse horse : mockHorses) {
            verify(horse).move();
        }

    }

   // Проверить, что метод вызывает метод move у всех лошадей.
    // При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify
//   public void move() {
//       horses.forEach(Horse::move);
//   }
}
