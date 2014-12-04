package com.codepath.apps.restclienttemplate.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
	private String body;
	private long uid;
	private String createAt;
	private User user;
	
	public static Tweet fromJson(JSONObject json) {
		Tweet tweet = new Tweet();
		try {
			tweet.body = json.getString("text");
			tweet.uid = json.getLong("id");
			tweet.createAt = json.getString("created_at");
			tweet.user = User.fromJson(json.getJSONObject("user"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return tweet;
	}

	public String getBody() {
		return body;
	}

	public long getUid() {
		return uid;
	}

	public String getCreateAt() {
		return createAt;
	}

	public User getUser() {
		return user;
	}

	public static List<Tweet> fromJsonArray(JSONArray jsonArray) {
		 List<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());
	      // Process each result in json array, decode and convert to business object
	      for (int i=0; i < jsonArray.length(); i++) {
	          JSONObject businessJson = null;
	          try {
	            businessJson = jsonArray.getJSONObject(i);
	          } catch (Exception e) {
	              e.printStackTrace();
	              continue;
	          }

	          Tweet tweet = Tweet.fromJson(businessJson);
	          if (tweet != null) {
	            tweets.add(tweet);
	          }
	      }

	      return tweets;
	  }
}

