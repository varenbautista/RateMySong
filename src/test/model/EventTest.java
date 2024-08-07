package model;

// import ca.ubc.cpsc210.alarm.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e1;
    private Event e2;
    private Event e3;
    private Event e4;
    private Event e5;
    private Event e6;
    private Date d1;
    
    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.
    
    @BeforeEach
    public void runBefore() {
        e1 = new Event("Lyrics rating for Vroom Vroom has been updated to 5");   // (1)
        e2 = new Event("Production rating for Vroom Vroom has been updated to 5");
        e3 = new Event("Vocals rating for Vroom Vroom has been updated to 5");
        e4 = new Event("Vroom Vroom favourite status updated to true");

        e5 = new Event("Event 5");
        e6 = new Event("Event 5");
        d1 = Calendar.getInstance().getTime();   // (2)
    }
    
    @Test
    public void testEventLyricsUpdate() {
        assertEquals("Lyrics rating for Vroom Vroom has been updated to 5", e1.getDescription());
        assertEquals(d1, e1.getDate());
    }

    @Test
    public void testEventProdUpdate() {
        assertEquals("Production rating for Vroom Vroom has been updated to 5", e2.getDescription());
        assertEquals(d1, e2.getDate());
    }

    @Test
    public void testEventVocalsUpdate() {
        assertEquals("Vocals rating for Vroom Vroom has been updated to 5", e3.getDescription());
        assertEquals(d1, e3.getDate());
    }

    @Test
    public void testEventFavouriteStatusUpdate() {
        assertEquals("Vroom Vroom favourite status updated to true", e4.getDescription());
        assertEquals(d1, e4.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d1.toString() + "\n" + "Lyrics rating for Vroom Vroom has been updated to 5", e1.toString());
    }

    @Test
    public void testEqualsSameEvent() {
        assertTrue(e1.equals(e1));
    }
    
    @Test
    public void testEqualsSameDescription() {
        assertTrue(e5.equals(e6));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(e1.equals("asdjklflakdjf"));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(e1.equals(null));
    }
}
