public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "username";
        String password = "password";

        EmployeeDAO employeeDAO = new EmployeeDAOImpl(url, username, password);
        employeeDAO.createTable();

        Employee employee1 = new Employee(1, "John", "Doe");
        Employee employee2 = new Employee(2, "Jane", "Smith");

        employeeDAO.insertEmployee(employee1);
        employeeDAO.insertEmployee(employee2);

        List <Employee> employees = employeeDAO.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        employeeDAO.close();
    }
}