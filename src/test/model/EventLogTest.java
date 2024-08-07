package model;

// import ca.ubc.cpsc210.alarm.model.Event;
// import ca.ubc.cpsc210.alarm.model.EventLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the EventLog class
 */
public class EventLogTest {
    private Event e1;
    private Event e2;
    private Event e3;
    private Event e4;
    
    @BeforeEach
    public void loadEvents() {
        e1 = new Event("Vroom Vroom added to music library");
        e2 = new Event("Lyrics rating for Vroom Vroom has been updated to 5");
        e3 = new Event("Happiness is a Butterfly added to music library");
        e4 = new Event("Music library has been sorted");
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);
        el.logEvent(e3);
        el.logEvent(e4);
    }
    
    @Test
    public void testLogEvent() {	
        List<Event> l = new ArrayList<Event>();
        
        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        
        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
        assertTrue(l.contains(e4));
    }

    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> itr = el.iterator();
        assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
