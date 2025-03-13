// filepath: /realtime_notifications/realtime_notifications/src/test/java/AppTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testMain() {
        // Assuming App has a method called getGreeting that returns a greeting message
        App app = new App();
        String expected = "Hello, World!";
        String actual = app.getGreeting();
        assertEquals(expected, actual);
    }
}