package com.codepath.apps.sweetie.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;

import com.codepath.apps.sweetie.TwitterApp;
import com.codepath.apps.sweetie.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private String idString;
	private static final int COUNT = 25;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);				
		refreshMentions(COUNT, null);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getAdapter().clear();
		refreshMentions(COUNT, null);
	}
	
	public void refreshMentions(int count, String max_id) {
		TwitterApp.getRestClient().getUserTimeline(count, max_id, new JsonHttpResponseHandler() {
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
		refreshMentions(COUNT, idString);
	}

}
