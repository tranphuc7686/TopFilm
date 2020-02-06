package com.example.topfilm.rank;

import com.example.topfilm.model.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RankHepler {
    public static List<Film> parseJsonToFilm(String json) {
        List<Film> resulf = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = (JSONArray) jsonObject.get("top");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = (JSONObject) jsonArray.get(i);
                resulf.add(new Film(data.getInt("id"),data.getString("point"), data.getString("name"), data.getString("trailler"), data.getString("url")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resulf;
    }
}
