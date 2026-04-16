import java.util.ArrayList;
public class Calendar {
    private ArrayList<Event> events;

    public Calendar() {
        events = new ArrayList<>();
    }

    // Add an event
    public void addEvent(Event event) {
        events.add(event);
    }

    // Remove an event
    public void removeByTitle(String title) {
    events.removeIf(e -> e.getTitle().equalsIgnoreCase(title));
    System.out.println("Event removed (if it existed).");
    }

    // View all events
    public void displayEvents() {
        for (Event e : events) {
            System.out.println(e);
        }
    }


    // Optional: find events by date
    public void getEventsByDate(String date) {
        for (Event e : events) {
            if (e.getDate().equals(date)) {
                System.out.println(e);
            }
        }
    }
}
