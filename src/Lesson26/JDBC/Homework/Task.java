package Lesson26.JDBC.Homework;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Task {
    public static void main(String[] args) throws IOException {
        HttpServer hS = HttpServer.create(new InetSocketAddress(8080), 0);
        hS.createContext("/continents", Task::handleContinents);
        hS.start();
    }

    static Gson gson = new Gson();

    static void handleContinents(HttpExchange he) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";
        String response = "";
        String sql = "select * from continents order by id";
        List<Continents> continents = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement PS = connection.prepareStatement(sql);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {
                continents.add(new Continents(rs.getInt("id"), rs.getString("name")));
            }
            response = gson.toJson(continents);
            he.sendResponseHeaders(200, response.getBytes().length);
        } catch (SQLException e) {
            response = "{error:" + e.getMessage() + "}";
            he.sendResponseHeaders(500, response.getBytes().length);
        }

        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

}
