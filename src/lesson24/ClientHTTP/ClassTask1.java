package lesson24.ClientHTTP;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ClassTask1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
//        // Создание пути
//        URI uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q=Astana&units=metric&lang=ru&appid=79d1ca96933b0328e1c7e3e7a26cb347");
//
//        // Описывает запрос (какой путь и какой метод и тд)
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()     // какой метод
//                .uri(uri)  // путь
//                .build();
//
//        // отправка запроса и получение ответа
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
//
//        JsonElement jsonElement = JsonParser.parseString(response.body());
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//
//        double temp = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
//        System.out.println("Температура воздуха " + temp + "˚C");
        //task1:
//        double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
//        System.out.println("Скорость порыва ветра"+windSpeed+"м/с");


        //task2:

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите город:");
        String nameOfCity = scanner.nextLine().trim().replace(" ","+");
        URI uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q=" + nameOfCity + "&units=metric&lang=ru&appid=79d1ca96933b0328e1c7e3e7a26cb347");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        System.out.println(nameOfCity);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 404) {
            System.out.println("такого города не существует");
        } else {
            JsonElement jsonElement = JsonParser.parseString(response.body());
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            double temp = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
            System.out.println("Температура воздуха " + temp + "˚C");
        }
    }

}
