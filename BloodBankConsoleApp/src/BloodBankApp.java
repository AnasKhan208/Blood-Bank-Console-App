import java.util.Scanner;

public class BloodBankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create tables if they don't exist
        DatabaseManager.createTables();

        while (true) {
            System.out.println("\n=== Blood Bank Application ===");
            System.out.println("1. Register Donor");
            System.out.println("2. Search Donors");
            System.out.println("3. Place Blood Request");
            System.out.println("4. View Blood Requests");
            System.out.println("5. Update Donor Contact");
            System.out.println("6. Delete Donor");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> Donor.registerDonor(sc);
                    case 2 -> Donor.searchDonors(sc);
                    case 3 -> Request.requestBlood(sc);
                    case 4 -> Request.viewRequests();
                    case 5 -> {
                        System.out.println("Enter Donor Name to Update: ");
                        String name = sc.nextLine();
                        System.out.println("Enter New Contact Info: ");
                        String contact = sc.nextLine();
                        Donor.updateContact(name, contact);
                    }
                    case 6 -> {
                        System.out.println("Enter Donor Name to Delete: ");
                        String name = sc.nextLine();
                        Donor.deleteDonor(name);
                    }
                    case 7 -> {
                        System.out.println("👋 Exiting application. Stay healthy!");
                        sc.close();
                        System.exit(0);
                    }
                    default -> System.out.println("⚠️ Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: Please enter a valid numeric option.");
            }
        }
    }
}