import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;
    private Statement statement;

    public EmployeeDAOImpl(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS isolated_employee (" +
                "id INT PRIMARY KEY," +
                "firstname VARCHAR(255)," +
                "lastname VARCHAR(255)" +
                ")";

        try {
            statement.executeUpdate(createTableQuery);
            System.out.println("Jadval ba movafaghiat dorost shod");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertEmployee(Employee employee) {
        String insertQuery = "INSERT INTO isolated_employee (id, firstname, lastname) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.executeUpdate();
            System.out.println("record ba movafaghiat ijad shod");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String selectQuery = "SELECT * FROM isolated_employee";

        try {
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                Employee employee = new Employee(id, firstName, lastName);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public void close() {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}