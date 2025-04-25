import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {
    public static void createTables() {
        String url = "jdbc:sqlite:F:/java project 2.0/BloodBankConsoleApp/db/bloodbank.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            String createDonorsTable = "CREATE TABLE IF NOT EXISTS Donors (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "bloodType TEXT NOT NULL," +
                    "contact TEXT NOT NULL)";
            stmt.execute(createDonorsTable);

            String createRequestsTable = "CREATE TABLE IF NOT EXISTS BloodRequests (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bloodType TEXT NOT NULL," +
                    "contact TEXT NOT NULL)";
            stmt.execute(createRequestsTable);

            System.out.println("✅ Tables created successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error creating tables: " + e.getMessage());
        }
    }
}