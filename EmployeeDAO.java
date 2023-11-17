import java.util.List;

public interface EmployeeDAO {
    void createTable();
    void insertEmployee(Employee employee);
    List<Employee> getAllEmployees();
    void close();
}
