package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String name;
	private Long uid;
	private String screenName;
	private String profileImageUrl;
	private String tagline;
	private Long following;
	private Long followers;

	public static User fromJson(JSONObject json) {
		User user = new User();
		try {
			user.name = json.getString("name");
			user.uid = json.getLong("id");
			user.screenName = json.getString("screen_name");
			user.profileImageUrl = json.getString("profile_image_url");
			user.tagline = json.getString("description");
			user.followers = json.getLong("followers_count");
			user.following = json.getLong("friends_count");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return user;

	}
	
	public Long getFollowers() {
		return followers;
	}
	
	public String getTagline() {
		return tagline;
	}

	public String getName() {
		return name;
	}

	public Long getUid() {
		return uid;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public Long getFollowing() {
		return following;
	}

}
