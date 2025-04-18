import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {

    private static Connection connection = null;

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/InventoryDB"; // Your database URL
        String username = "root"; // Your MySQL username
        String password = "password"; // Your MySQL password

        Scanner scanner = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection(url, username, password);
            boolean exit = false;

            while (!exit) {
                // Display menu options
                System.out.println("\nSelect an operation:");
                System.out.println("1. Create Product");
                System.out.println("2. Read Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        createProduct(scanner);
                        break;
                    case 2:
                        readProducts();
                        break;
                    case 3:
                        updateProduct(scanner);
                        break;
                    case 4:
                        deleteProduct(scanner);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Create Product
    private static void createProduct(Scanner scanner) {
        System.out.print("Enter ProductID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter ProductName: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        String query = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false); // Begin transaction
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, productId);
                stmt.setString(2, productName);
                stmt.setDouble(3, price);
                stmt.setInt(4, quantity);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Product created successfully!");
                    connection.commit(); // Commit transaction
                }
            } catch (SQLException e) {
                connection.rollback(); // Rollback in case of an error
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true); // Reset auto-commit mode
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Products
    private static void readProducts() {
        String query = "SELECT * FROM Product";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nProductID | ProductName | Price | Quantity");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");

                System.out.println(productId + " | " + productName + " | " + price + " | " + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Product
    private static void updateProduct(Scanner scanner) {
        System.out.print("Enter ProductID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter new ProductName: ");
        String productName = scanner.nextLine();
        System.out.print("Enter new Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();

        String query = "UPDATE Product SET ProductName = ?, Price = ?, Quantity = ? WHERE ProductID = ?";

        try {
            connection.setAutoCommit(false); // Begin transaction
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, productName);
                stmt.setDouble(2, price);
                stmt.setInt(3, quantity);
                stmt.setInt(4, productId);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Product updated successfully!");
                    connection.commit(); // Commit transaction
                }
            } catch (SQLException e) {
                connection.rollback(); // Rollback in case of an error
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true); // Reset auto-commit mode
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Product
    private static void deleteProduct(Scanner scanner) {
        System.out.print("Enter ProductID to delete: ");
        int productId = scanner.nextInt();

        String query = "DELETE FROM Product WHERE ProductID = ?";

        try {
            connection.setAutoCommit(false); // Begin transaction
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, productId);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Product deleted successfully!");
                    connection.commit(); // Commit transaction
                }
            } catch (SQLException e) {
                connection.rollback(); // Rollback in case of an error
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true); // Reset auto-commit mode
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
