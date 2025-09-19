package lesson23.ClassHttpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Random;

public class DayHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Началась обработка /day запроса от клиента.");
        DayOfWeek[] days = DayOfWeek.values();

        int randomDayIndex = new Random().nextInt(days.length);
        DayOfWeek randomDay = days[randomDayIndex];

        String response = randomDay.getDisplayName(TextStyle.SHORT,Locale.ENGLISH);

        httpExchange.sendResponseHeaders(200, 0);

        try (OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
