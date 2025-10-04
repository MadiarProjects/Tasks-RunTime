package lesson24.ClientHTTP;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("введите сумму в тенге:");
        Scanner amountScanner = new Scanner(System.in);
        int amount = amountScanner.nextInt();
        System.out.println("Введите валюту в которую хотите поменять тенге:");
        Scanner curencyScanner = new Scanner(System.in);
        String currency = curencyScanner.nextLine().trim();
        URI uri = URI.create("https://api.apilayer.com/exchangerates_data/latest?base=" + currency + "&symbols=KZT&apikey=iISN69jOgAmSSuWq5GG68tko23CuqMLk");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement element = JsonParser.parseString(response.body());
        JsonObject object = element.getAsJsonObject();
        double rates = object.getAsJsonObject("rates").get("KZT").getAsDouble();
        double total = amount / rates;
        System.out.printf("%d тенге в %s будет: %.2f%n", amount, currency, total);
        //
    }
}
