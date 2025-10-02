package lesson23.ClassHttpServer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    static Gson gson = new Gson();
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        httpServer.createContext("/users", Task3::handleUsers);
        httpServer.start();
    }

    static void handleUsers(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        String response="";
        switch (method) {
            case "GET" -> {
                String path = httpExchange.getRequestURI().getPath();
                // /users - возвращаем всех пользователей

                if (!users.isEmpty()) {
                     if (path.startsWith("/users/")) {
                        String [] parts=path.split("/");
                        int userId = Integer.parseInt(parts[2]);
                        User userFounder= users.stream()
                                .filter(user -> user.getId() == userId)
                                .findFirst()
                                .orElse(null);
                        if (userFounder != null) {
                            response = gson.toJson(userFounder);
                            httpExchange.sendResponseHeaders(200, 0);
                        }else {
                            response = "Пользователь с таким ID не найден";
                            httpExchange.sendResponseHeaders(404,0);
                        }
                    } else if (path.equals("/users")) {
                         response = gson.toJson(users);
                         httpExchange.sendResponseHeaders(200,0);
                     }else {
                         response="такого ендпоинта не существует";
                         httpExchange.sendResponseHeaders(404,0);
                     }
                }else {
                    response="пользователи еще не были добавлены";
                    httpExchange.sendResponseHeaders(404,0);
                }
                // /users/1 - возвращаем пользователя с id=1, если пользователя с таким id нет, выдаем статус 404
                System.out.println(path);

                 // OK

            }
            case "POST" -> {
                InputStream inputStream = httpExchange.getRequestBody();
                byte[] bytes = inputStream.readAllBytes();
                String requestBody = new String(bytes);

                User user = gson.fromJson(requestBody, User.class);

                if (user.getName() == null) {
                    httpExchange.sendResponseHeaders(400, 0);
                    response = "Имя не может быть пустым";
                } else if (user.getLastName() == null) {
                    httpExchange.sendResponseHeaders(400, 0);
                    response = "Фамилия не может быть пустым";
                } else if (user.getAge() == null) {
                    httpExchange.sendResponseHeaders(400, 0);
                    response = "Возраст не может быть пустым";
                } else {
                    // TODO добавить проверку, существует ли пользователь с таким именем и фамилией
                    //   если существует, выдаем статус 409 CONFLICT
                    boolean isUserExists = users.stream()
                            .anyMatch(u -> u.getName().equals(user.getName()) && u.getLastName().equals(user.getLastName()));
                    if (!isUserExists) {
                        httpExchange.sendResponseHeaders(201, 0);
                        user.setId(users.size() + 1);
                        users.add(user);
                        response = gson.toJson(user);
                    }else {
                        httpExchange.sendResponseHeaders(409, 0);
                        response="пользователь с таким именем и фамилией уже существует";
                    }

                }
            }
            default -> {
                httpExchange.sendResponseHeaders(405, 0); // CREATED
                response = "Вы выбрали неподдерживаемый метод"; // 405 METHOD NOT ALLOWED
            }
        }

        try (OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

}
