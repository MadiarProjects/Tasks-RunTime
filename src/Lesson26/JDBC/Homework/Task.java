package Lesson26.JDBC.Homework;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public class Task {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer= HttpServer.create();
        httpServer.start();
    }
}
