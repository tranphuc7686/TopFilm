package com.example.topfilm.category;

import com.example.topfilm.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryHelper {
    public static List<Category> parseJsonToCategory(String json){
        List<Category> resulf = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = (JSONArray) jsonObject.get("top");
            for (int i = 0 ; i < jsonArray.length() ; i++){
                JSONObject data = (JSONObject) jsonArray.get(i);
                resulf.add(new Category(data.getInt("id"),data.getString("name"),data.getString("url")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resulf;
    }
}
