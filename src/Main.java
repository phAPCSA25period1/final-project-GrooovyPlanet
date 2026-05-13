import java.util.Scanner;

/**
 * Entry point and user interface controller for the Calendar application.
 *
 * <p>This application provides a console-based menu system that allows users to
 * manage calendar events. Users can perform operations such as:</p>
 *
 * <ul>
 *     <li>Adding new events</li>
 *     <li>Removing events by title</li>
 *     <li>Viewing all stored events</li>
 *     <li>Searching for events by date or month/year</li>
 *     <li>Updating existing events</li>
 *     <li>Running an interactive tutorial</li>
 * </ul>
 *
 * <p>The program continues running until the user selects the exit option.</p>
 *
 * @author YourName
 * @version 1.0
 */
public class Main {

    /**
     * Launches the Calendar application.
     *
     * <p>This method initializes the calendar system and continuously displays
     * a menu-driven interface for user interaction. Based on the selected menu
     * option, the application performs the requested calendar operation.</p>
     *
     * <p>Menu options include:</p>
     * <ol>
     *     <li>Add an event</li>
     *     <li>Remove an event</li>
     *     <li>Display all events</li>
     *     <li>Search events</li>
     *     <li>Update an event</li>
     *     <li>Exit the application</li>
     *     <li>Run the tutorial</li>
     * </ol>
     *
     * @param args command-line arguments (not used)
     * @throws Exception if an unexpected runtime error occurs
     */
    public static void main(String[] args) throws Exception {

        /**
         * Stores and manages all calendar events.
         */
        Calendar calendar = new Calendar();

        /**
         * Reads user input from the console.
         */
        Scanner scanner = new Scanner(System.in);

        /**
         * Tracks the currently selected menu option.
         * Initialized to -1 to ensure the menu loop starts.
         */
        int choice = -1;

        /**
         * Main application loop.
         *
         * <p>Continues executing until the user selects the exit option.</p>
         */
        while (choice != 6) {

            // Display main menu
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
             * Reads the user's menu selection.
             */
            choice = scanner.nextInt();

            /**
             * Consumes the trailing newline character left by nextInt()
             * to prevent skipped input when using nextLine().
             */
            scanner.nextLine();

            /**
             * Executes functionality based on the selected menu option.
             */
            switch (choice) {

                /**
                 * Adds a new event to the calendar.
                 */
                case 1:

                    // Request event title
                    System.out.print("Enter event title: ");
                    String title = scanner.nextLine();

                    // Request event date
                    System.out.print("Enter event date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();

                    // Create and store event
                    calendar.addEvent(new Event(title, date));

                    // Success confirmation
                    System.out.println("Event added!");
                    break;

                /**
                 * Removes all events matching the specified title.
                 */
                case 2:

                    // Request title to remove
                    System.out.print("Enter title of event to remove: ");
                    String removeTitle = scanner.nextLine();

                    // Remove matching events
                    calendar.removeByTitle(removeTitle);
                    break;

                /**
                 * Displays all events currently stored in the calendar.
                 */
                case 3:

                    System.out.println("--- All Events ---");

                    // Output all events
                    calendar.displayEvents();
                    break;

                /**
                 * Provides search functionality for calendar events.
                 *
                 * <p>Users may search either by:</p>
                 * <ul>
                 *     <li>Exact date</li>
                 *     <li>Month and year</li>
                 * </ul>
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
                     * Search for events on an exact date.
                     */
                    if (searchChoice == 1) {

                        System.out.print("Enter date to search (YYYY-MM-DD): ");
                        String searchDate = scanner.nextLine();

                        System.out.println("--- Events on " + searchDate + " ---");

                        // Display matching events
                        calendar.getEventsByDate(searchDate);

                    /**
                     * Search for events by month and year.
                     */
                    } else if (searchChoice == 2) {

                        // Request year
                        System.out.print("Enter year (YYYY): ");
                        int year = scanner.nextInt();

                        // Request month
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
                     * Handles invalid search menu selections.
                     */
                    } else {

                        System.out.println("Invalid search option.");
                    }

                    break;

                /**
                 * Updates an existing event with new details.
                 */
                case 5:

                    // Request current event title
                    System.out.print("Enter title of event to update: ");
                    String oldTitle = scanner.nextLine();

                    // Request current event date
                    System.out.print("Enter date of event to update (YYYY-MM-DD): ");
                    String oldDate = scanner.nextLine();

                    // Request updated title
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();

                    // Request updated date
                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();

                    /**
                     * Attempts to update the specified event.
                     *
                     * @return true if the event was successfully updated;
                     *         false otherwise
                     */
                    boolean updated = calendar.updateEvent(
                        oldTitle,
                        oldDate,
                        new Event(newTitle, newDate)
                    );

                    // Display update result
                    if (updated) {

                        System.out.println("Event updated successfully.");

                    } else {

                        System.out.println("Event not found.");
                    }

                    break;

                /**
                 * Terminates the application.
                 */
                case 6:

                    System.out.println("Goodbye!");
                    break;

                /**
                 * Starts the interactive tutorial.
                 */
                case 7:

                    runTutorial(calendar, scanner);
                    break;

                /**
                 * Handles invalid menu selections.
                 */
                default:

                    System.out.println("Invalid option, please try again.");
            }
        }

        /**
         * Releases scanner resources before application shutdown.
         */
        scanner.close();
    }

    /**
     * Runs an interactive tutorial demonstrating application features.
     *
     * <p>The tutorial guides users through the core functionality of the
     * calendar system in a step-by-step manner.</p>
     *
     * <p>The tutorial includes:</p>
     * <ol>
     *     <li>Adding an event</li>
     *     <li>Viewing all events</li>
     *     <li>Searching for events by date</li>
     *     <li>Updating an event</li>
     *     <li>Removing an event</li>
     * </ol>
     *
     * @param calendar the calendar instance used during the tutorial
     * @param scanner  the scanner used to collect user input
     */
    public static void runTutorial(Calendar calendar, Scanner scanner) {

        System.out.println("\n=== Welcome to the Calendar Tutorial ===");
        System.out.println("This tutorial will guide you through the basics.\n");

        /**
         * STEP 1:
         * Demonstrates how to add a new event.
         */
        System.out.println("Step 1: Let's add your first event.");

        System.out.print("Enter a title: ");
        String title = scanner.nextLine();

        System.out.print("Enter a date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        // Store tutorial event
        calendar.addEvent(new Event(title, date));

        System.out.println("✅ Event added!\n");

        /**
         * STEP 2:
         * Demonstrates how to view all events.
         */
        System.out.println("Step 2: Viewing all events...");

        calendar.displayEvents();
        System.out.println();

        /**
         * STEP 3:
         * Demonstrates searching events by date.
         */
        System.out.println("Step 3: Search for your event by date.");

        System.out.print("Enter the same date: ");
        String searchDate = scanner.nextLine();

        // Perform date search
        calendar.getEventsByDate(searchDate);

        System.out.println();

        /**
         * STEP 4:
         * Demonstrates updating an event.
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
         * Demonstrates removing an event.
         */
        System.out.println("Step 5: Now remove the event.");

        System.out.print("Enter the title to remove: ");
        String removeTitle = scanner.nextLine();

        // Remove matching event(s)
        calendar.removeByTitle(removeTitle);

        System.out.println();

        /**
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
