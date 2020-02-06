package com.example.topfilm.details;

import com.example.topfilm.model.Detail;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailHelper {
    public static Detail parseJsonToDetail(String json, String urlTrailler) {
        Detail resulf = null;

        try {
            JSONObject data = new JSONObject(json);
            resulf = new Detail(data.getString("Title"), urlTrailler, data.getString("Plot"), data.getString("Actors"));
            ;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resulf;
    }
}
