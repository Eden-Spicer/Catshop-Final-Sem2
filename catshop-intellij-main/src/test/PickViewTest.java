import clients.warehousePick.PickView;
import org.junit.jupiter.api.Test;
import static junit.framework.Assert.assertTrue;


// Don't run this test, it is very crude but the only way I could think to implement some testing. Really just take my word it works!!!
public class PickViewTest {
    @Test
    public void PickViewTimerTest1(){

        PickView testing = new PickView();

        while (testing.counterMins < 1){System.out.println(testing.counterSecs);}
        assertTrue(testing.counterMins == 1);
        assertTrue(testing.counterSecs == 0);
    }
}
