package com.codepath.apps.sweetie.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.sweetie.TwitterApp;
import com.codepath.apps.sweetie.models.Tweet;
import com.codepath.apps.sweetie.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private String idString;
	private String twitterUsername = "";
	private static final int COUNT = 25;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//nullpointerhere...
		twitterUsername = getArguments().getString("username", "");
		refreshTweets(COUNT, null, twitterUsername);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getAdapter().clear();
		refreshTweets(COUNT, null, twitterUsername);
	}
	
	public static UserTimelineFragment newInstance(String username) {
		UserTimelineFragment timelineFragment = new UserTimelineFragment();
		Bundle args = new Bundle();
		args.putString("username", username);
		timelineFragment.setArguments(args);
		return timelineFragment;
		
	}
	
	public void refreshTweets(int count, String max_id, String username) {
		TwitterApp.getRestClient().getUserTimeline(count, max_id, username, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {			
				tweets = Tweet.fromJson(jsonTweets);
				Tweet lastTweet = tweets.get(tweets.size() - 1);
				idString = lastTweet.getIdString();
				getAdapter().addAll(tweets);
			}

			@Override
			public void onFailure(Throwable arg0, JSONArray arg1) {
			}
		});
	}
	
	@Override
	public void loadMoreItems() {		
		refreshTweets(COUNT, idString, twitterUsername);
	}

}
