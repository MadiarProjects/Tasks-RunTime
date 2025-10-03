package lesson24.ClientHTTP;
import java.io.IOException;
import java.net.URI;
import java.net.URI.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class example1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        URI uri = URI.create("https://ru.wikipedia.org/wiki/Список_кодов_состояния_HTTP");
        HttpRequest request =  HttpRequest.newBuilder() // получаем экземпляр билдера
                .GET()    // указываем HTTP-метод запроса
                .uri(uri) // указываем адрес ресурса
                .version(HttpClient.Version.HTTP_1_1) // указываем версию протокола
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36") // указываем заголовок Accept
                .build(); // заканчиваем настройку и создаём ("строим") http-запрос
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        // получаем стандартный обработчик тела ответа с конвертацией содержимого в строку
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
// отправляем запрос и получаем ответ от сервера
        HttpResponse<String> response = client.send(request, handler);
        // выводим код состояния и тело ответа
        System.out.println("Код ответа: " + response.statusCode());
        System.out.println("Тело ответа: " + response.body());

    }
}
