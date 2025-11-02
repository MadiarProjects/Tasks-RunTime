package Lesson26.JDBC.Example;

import java.sql.*;

public class Example1 {
    public static void main(String[] args)throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/sql-practice-madiar";
        String user = "postgres";
        String password = "postgres";

        // подключение к базе данных
        Connection connection = DriverManager.getConnection(url, user, password);


        // insert
//        String sql = "insert into employees (name) values (?)";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setString(1, "Dan");
//
//        ps.executeUpdate();

        // update
//        String sql = "update employees set name = ? where id = ?";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setString(1, "Daniel Updated");
//        ps.setInt(2, 4);
//        int rowsAffected = ps.executeUpdate();
//        if (rowsAffected == 0) {
//            System.out.println("Пользователь не найден");
//        } else {
//            System.out.println("Успешно обновили");
//        }

        // delete
//        String sql = "delete from employees where id = ?";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setInt(1, 4);
//        int rowsAffected = ps.executeUpdate();
//        if (rowsAffected == 0) {
//            System.out.println("Пользователь не найден");
//        } else {
//            System.out.println("Успешно удалили");
//        }


    }
}
