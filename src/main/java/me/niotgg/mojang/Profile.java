package me.niotgg.mojang;

import me.niotgg.others.Requester;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

    /*
     * Class: Profile
     * Author: NiotGG
     * Version: 1.0
     * Description: Profile Class
     * Copright NiotGG Projects
     */

public class Profile {

    private String name;
    private String uuid;
    private Long timestamp;
    private String skin_url;
    private String cape_url;
    private boolean  legacy;
    private boolean demo;

    protected Profile get(String uuid) {
        try {
            String result = new Requester().post(" https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
            if (result == null) {
                return null;
            }
            if (result.contains("error")) {
                JSONObject json = new JSONObject(result);
                System.out.println("Error: " + json.getString("error"));
                System.out.println("ErrorMessage: " + json.getString("errorMessage"));
                return null;
            }
            if (!result.contains("properties")) {
                return null;
            }
            if (!result.contains("value")) {
                return null;
            }
            JSONObject jsonresult = new JSONObject(result);
            if (jsonresult.toString().contains("legacy")) {
                this.legacy = jsonresult.getBoolean("legacy");
            } else {
                this.legacy = false;
            }

            if (jsonresult.toString().contains("demo")) {
                this.demo = jsonresult.getBoolean("demo");
            } else {
                this.demo = false;
            }
            JSONArray jsonarray = new JSONObject(result).getJSONArray("properties");
            String value = null;
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject o = new JSONObject(jsonarray.get(i).toString());
                value = o.getString("value");
            }



            String decoded = new String(Base64.getDecoder().decode(value));
            JSONObject jsondecoded = new JSONObject(decoded);
            this.timestamp = jsondecoded.getLong("timestamp");
            this.uuid = jsondecoded.getString("profileId");
            this.name = jsondecoded.getString("profileName");
            if (decoded.contains("SKIN")) {
                this.skin_url = jsondecoded.getJSONObject("textures").getJSONObject("SKIN").getString("url");
            } else {
                this.skin_url = null;
            }

            if (decoded.contains("CAPE")) {
                this.cape_url = jsondecoded.getJSONObject("textures").getJSONObject("CAPE").getString("url");
            } else {
                this.cape_url = null;
            }

            return this;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getSkin_url() {
        return skin_url;
    }

    public String getCape_url() {
        return cape_url;
    }

    public boolean isLegacy() {
        return legacy;
    }

    public boolean isDemo() {
        return demo;
    }



}
