import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Donor {

    public static void registerDonor(Scanner sc) {
        System.out.println("Enter Donor Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Blood Type: ");
        String bloodType = sc.nextLine();
        System.out.println("Enter Contact Info: ");
        String contact = sc.nextLine();

        String sql = "INSERT INTO Donors (name, bloodType, contact) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:F:/java project 2.0/BloodBankConsoleApp/db/bloodbank.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, bloodType);
            pstmt.setString(3, contact);
            pstmt.executeUpdate();
            System.out.println("✅ Donor Registered Successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error saving donor: " + e.getMessage());
        }
    }

    public static void searchDonors(Scanner sc) {
        System.out.println("Enter Blood Type to Search: ");
        String bloodType = sc.nextLine();

        String sql = "SELECT * FROM Donors WHERE bloodType = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:F:/java project 2.0/BloodBankConsoleApp/db/bloodbank.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bloodType);
            ResultSet rs = pstmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                System.out.println("Name: " + rs.getString("name") +
                        ", Blood Type: " + rs.getString("bloodType") +
                        ", Contact: " + rs.getString("contact"));
            }

            if (!hasResults) {
                System.out.println("❌ No donors found with blood type: " + bloodType);
            }
        } catch (Exception e) {
            System.err.println("❌ Error searching for donors: " + e.getMessage());
        }
    }

    public static void updateContact(String donorName, String newContact) {
        String sql = "UPDATE Donors SET contact = ? WHERE name = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:F:/java project 2.0/BloodBankConsoleApp/db/bloodbank.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newContact);
            pstmt.setString(2, donorName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Donor contact updated successfully!");
            } else {
                System.out.println("❌ Donor not found.");
            }
        } catch (Exception e) {
            System.err.println("❌ Error updating contact: " + e.getMessage());
        }
    }

    public static void deleteDonor(String donorName) {
        String sql = "DELETE FROM Donors WHERE name = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:F:/java project 2.0/BloodBankConsoleApp/db/bloodbank.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, donorName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Donor deleted successfully!");
            } else {
                System.out.println("❌ Donor not found.");
            }
        } catch (Exception e) {
            System.err.println("❌ Error deleting donor: " + e.getMessage());
        }
    }
}