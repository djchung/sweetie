package com.codepath.apps.sweetie;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.util.Log;
import com.codepath.apps.sweetie.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimeLineActivity extends Activity {
	
	private int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
		ListView lvTimeline = (ListView) findViewById(R.id.lvTweets);
		lvTimeline.setOnScrollListener(new EndlessScrollListener() {
			
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				if (count < 175) {
					count = count + 25;
					refreshTweets(count);
					
				}
			}
		});
		count = 25;
		refreshTweets(count);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}
	
	public void onCompose(MenuItem menuItem) {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivity(i);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		refreshTweets(25);
	}
	
	public void refreshTweets(int count) {
		//TODO: after refreshing, user is taken to the top - fix this
		TwitterApp.getRestClient().getHomeTimeline(count, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				ListView lvTweets = (ListView) findViewById(R.id.lvTweets);
				TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
				lvTweets.setAdapter(adapter);
			}
			
			@Override
			public void onFailure(Throwable arg0, JSONArray arg1) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getBaseContext(), "Failed to load", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
