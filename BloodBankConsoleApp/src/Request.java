import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Request {

    public static void requestBlood(Scanner sc) {
        System.out.println("Enter Blood Type Needed: ");
        String bloodType = sc.nextLine();
        System.out.println("Enter Contact Info: ");
        String contact = sc.nextLine();

        String sql = "INSERT INTO BloodRequests (bloodType, contact) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:F:/java project 2.0/BloodBankConsoleApp/db/bloodbank.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bloodType);
            pstmt.setString(2, contact);
            pstmt.executeUpdate();
            System.out.println("✅ Blood request placed successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error placing blood request: " + e.getMessage());
        }
    }

    public static void viewRequests() {
        String sql = "SELECT * FROM BloodRequests";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:F:/java project 2.0/BloodBankConsoleApp/db/bloodbank.db");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                System.out.println("Request ID: " + rs.getInt("id") +
                        ", Blood Type: " + rs.getString("bloodType") +
                        ", Contact: " + rs.getString("contact"));
            }

            if (!hasResults) {
                System.out.println("❌ No blood requests found.");
            }
        } catch (Exception e) {
            System.err.println("❌ Error retrieving blood requests: " + e.getMessage());
        }
    }
}