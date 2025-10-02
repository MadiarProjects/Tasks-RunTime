package lesson23.ClassHttpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/calculate",Task2::calculateHandler);

        server.start();
    }

    public static void calculateHandler(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().getQuery();
        String str;
        if (query == null) {
            httpExchange.sendResponseHeaders(400, 0);
            str = "a and b hasn't written";
        } else {
            httpExchange.sendResponseHeaders(200, 0);
            String [] params = query.split("&");
            String a=params[0].split("=")[1];
            String b=params[1].split("=")[1];
            str="result="+(Integer.parseInt(a)+Integer.parseInt(b));
        }
        try (OutputStream os = httpExchange.getResponseBody()) {
            os.write(str.getBytes());
        }
    }
}
