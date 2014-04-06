package com.codepath.apps.sweetie.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.sweetie.EndlessScrollListener;
import com.codepath.apps.sweetie.R;
import com.codepath.apps.sweetie.TweetsAdapter;
import com.codepath.apps.sweetie.models.Tweet;

public class TweetsListFragment extends Fragment {
	TweetsAdapter adapter;
	ListView lv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragments_tweets_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		adapter = new TweetsAdapter(getActivity(), tweets);
		lv = (ListView) getActivity().findViewById(R.id.lvTweets);
		lv.setAdapter(adapter);	
		lv.setOnScrollListener(new EndlessScrollListener() {
			
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				loadMoreItems();				
			}
		});
	}
	
	public TweetsAdapter getAdapter() {
		return adapter;
	}
	
	public void loadMoreItems(){
		
	}

}
