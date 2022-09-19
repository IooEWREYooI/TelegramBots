package config.API;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.sun.istack.NotNull;
import config.DTO.FoundDTO;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Утильный класс для получения данных из API
 * <a href="https/t.me/u_bablo"><strong>Канал автора</strong></a>
 @throw NullPointerException
 @author @lllooEWREYoolll
 */
public class API_Founds {
    private JsonReader OpenConnection(){
        JsonReader json = null;
        try {
            URL url = new URL("https://api.cryptorank.io/v0/coin-funds?withSummary=true");
            URLConnection connection = url.openConnection();
            json = new JsonReader(new InputStreamReader(connection.getInputStream()));
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }
    public ArrayList<FoundDTO> getListOfFounds() {
        JsonReader json = OpenConnection();
        JsonElement jsonElement = JsonParser.parseReader(json);
        JsonObject rootObject = jsonElement.getAsJsonObject();
        JsonArray data = rootObject.getAsJsonArray("data");

        Iterator<JsonElement> dataIterable = data.iterator();
        ArrayList<FoundDTO> foundsList = new ArrayList<>();

        while (dataIterable.hasNext()){
            foundsList.add(new FoundDTO(dataIterable.next().getAsJsonObject()));
        }
        return foundsList;
    }
    public ArrayList<FoundDTO> foundByNameContains(@NotNull String word) {
        Iterator<FoundDTO> foundsList = new API_Founds().getListOfFounds().iterator();
        ArrayList<FoundDTO> listOfFoundsWhoIsContains = new ArrayList<FoundDTO>();
        while(foundsList.hasNext()){
            FoundDTO found = foundsList.next();
            if (found.getName().contains(word)){
                listOfFoundsWhoIsContains.add(found);
            }
        }
        if (listOfFoundsWhoIsContains.size() > 0){
            return listOfFoundsWhoIsContains;
        } else throw new NullPointerException();
    }
    public FoundDTO foundByName(@NotNull String name) {
        Iterator<FoundDTO> foundsList = new API_Founds().getListOfFounds().iterator();
        while(foundsList.hasNext()){
            FoundDTO found = foundsList.next();
            if (found.getName().equalsIgnoreCase(name)){
                return found;
            }
        }
        throw new NullPointerException();
    }
    public FoundDTO foundById(@NotNull long id) {
        Iterator<FoundDTO> foundsList = new API_Founds().getListOfFounds().iterator();
        while(foundsList.hasNext()){
            FoundDTO found = foundsList.next();
            if (found.getId() == id){
                return found;
            }
        }
        throw new NullPointerException();
    }
    public ArrayList<FoundDTO> foundByListOfIds(ArrayList<Integer> listOfIds) {
        Iterator<FoundDTO> foundsList = new API_Founds().getListOfFounds().iterator();
        ArrayList<FoundDTO> foundsIdsList = new ArrayList<>();
        Iterator<Integer> iterableFoundsIds = listOfIds.iterator();
        while(foundsList.hasNext()){
            FoundDTO found = foundsList.next();
            while(iterableFoundsIds.hasNext()) {
                if (found.getId() == iterableFoundsIds.next()) {
                    foundsIdsList.add(found);
                }
            }
        }
        if (foundsIdsList.size() > 0)
            return foundsIdsList;
        else throw new NullPointerException();
    }
}
