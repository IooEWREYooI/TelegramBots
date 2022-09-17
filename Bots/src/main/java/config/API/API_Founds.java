package config.API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import config.DTO.FoundDTO;

import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class API_Founds {

    public static void main(String... args) throws IOException {
        URL url = new URL("https://api.cryptorank.io/v0/coin-funds?withSummary=true");
        URLConnection connection =  url.openConnection();

        JsonReader json = new JsonReader(new InputStreamReader(connection.getInputStream()));
        JsonElement jsonElement = JsonParser.parseReader(json);

        JsonObject rootObject = jsonElement.getAsJsonObject();
        JsonArray data = rootObject.getAsJsonArray("data");
        Iterator<JsonElement> dataIterable = data.iterator();
        ArrayList<FoundDTO> foundsList = new ArrayList<>();
        while (dataIterable.hasNext()){
            foundsList.add(new FoundDTO(dataIterable.next().getAsJsonObject()));
        }
        System.out.println(foundsList.get(228).getName());

    }
}
