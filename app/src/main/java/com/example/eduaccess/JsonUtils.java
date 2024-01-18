package com.example.eduaccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class JsonUtils {

    private JsonUtils() {
        // private constructor to prevent instantiation
    }

    // Json file create
    public static JSONObject videoToJson(Video video) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Video.JSON_KEY_VIDEO_ID, video.getVideoId());
        jsonObject.put(Video.JSON_KEY_VIDEO_TITLE, video.getVideoTitle());
        jsonObject.put(Video.JSON_KEY_VIDEO_TEACHER, video.getVideoTeacher());
        jsonObject.put(Video.JSON_KEY_IMAGE_LINK, video.getVideoLink());
        return jsonObject;
    }

    public static Video fromJson(JSONObject jsonObject) throws JSONException {
        int videoId = jsonObject.getInt(Video.JSON_KEY_VIDEO_ID);
        String videoTitle = jsonObject.getString(Video.JSON_KEY_VIDEO_TITLE);
        String videoTeacher = jsonObject.getString(Video.JSON_KEY_VIDEO_TEACHER);
        String imageLink = jsonObject.getString(Video.JSON_KEY_IMAGE_LINK);
        String videoLink = jsonObject.getString(Video.JSON_KEY_VIDEO_LINK);
        return new Video(videoId, videoTitle, videoTeacher, imageLink, videoLink);
    }

    public static ArrayList<Video> fromJsonArray(JSONArray jsonArray) throws JSONException {
        ArrayList<Video> videos = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int videoId = jsonObject.getInt(Video.JSON_KEY_VIDEO_ID);
            String videoTitle = jsonObject.getString(Video.JSON_KEY_VIDEO_TITLE);
            String videoTeacher = jsonObject.getString(Video.JSON_KEY_VIDEO_TEACHER);
            String imageLink = jsonObject.getString(Video.JSON_KEY_IMAGE_LINK);
            String videoLink = jsonObject.getString(Video.JSON_KEY_VIDEO_LINK);
            videos.add(new Video(videoId, videoTitle, videoTeacher, imageLink, videoLink));
        }
        return videos;
    }
}
