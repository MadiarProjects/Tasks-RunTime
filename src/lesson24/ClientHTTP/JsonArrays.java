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
import java.util.*;

public class JsonArrays {
    static String nameOfAutor;

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
        nameOfAutor = nameScanner.nextLine().trim().replace(" ", "%20");
        URI uri = URI.create("https://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=" + nameOfAutor + "&api_key=31c6a431b77159b2e385bc83d1be07db&format=json");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        JsonObject object = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonElement isTopAlbumsNull = object.get("topalbums");
        if (isTopAlbumsNull == null) {
            return;
        }
        JsonElement isAlbumsNull = isTopAlbumsNull.getAsJsonObject().get("album");
        if (isAlbumsNull == null) {
            return;
        }
        JsonArray albums = isAlbumsNull.getAsJsonArray();
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
        HttpResponse<String> responseForMusics = clientForMusics.send(requestForMUsics, HttpResponse.BodyHandlers.ofString());
        JsonObject object = JsonParser.parseString(responseForMusics.body()).getAsJsonObject();
        JsonElement isAlbumNull = object.get("album");
        if (isAlbumNull == null) {
            return;
        }
        JsonElement isTracksNull = isAlbumNull.getAsJsonObject().get("tracks");
        if (isTracksNull == null) {
            return;
        }
        JsonElement isTrackNull = isTracksNull.getAsJsonObject().get("track");
        if (isTrackNull == null) {
            return;
        }
        JsonArray track ;
        String nameOfMusic = "";
        int timeOfMusic = 0;
        if (isTrackNull.isJsonArray()) {
            track = isTrackNull.getAsJsonArray();
        } else {
            JsonElement isTimeOfMusicNull = isTrackNull.getAsJsonObject().get("duration");
            if (isTimeOfMusicNull.isJsonNull()) {
                return;
            }
            nameOfMusic = isTrackNull.getAsJsonObject().get("name").getAsString();
            timeOfMusic = isTimeOfMusicNull.getAsInt();
            System.out.println(nameOfMusic + "(" + timeOfMusic + " seconds)");
            return;
        }
        if (track.size() < 10) {
            return;
        }
        for (int i = 0; i < 10; i++) {
            JsonElement isTimeOfMusicNull = track.get(i).getAsJsonObject().get("duration");
            if (isTimeOfMusicNull.isJsonNull()) {
                continue;
            }
            nameOfMusic = track.get(i).getAsJsonObject().get("name").getAsString();
            timeOfMusic = isTimeOfMusicNull.getAsInt();
            System.out.print(nameOfMusic + "(" + timeOfMusic + " seconds)");
            musicJenre(nameOfMusic);
        }
    }


    public static void musicJenre(String nameOfMusic) throws IOException, InterruptedException {
        URI uri = URI.create("https://ws.audioscrobbler.com/2.0/?method=track.getinfo&artist=" + nameOfAutor + "&track=" + nameOfMusic.trim().replace("\"", " ").replace(" ", "&20") + "&format=json&api_key=31c6a431b77159b2e385bc83d1be07db");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestForGenre = HttpRequest
                .newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> responseForGenre = client.send(requestForGenre, HttpResponse.BodyHandlers.ofString());
        JsonObject object = JsonParser.parseString(responseForGenre.body()).getAsJsonObject();
        JsonElement isTrackNull = object.get("track");
        if (isTrackNull == null) {
            System.out.print("\n");
            return;
        }
        JsonElement isTopTagsNull = isTrackNull.getAsJsonObject().get("toptags");
        if (isTopTagsNull == null) {
            System.out.print("\n");
            return;
        }
        JsonElement isTagNull = isTopTagsNull.getAsJsonObject().get("tag");
        if (isTagNull == null) {
            System.out.print("\n");
            return;
        }
        JsonArray tags = isTagNull.getAsJsonArray();
        if (tags.isEmpty()) {
            System.out.print("\n");
            return;
        }
        List<String> jenre = new ArrayList<>();
        String nameOfJenre = "";
        for (JsonElement tag : tags) {
            jenre.add(tag.getAsJsonObject().get("name").getAsString());
            nameOfJenre = tag.getAsJsonObject().get("name").getAsString();
            if (nameOfJenre == null) {
                continue;
            }
        }
        System.out.print(jenre + "\n");
    }
}

