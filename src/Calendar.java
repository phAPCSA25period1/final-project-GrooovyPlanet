import java.util.ArrayList;
public class Calendar {
    private ArrayList<Event> events;

    public Calendar() {
        events = new ArrayList<>();
    }

    // Add an event
    public void addEvent(Event event) {
    for (Event e : events) {
        if (e.getTitle().equalsIgnoreCase(event.getTitle()) &&
            e.getDate().equals(event.getDate())) {

            System.out.println("Duplicate event detected. Not added.");
            return;
        }
    }

    events.add(event);
}

public boolean updateEvent(String title, String date, Event updatedEvent) {
    for (int i = 0; i < events.size(); i++) {
        Event e = events.get(i);
        if (e.getTitle().equalsIgnoreCase(title) &&
            e.getDate().equals(date)) {

            events.set(i, updatedEvent);
            return true;
        }
    }
    return false;
}

    // Remove an event
    public void removeByTitle(String title) {
    events.removeIf(e -> e.getTitle().equalsIgnoreCase(title));
    System.out.println("Event removed (if it existed).");
    }

    // View all events
    public void displayEvents() {
    if (events.isEmpty()) {
        System.out.println("No events found.");
        return;
    }

    for (Event e : events) {
        System.out.println(e);
    }
}


    // Optional: find events by date
    public void getEventsByDate(String date) {
    boolean found = false;

    for (Event e : events) {
        if (e.getDate().equals(date)) {
            System.out.println(e);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No events found on this date.");
    }
}
    public void getEventsByMonthYear(int month, int year) {
        boolean found = false;

        for (Event event : events) {
            String[] parts = event.getDate().split("-");
            int eventYear = Integer.parseInt(parts[0]);
            int eventMonth = Integer.parseInt(parts[1]);

            if (eventYear == year && eventMonth == month) {
                System.out.println(event);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No events found for that month.");
        }
    }


}
