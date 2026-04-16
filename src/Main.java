import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Calendar calendar = new Calendar();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        //Options and choices

        while (choice != 5) {
            System.out.println("\n--- Calendar Menu ---");
            System.out.println("1. Add event");
            System.out.println("2. Remove event by title");
            System.out.println("3. View all events");
            System.out.println("4. Search by date");
            System.out.println("5. Exit");
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
                    System.out.print("Enter date to search (YYYY-MM-DD): ");
                    String searchDate = scanner.nextLine();
                    System.out.println("--- Events on " + searchDate + " ---");
                    calendar.getEventsByDate(searchDate);
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }


        scanner.close();
    }
}

