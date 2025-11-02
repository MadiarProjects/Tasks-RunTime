package Lesson26.JDBC.Example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO - Data Access Object
public class EmployeeDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/sql-practice-madiar";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    // Получить список сотрудников.
    public List<Employee> getAll() {
        String sql = "select * from employees";
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            List<Employee> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Employee employee = new Employee(id, name);
                list.add(employee);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Получить сотрудника по id.
    public Employee getById(int id) {
        String sql = "select * from employees where id = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new Employee(id, name);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Создать сотрудника.
    public void insert(Employee employee) {
        String sql = "insert into employees (name) values (?) returning id";
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, employee.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                employee.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Обновить сотрудника.
    public void update(int id, Employee updatingEmployee) {
        String sql = "update employees set name = ? where id = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, updatingEmployee.getName());
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Сотрудник с id=" + id + " не найден");
            } else {
                System.out.println("Сотрудник успешно обновлен");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Удалить сотрудника.
    public void delete(int id) {
        String sql = "delete from employees where id = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Сотрудник с id=" + id + " не найден");
            } else {
                System.out.println("Сотрудник успешно удален");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}