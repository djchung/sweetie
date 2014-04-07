package com.codepath.apps.sweetie.models;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
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
    
    public User() {
    	
    }
    
    public User(Parcel in) {
    	name = in.readString();
    	uid = in.readLong();
    	screenName = in.readString();
    	profileBgImageUrl = in.readString();
    	profileImage = in.readString();
    	numTweets = in.readInt();
    	followersCount = in.readInt();
    	friendsCount = in.readInt();
    	tagline = in.readString();
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeLong(uid);
		dest.writeString(screenName);
		dest.writeString(profileBgImageUrl);
		dest.writeString(profileImage);
		dest.writeInt(numTweets);
		dest.writeInt(followersCount);
		dest.writeInt(friendsCount);
		dest.writeString(tagline);
		
	}
	
	public static final Parcelable.Creator<User> CREATOR
     	= new Parcelable.Creator<User>() {
		 	public User createFromParcel(Parcel in) {
		 		return new User(in);
		 	}

		 	public User[] newArray(int size) {
		 		return new User[size];
		 	}
	 };
	
	
	
	
}
