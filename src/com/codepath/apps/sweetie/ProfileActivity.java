package com.codepath.apps.sweetie;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.sweetie.fragments.MentionsFragment;
import com.codepath.apps.sweetie.fragments.UserTimelineFragment;
import com.codepath.apps.sweetie.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {
	
	User twitterUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		twitterUser = getIntent().getParcelableExtra("twitterUser");		
		loadProfileInfo();
	}

	private void loadProfileInfo() {
		
		if (twitterUser != null) {
			populateProfileHeader(twitterUser);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			UserTimelineFragment fragment = UserTimelineFragment.newInstance(twitterUser.getScreenName());
			ft.replace(R.id.tweets_placeholder, fragment);
			ft.commit();
			
		} else {
			TwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject json) {
					twitterUser = User.fromJson(json);
					getActionBar().setTitle("@" + twitterUser.getScreenName());
					populateProfileHeader(twitterUser);
					
					FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
					UserTimelineFragment fragment = UserTimelineFragment.newInstance(twitterUser.getScreenName());
					ft.replace(R.id.tweets_placeholder, fragment);
					ft.commit();
				}
			});
		}
		
	}

	protected void populateProfileHeader(User u) {
		  TextView tvName = (TextView) findViewById(R.id.tvName);
		  TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
		  TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		  TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		  ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		  
		  tvName.setText(u.getName());
		  tvTagline.setText(u.getTagline());
		  tvFollowers.setText(u.getFollowersCount() + " Followers");
		  tvFollowing.setText(u.getFriendsCount() + " Following");
		  ImageLoader.getInstance().displayImage(u.getProfileImage(), ivProfileImage);
	}

}
