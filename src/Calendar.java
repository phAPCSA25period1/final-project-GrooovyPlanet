import java.util.ArrayList;

/**
 * Represents a simple calendar that stores and manages {@code Event} objects.
 * <p>
 * The calendar supports adding, updating, removing, and displaying events,
 * as well as searching for events by date or by month and year.
 * </p>
 */
public class Calendar {

    /**
     * List that stores all events in the calendar.
     */
    private ArrayList<Event> events;

    /**
     * Constructs an empty {@code Calendar}.
     * <p>
     * Initializes the internal event list.
     * </p>
     */
    public Calendar() {
        events = new ArrayList<>();
    }

    /**
     * Adds a new event to the calendar.
     * <p>
     * Before adding, the method checks for duplicate events by comparing
     * both the event title and date. If a duplicate is found, the event
     * is not added.
     * </p>
     *
     * @param event the {@code Event} object to add
     */
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

    /**
     * Updates an existing event in the calendar.
     * <p>
     * The event is identified using its title and date. If a matching
     * event is found, it is replaced with the provided updated event.
     * </p>
     *
     * @param title        the title of the event to update
     * @param date         the date of the event to update
     * @param updatedEvent the new {@code Event} object that replaces the old one
     * @return {@code true} if the event was successfully updated;
     *         {@code false} if no matching event was found
     */
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

    /**
     * Removes all events that match the given title.
     * <p>
     * Title matching is case-insensitive.
     * </p>
     *
     * @param title the title of the event(s) to remove
     */
    public void removeByTitle(String title) {

        events.removeIf(e -> e.getTitle().equalsIgnoreCase(title));
        System.out.println("Event removed (if it existed).");
    }

    /**
     * Displays all events currently stored in the calendar.
     * <p>
     * If no events exist, a message is displayed instead.
     * </p>
     */
    public void displayEvents() {

        if (events.isEmpty()) {

            System.out.println("No events found.");
            return;
        }

        for (Event e : events) {
            System.out.println(e);
        }
    }

    /**
     * Displays all events that occur on a specific date.
     * <p>
     * The date must match exactly with the event date format.
     * If no matching events are found, a message is displayed.
     * </p>
     *
     * @param date the date used to search for events
     */
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

    /**
     * Displays all events that occur within a specific month and year.
     * <p>
     * This method assumes event dates are stored in the format:
     * {@code YYYY-MM-DD}.
     * </p>
     *
     * @param month the month to search for (1-12)
     * @param year  the year to search for
     */
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
