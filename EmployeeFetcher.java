import java.sql.*;

public class EmployeeFetcher {

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/EmployeeDB";  // Your database URL
        String username = "root";  // Your MySQL username
        String password = "password";  // Your MySQL password
        
        // SQL query to fetch all records from the Employee table
        String query = "SELECT EmpID, Name, Salary FROM Employee";

        // Declare JDBC objects
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Establish the connection
            connection = DriverManager.getConnection(url, username, password);

            // Step 2: Create a statement object to send the SQL query to the database
            statement = connection.createStatement();

            // Step 3: Execute the query and store the result in a ResultSet
            resultSet = statement.executeQuery(query);

            // Step 4: Process the ResultSet and display the data
            System.out.println("EmpID | Name          | Salary");
            System.out.println("-------------------------------");
            while (resultSet.next()) {
                int empID = resultSet.getInt("EmpID");
                String name = resultSet.getString("Name");
                double salary = resultSet.getDouble("Salary");

                // Display the record
                System.out.println(empID + "    | " + name + " | " + salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 5: Close resources in the 'finally' block to ensure they are always closed
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
