package edu.polytech.repo_ihm.api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
public class Requester {

    private static final String baseURL = "https://api.spoonacular.com/recipes";
    private static final String key = "?apiKey=24bdbb27c8234ceba5c3f893b6e23200";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject query(String parameter) throws Exception {
        String url = baseURL + key + "&query=" + parameter;
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String json_text = readAll(rd);
            JSONObject json = new JSONObject(json_text);
            return json;
        }
        finally {
            is.close();
        }
    }

    public static JSONObject getIngredientQuantity(Integer ingredientID, Integer cal) throws Exception {
        String baseUrl = "https://api.spoonacular.com/food/ingredients/" + ingredientID.toString() + "/amount";
        String url = baseUrl + key + "&nutrient=calories&unit=g&target=" + cal.toString();
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String json_text = readAll(rd);
            JSONObject json = new JSONObject(json_text);
            return json;
        }
        finally {
            is.close();
        }
    }
}
