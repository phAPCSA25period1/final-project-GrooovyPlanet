/**
 * Represents a single event in the calendar system.
 * <p>
 * Each event contains a title and a date that describe
 * the scheduled activity.
 * </p>
 */
public class Event {

    /**
     * The title or name of the event.
     */
    String title;

    /**
     * The date of the event.
     * <p>
     * Expected format: {@code YYYY-MM-DD}.
     * </p>
     */
    String date;

    /**
     * Constructs a new {@code Event} object.
     *
     * @param title the title or name of the event
     * @param date  the date of the event
     */
    Event(String title, String date) {

        this.title = title;
        this.date = date;
    }

    /**
     * Returns the date of the event.
     *
     * @return the event date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the title of the event.
     *
     * @return the event title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns a string representation of the event.
     * <p>
     * The format returned is:
     * {@code "title on date"}.
     * </p>
     *
     * @return a formatted string describing the event
     */
    @Override
    public String toString() {
        return title + " on " + date;
    }
}
