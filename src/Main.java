import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Calendar calendar = new Calendar();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        //Options and choices

        while (choice != 6) {
            System.out.println("\n--- Calendar Menu ---");
            System.out.println("1. Add event");
            System.out.println("2. Remove event by title");
            System.out.println("3. View all events");
            System.out.println("4. Search by date");
            System.out.println("5. Update event");
            System.out.println("6. Exit");
            System.out.println("7. Run tutorial");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            // Cases for each option and a default for errors

            switch (choice) {
                case 1:
                    System.out.print("Enter event title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter event date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    calendar.addEvent(new Event(title, date));
                    System.out.println("Event added!");
                    break;

                case 2:
                    System.out.print("Enter title of event to remove: ");
                    String removeTitle = scanner.nextLine();
                    calendar.removeByTitle(removeTitle);
                    break;

                case 3:
                    System.out.println("--- All Events ---");
                    calendar.displayEvents();
                    break;

                case 4:
                     System.out.println("Search by:");
                    System.out.println("1. Exact date");
                    System.out.println("2. Month and year");
                    System.out.print("Choose option: ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (searchChoice == 1) {
                        System.out.print("Enter date to search (YYYY-MM-DD): ");
                        String searchDate = scanner.nextLine();
                        System.out.println("--- Events on " + searchDate + " ---");
                        calendar.getEventsByDate(searchDate);

                    } else if (searchChoice == 2) {
                        System.out.print("Enter year (YYYY): ");
                        int year = scanner.nextInt();

                        System.out.print("Enter month (1-12): ");
                        int month = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        System.out.println("--- Events in " + year + "-" + String.format("%02d", month) + " ---");
                        calendar.getEventsByMonthYear(month, year);

                    } else {
                        System.out.println("Invalid search option.");
                    }
                    break;

                case 5:
                    System.out.print("Enter title of event to update: ");
                    String oldTitle = scanner.nextLine();

                    System.out.print("Enter date of event to update (YYYY-MM-DD): ");
                    String oldDate = scanner.nextLine();

                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();

                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();

                    boolean updated = calendar.updateEvent(
                        oldTitle,
                        oldDate,
                        new Event(newTitle, newDate)
                    );

                    if (updated) {
                        System.out.println("Event updated successfully.");
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    break;

                case 7:
                runTutorial(calendar, scanner);
                break;

                default:
                    System.out.println("Invalid option, please try again.");

            }
        }


        scanner.close();
    }

    public static void runTutorial(Calendar calendar, Scanner scanner) {
        System.out.println("\n=== Welcome to the Calendar Tutorial ===");
        System.out.println("This will guide you step-by-step.\n");

        // STEP 1: Add Event
        System.out.println("Step 1: Let's add your first event.");
        System.out.print("Enter a title: ");
        String title = scanner.nextLine();

        System.out.print("Enter a date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        calendar.addEvent(new Event(title, date));
        System.out.println("✅ Event added!\n");

        // STEP 2: View Events
        System.out.println("Step 2: Viewing all events...");
        calendar.displayEvents();
        System.out.println();

        // STEP 3: Search by Date
        System.out.println("Step 3: Search for your event by date.");
        System.out.print("Enter the same date: ");
        String searchDate = scanner.nextLine();

        calendar.getEventsByDate(searchDate);
        System.out.println();

        // STEP 4: Update Event
        System.out.println("Step 4: Let's update your event.");
        System.out.print("Enter a new title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter a new date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();

        boolean updated = calendar.updateEvent(title, date, new Event(newTitle, newDate));

        if (updated) {
            System.out.println("✅ Event updated!\n");
        } else {
            System.out.println("❌ Update failed.\n");
        }

        // STEP 5: Remove Event
        System.out.println("Step 5: Now remove the event.");
        System.out.print("Enter the title to remove: ");
        String removeTitle = scanner.nextLine();

        calendar.removeByTitle(removeTitle);
        System.out.println();

        // FINAL STEP
        System.out.println("🎉 Tutorial complete!");
        System.out.println("You now know how to:");
        System.out.println("- Add events");
        System.out.println("- View events");
        System.out.println("- Search events");
        System.out.println("- Update events");
        System.out.println("- Remove events\n");
    }
}

