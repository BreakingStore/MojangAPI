package me.niotgg.mojang;

import me.niotgg.others.Requester;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Mojang {

    /**
     * Main Class
     * Class: Mojang
     * Author: NiotGG
     * Version: 1.1
     * WebDependencies: https://api.mojang.com/
     * Description: Mojang Class
     * Copright NiotGG Projects
     */

    public boolean isOnline() {
        JSONObject json;
        try {
            String result = new Requester().post("https://api.mojang.com");
            if (result == null) {
                return false;
            }
            if (!result.contains("Status")) {
                return false;
            }
            json = new JSONObject(result);
            if (!json.getString("Status").equalsIgnoreCase("OK")) {
                return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUUIDByNickName(String name) {
        JSONObject json;
        try {
            String result = new Requester().post("https://api.mojang.com/users/profiles/minecraft/"  + name);
            if (result == null) {
                return null;
            }
            if (!result.contains("id")) {
                return null;
            }
            json = new JSONObject(result);
            if (json.getString("id") == null) {
                return null;
            }
            return json.getString("id");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNickNameByUUID(String uuid) {
        JSONArray json;
        if (uuid == null) {
            return null;
        }
        try {
            String result = new Requester().post("https://api.mojang.com/user/profiles/" + uuid + "/names");
            if (result == null) {
                return null;
            }
            if (!result.contains("name")) {
                return null;
            }

            json = new JSONArray(result);

            return json.getJSONObject(json.length() - 1).getString("name");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HistoryName getHistoryNameByNickName(String name) {
        JSONArray json;
        String resultUUID = getUUIDByNickName(name);
        if (resultUUID == null) {
            return null;
        }
        try {
            String result = new Requester().post("https://api.mojang.com/user/profiles/" + resultUUID + "/names");
            if (result == null) {
                return null;
            }
            if (!result.contains("name")) {
                return null;
            }
            json = new JSONArray(result);
            HistoryName history = new HistoryName(json);
            return history;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HistoryName getHistoryNameByUUID(String uuid) {
        JSONArray json;
        if (uuid == null) {
            return null;
        }
        try {
            String result = new Requester().post("https://api.mojang.com/user/profiles/" + uuid + "/names");
            if (result == null) {
                return null;
            }
            if (!result.contains("name")) {
                return null;
            }

            json = new JSONArray(result);
            HistoryName history = new HistoryName(json);

            return history;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Profile getProfileByUUID(String uuid) {
        return new Profile().get(uuid);
    }

    public Profile getProfileByNickName(String name) {
        String resultUUID = getUUIDByNickName(name);
        if (resultUUID == null) {
            return null;
        }
        return new Profile().get(resultUUID);
    }
}

