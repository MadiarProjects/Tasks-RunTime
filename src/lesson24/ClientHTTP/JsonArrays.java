package lesson24.ClientHTTP;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Period;
import java.util.*;

public class JsonArrays {
    public static void main(String[] args) throws IOException, InterruptedException {
//        task1:
//        URI uri = URI.create("https://swapi.dev/api/people?format=json");
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .uri(uri)
//                .build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        JsonObject object = JsonParser.parseString(response.body()).getAsJsonObject();
//        JsonArray characters = object.get("results").getAsJsonArray();
//        int bestHeight=0;
//        String name="";
//        for (JsonElement character : characters) {
//            String nameOfCharacter = character.getAsJsonObject().get("name").getAsString();
//            int characterHeight = character.getAsJsonObject().get("height").getAsInt();
//
//            if (bestHeight<=characterHeight){
//                bestHeight=characterHeight;
//                name=nameOfCharacter;
//            }
//        }
//        System.out.println(name +" самый высокий "+bestHeight);
        //task1 end

        //task2:
        Scanner nameScanner = new Scanner(System.in);
        System.out.println("введите имя исполнителя:");
        String name = nameScanner.nextLine().trim().replace(" ", "%20");
        URI uri = URI.create("https://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=" + name + "&api_key=31c6a431b77159b2e385bc83d1be07db&format=json");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        JsonObject object = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray albums = object.getAsJsonObject("topalbums").get("album").getAsJsonArray();
        for (JsonElement album : albums) {
            String nameOfMusic = album.getAsJsonObject().get("name").getAsString();
            int playCount = album.getAsJsonObject().get("playcount").getAsInt();
            System.out.println(" - " + nameOfMusic + "(" + playCount + " playCount)");
            String mbid;
            JsonElement isMbidNull = album.getAsJsonObject().get("mbid");
            if (isMbidNull == null) {
                continue;
            } else {
                mbid = isMbidNull.getAsString();
                musicsInfo(mbid);
            }
        }//task2 end
    }

    public static void musicsInfo(String mbid) throws IOException, InterruptedException {
        URI uriForMusics = URI.create("https://ws.audioscrobbler.com/2.0/?method=album.getinfo&mbid=" + mbid + "&format=json&api_key=31c6a431b77159b2e385bc83d1be07db");
        HttpClient clientForMusics = HttpClient.newHttpClient();
        HttpRequest requestForMUsics = HttpRequest.newBuilder()
                .GET()
                .uri(uriForMusics)
                .build();
        HttpResponse<String> responseForMUsics = clientForMusics.send(requestForMUsics, HttpResponse.BodyHandlers.ofString());
        JsonObject object = JsonParser.parseString(responseForMUsics.body()).getAsJsonObject();
        JsonElement isAlbumNull = object.get("album");
        if (isAlbumNull == null) {
            System.out.println("album is null");
            return;
        } else if (isAlbumNull.getAsJsonObject().get("tracks") == null) {
            System.out.println("tracks is null");
            return;
        } else {
            JsonElement tracksIsNotNull = isAlbumNull.getAsJsonObject().getAsJsonObject("tracks").get("track");
            JsonArray track = null;
            String nameOfMusic = "";
            int timeOfMusic = 0;
            if (Objects.requireNonNull(tracksIsNotNull).isJsonArray()) {
                track = tracksIsNotNull.getAsJsonArray();
            } else {
                JsonElement isTimeOfMusicNull = tracksIsNotNull.getAsJsonObject().get("duration");
                if (isTimeOfMusicNull == null||isTimeOfMusicNull.isJsonNull()) {
                    System.out.println("duration of this music is null");
                    return;
                }
                nameOfMusic = tracksIsNotNull.getAsJsonObject().get("name").getAsString();
                timeOfMusic = isTimeOfMusicNull.getAsInt();
                System.out.println(nameOfMusic + "(" + timeOfMusic + " seconds)");
                return;
            }
            if (track == null || track.size() < 10) {
                return;
            }
            for (int i = 0; i < 10; i++) {
                JsonElement isTimeOfMusicNull = track.get(i).getAsJsonObject().get("duration");
                if (isTimeOfMusicNull == null || isTimeOfMusicNull.isJsonNull()) {
                    continue;
                }
                nameOfMusic = track.get(i).getAsJsonObject().get("name").getAsString();
                timeOfMusic = track.get(i).getAsJsonObject().get("duration").getAsInt();
                System.out.println(nameOfMusic + "(" + timeOfMusic + " seconds)");

            }
        }
    }
}

