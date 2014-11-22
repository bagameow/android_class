package com.y.gridimagesearch.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult {
	public String fullUrl;
	public String thumbUrl;
	public String title;

	public ImageResult(JSONObject json) {
		try {
			fullUrl = json.getString("url");
			thumbUrl = json.getString("tbUrl");
			title = json.getString("title");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static List<ImageResult> fromJSONArray(JSONArray array) {
		ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
		for(int i = 0; i < array.length(); i++) {
			try {
				imageResults.add(i, new ImageResult(array.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return imageResults;
	}
}
