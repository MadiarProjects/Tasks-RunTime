package Lesson26.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException {
        Scanner scanner=new Scanner(System.in);
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";
        //подключение к базе данных
        Connection connection = DriverManager.getConnection(url, user, password);
//        System.out.println("Connected to database successfully");
//        System.out.println("Please enter your name");
//        String name=scanner.nextLine();

        //составление запроса
//        PreparedStatement ps= connection.prepareStatement("select * from developers where name =?");
//        ps.setString(1,name);
        //отправка запрса и получение результата
//        ResultSet rs=ps.executeQuery();
//        //rs.next возвращает boolean если оно не пустое
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            System.out.println(id+ " " );
//        }
    //task
//        System.out.println("введите начальный диапозон");
//        int  start =scanner.nextInt();
//        System.out.println("введите конечный диапозон");
//        int  end =scanner.nextInt();
//        String sql= "select cities.name from cities where population between ? and ?";
        System.out.println("название страны:");
        String country_name=scanner.nextLine();
        String SQL = """
                select cities.name AS Cities_name,cities.population Population
                from countries
                join cities on countries.id = cities.country_id
                where countries.name = ?;
                """;
    PreparedStatement ps= connection.prepareStatement(SQL);
    ps.setString(1, country_name);
    ResultSet rs=ps.executeQuery();
    while(rs.next()){
        String cities_name=rs.getString("Cities_name");
        int population=rs.getInt("Population");
        System.out.println(cities_name+" "+population);
    }
//        ps.setInt(1,start);
//        ps.setInt(2,end);
//        ResultSet rs=ps.executeQuery();
//        while(rs.next()){
//            System.out.println(rs.getString(1));
//        }
    }
}
