import java.util.Scanner;

/**
 * Main driver class for the Calendar application.
 * <p>
 * This program allows users to:
 * </p>
 * <ul>
 *     <li>Add events</li>
 *     <li>Remove events</li>
 *     <li>View all events</li>
 *     <li>Search events by date or month/year</li>
 *     <li>Update existing events</li>
 *     <li>Run an interactive tutorial</li>
 * </ul>
 */
public class Main {

    /**
     * Main method that starts the calendar program.
     * <p>
     * Displays a menu-driven interface that continuously runs
     * until the user chooses to exit.
     * </p>
     *
     * @param args command-line arguments (not used)
     * @throws Exception if an unexpected runtime error occurs
     */
    public static void main(String[] args) throws Exception {

        /**
         * Calendar object used to store and manage events.
         */
        Calendar calendar = new Calendar();

        /**
         * Scanner object used for reading user input.
         */
        Scanner scanner = new Scanner(System.in);

        /**
         * Stores the user's menu selection.
         * Initialized to -1 so the loop starts immediately.
         */
        int choice = -1;

        /**
         * Main application loop.
         * Continues running until the user selects option 6 (Exit).
         */
        while (choice != 6) {

            // Display menu options
            System.out.println("\n--- Calendar Menu ---");
            System.out.println("1. Add event");
            System.out.println("2. Remove event by title");
            System.out.println("3. View all events");
            System.out.println("4. Search by date");
            System.out.println("5. Update event");
            System.out.println("6. Exit");
            System.out.println("7. Run tutorial");
            System.out.print("Choose an option: ");

            /**
             * Read user's menu choice.
             */
            choice = scanner.nextInt();

            /**
             * Consumes the leftover newline character after nextInt().
             * Prevents input skipping issues when using nextLine().
             */
            scanner.nextLine();

            /**
             * Executes functionality based on the user's menu selection.
             */
            switch (choice) {

                /**
                 * CASE 1:
                 * Adds a new event to the calendar.
                 */
                case 1:

                    // Prompt user for event title
                    System.out.print("Enter event title: ");
                    String title = scanner.nextLine();

                    // Prompt user for event date
                    System.out.print("Enter event date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();

                    // Create and add new event
                    calendar.addEvent(new Event(title, date));

                    // Confirmation message
                    System.out.println("Event added!");
                    break;

                /**
                 * CASE 2:
                 * Removes events matching a given title.
                 */
                case 2:

                    // Ask user which title to remove
                    System.out.print("Enter title of event to remove: ");
                    String removeTitle = scanner.nextLine();

                    // Remove matching events
                    calendar.removeByTitle(removeTitle);
                    break;

                /**
                 * CASE 3:
                 * Displays all events currently stored.
                 */
                case 3:

                    System.out.println("--- All Events ---");

                    // Display all calendar events
                    calendar.displayEvents();
                    break;

                /**
                 * CASE 4:
                 * Allows the user to search events.
                 * User may search by:
                 * 1. Exact date
                 * 2. Month and year
                 */
                case 4:

                    // Search submenu
                    System.out.println("Search by:");
                    System.out.println("1. Exact date");
                    System.out.println("2. Month and year");
                    System.out.print("Choose option: ");

                    // Read search option
                    int searchChoice = scanner.nextInt();

                    // Consume newline
                    scanner.nextLine();

                    /**
                     * Search by exact date.
                     */
                    if (searchChoice == 1) {

                        System.out.print("Enter date to search (YYYY-MM-DD): ");
                        String searchDate = scanner.nextLine();

                        System.out.println("--- Events on " + searchDate + " ---");

                        // Display events matching the date
                        calendar.getEventsByDate(searchDate);

                    /**
                     * Search by month and year.
                     */
                    } else if (searchChoice == 2) {

                        // Prompt for year
                        System.out.print("Enter year (YYYY): ");
                        int year = scanner.nextInt();

                        // Prompt for month
                        System.out.print("Enter month (1-12): ");
                        int month = scanner.nextInt();

                        // Consume newline
                        scanner.nextLine();

                        System.out.println(
                            "--- Events in " +
                            year + "-" +
                            String.format("%02d", month) +
                            " ---"
                        );

                        // Display matching events
                        calendar.getEventsByMonthYear(month, year);

                    /**
                     * Handles invalid search selections.
                     */
                    } else {

                        System.out.println("Invalid search option.");
                    }

                    break;

                /**
                 * CASE 5:
                 * Updates an existing event.
                 */
                case 5:

                    // Ask for existing event title
                    System.out.print("Enter title of event to update: ");
                    String oldTitle = scanner.nextLine();

                    // Ask for existing event date
                    System.out.print("Enter date of event to update (YYYY-MM-DD): ");
                    String oldDate = scanner.nextLine();

                    // Ask for updated title
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();

                    // Ask for updated date
                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();

                    /**
                     * Attempt to update the event.
                     * Returns true if successful.
                     */
                    boolean updated = calendar.updateEvent(
                        oldTitle,
                        oldDate,
                        new Event(newTitle, newDate)
                    );

                    // Display result message
                    if (updated) {

                        System.out.println("Event updated successfully.");

                    } else {

                        System.out.println("Event not found.");
                    }

                    break;

                /**
                 * CASE 6:
                 * Exits the program.
                 */
                case 6:

                    System.out.println("Goodbye!");
                    break;

                /**
                 * CASE 7:
                 * Launches the interactive tutorial.
                 */
                case 7:

                    runTutorial(calendar, scanner);
                    break;

                /**
                 * DEFAULT CASE:
                 * Handles invalid menu selections.
                 */
                default:

                    System.out.println("Invalid option, please try again.");
            }
        }

        /**
         * Close scanner to prevent resource leaks.
         */
        scanner.close();
    }

    /**
     * Runs an interactive tutorial for new users.
     * <p>
     * The tutorial walks the user through:
     * </p>
     * <ol>
     *     <li>Adding an event</li>
     *     <li>Viewing events</li>
     *     <li>Searching events</li>
     *     <li>Updating an event</li>
     *     <li>Removing an event</li>
     * </ol>
     *
     * @param calendar the calendar object used during the tutorial
     * @param scanner  scanner object used for user input
     */
    public static void runTutorial(Calendar calendar, Scanner scanner) {

        System.out.println("\n=== Welcome to the Calendar Tutorial ===");
        System.out.println("This will guide you step-by-step.\n");

        /**
         * STEP 1:
         * Add an event.
         */
        System.out.println("Step 1: Let's add your first event.");

        System.out.print("Enter a title: ");
        String title = scanner.nextLine();

        System.out.print("Enter a date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        // Add tutorial event
        calendar.addEvent(new Event(title, date));

        System.out.println("✅ Event added!\n");

        /**
         * STEP 2:
         * Display all events.
         */
        System.out.println("Step 2: Viewing all events...");

        calendar.displayEvents();
        System.out.println();

        /**
         * STEP 3:
         * Search for events by date.
         */
        System.out.println("Step 3: Search for your event by date.");

        System.out.print("Enter the same date: ");
        String searchDate = scanner.nextLine();

        // Search events
        calendar.getEventsByDate(searchDate);

        System.out.println();

        /**
         * STEP 4:
         * Update the event.
         */
        System.out.println("Step 4: Let's update your event.");

        System.out.print("Enter a new title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter a new date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();

        // Attempt update
        boolean updated = calendar.updateEvent(
            title,
            date,
            new Event(newTitle, newDate)
        );

        // Display update result
        if (updated) {

            System.out.println("✅ Event updated!\n");

        } else {

            System.out.println("❌ Update failed.\n");
        }

        /**
         * STEP 5:
         * Remove the event.
         */
        System.out.println("Step 5: Now remove the event.");

        System.out.print("Enter the title to remove: ");
        String removeTitle = scanner.nextLine();

        // Remove event
        calendar.removeByTitle(removeTitle);

        System.out.println();

        /**
         * FINAL STEP:
         * Tutorial completion message.
         */
        System.out.println("🎉 Tutorial complete!");
        System.out.println("You now know how to:");
        System.out.println("- Add events");
        System.out.println("- View events");
        System.out.println("- Search events");
        System.out.println("- Update events");
        System.out.println("- Remove events\n");
    }
}
