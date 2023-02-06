import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/calculations";
    private static final String USER = "postgres";
    private static final String PASS = "Infinity2021.";
    public static Scanner scanner = new Scanner(System.in);


    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        // Create the table to store the calculations
        createTable();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the calculator! Choose an option:");
            System.out.println("1. Mathematical operations");
            System.out.println("2. Boolean operations");
            System.out.println("3. View calculations");
            System.out.println("4. Clear calculations");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the two numbers:");
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    MathOperation mathOperation = new MathOperation(a, b);
                    int mathResult = mathOperation.performOperation();
                    System.out.println("Result: " + mathResult);
                    insertCalculation(a, b, mathResult, "math");
                    break;
                case 2:
                    System.out.println("Enter the two boolean values (true or false):");
                    boolean c = scanner.nextBoolean();
                    boolean d = scanner.nextBoolean();
                    BooleanOperation booleanOperation = new BooleanOperation(c, d);
                    int boolResult = booleanOperation.performOperation();
                    System.out.println("Result: " + boolResult);
                    insertCalculation(c ? 1 : 0, d ? 1 : 0, boolResult, "bool");
                    break;
                case 3:
                    viewCalculations();
                    break;
                case 4:
                    clearCalculations();
                    break;
                case 5:
                    System.out.println("Exiting the calculator...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS calculations (\n"
                + "id serial PRIMARY KEY,\n"
                + "operand_1 INTEGER NOT NULL,\n"
                + "operand_2 INTEGER NOT NULL,\n"
                + "result INTEGER NOT NULL,\n"
                + "operation_type VARCHAR(255) NOT NULL\n"
                + ");";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void insertCalculation(int operand1, int operand2, int result, String operationType) {
        String sql = "INSERT INTO calculations(operand_1, operand_2, result, operation_type) VALUES(?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, operand1);
            pstmt.setInt(2, operand2);
            pstmt.setInt(3, result);
            pstmt.setString(4, operationType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void viewCalculations() {
        String sql = "SELECT * FROM calculations";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID\tOperand 1\tOperand 2\tResult\tOperation Type");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getInt("operand_1") + "\t\t"
                        + rs.getInt("operand_2") + "\t\t"
                        + rs.getInt("result") + "\t\t"
                        + rs.getString("operation_type"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }   private static void clearCalculations() {
        String sql = "DELETE FROM calculations";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
