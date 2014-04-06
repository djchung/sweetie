package com.codepath.apps.sweetie.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String name;
	private long uid;
	private String screenName;
	private String profileBgImageUrl;
	private String profileImage;
	private int numTweets;
	private int followersCount;
	private int friendsCount;
	private String tagline;
	
    public String getName() {
        return name;
    }

    public long getId() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileBackgroundImageUrl() {
        return profileBgImageUrl;
    }
    
    public String getProfileImage() {
    	return profileImage;
    }

    public int getNumTweets() {
        return numTweets;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }
    
    public String getTagline() {
    	return tagline;
    }
    public static User fromJson(JSONObject json) {
        User u = new User();
        try {
        	u.name = json.getString("name");
        	u.uid = json.getLong("id");
        	u.screenName = json.getString("screen_name");
        	u.profileBgImageUrl = json.getString("profile_background_image_url");
        	u.numTweets = json.getInt("statuses_count");
        	u.followersCount = json.getInt("followers_count");
        	u.friendsCount = json.getInt("friends_count");
        	u.profileImage = json.getString("profile_image_url");
        	u.tagline = json.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }
}
