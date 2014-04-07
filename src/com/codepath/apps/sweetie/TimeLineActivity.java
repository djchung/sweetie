package com.codepath.apps.sweetie;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.sweetie.fragments.HomeTimeLineFragment;
import com.codepath.apps.sweetie.fragments.MentionsFragment;
import com.codepath.apps.sweetie.models.User;

public class TimeLineActivity extends FragmentActivity implements TabListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
		setupNavigationTabs();
	}

	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab tabHome = actionBar.newTab().setText("Home").setTag("HomeTimelineFragment")
				.setTabListener(this);
		Tab tabMentions = actionBar.newTab().setText("Mentions").setTag("MentionsFragment")
				.setTabListener(this);
		
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);		
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
	
	public void onProfileView(MenuItem menuItem) {
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		if (tab.getTag() == "HomeTimelineFragment") {
			fts.replace(R.id.frameContainer, new HomeTimeLineFragment());
		} else {
			fts.replace(R.id.frameContainer, new MentionsFragment());
		}
		fts.commit();		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}