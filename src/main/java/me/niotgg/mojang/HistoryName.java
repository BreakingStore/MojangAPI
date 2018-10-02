package me.niotgg.mojang;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryName {

    /**
     * Class: HistoryName
     * Author: NiotGG
     * Version: 1.0
     * Description: HistoryName Class
     * Copright NiotGG Projects
     */

    private List<String> keys = new ArrayList<>();
    private List<Long> values = new ArrayList<>();
    private HashMap<String, Long> hash = new HashMap<>();

    public HistoryName(JSONArray json) {
        for (int i = 0; i < json.length(); i++) {
            JSONObject jobject = json.getJSONObject(i);
            if (!jobject.toString().contains("changedToAt")) {
                keys.add(jobject.getString("name"));
                values.add(Long.valueOf(0));
                hash.put(jobject.getString("name"), Long.valueOf(0));
            } else {
                keys.add(jobject.getString("name"));
                values.add(jobject.getLong("changedToAt"));
                hash.put(jobject.getString("name"), jobject.getLong("changedToAt"));
            }
        }
    }

    public String getName(int i) {
        return keys.get(i);
    }

    public String getNameByChangedToAt(Long changedToAt) {
        for (String names : hash.keySet()) {
            if (hash.get(names) == changedToAt) {
                return names;
            }
        }
        System.out.println("There is no key that returns this value!");
        return null;
    }

    public Long getChangedToAt(int i) {
        return values.get(i);
    }

    public Long getChangedToAtByNickName(String name) {
        return hash.get(name);
    }

    public List<String> getNickNames() {
        return keys;
    }

}
