package com.codepath.apps.sweetie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.sweetie.fragments.UserTimelineFragment;
import com.codepath.apps.sweetie.models.Tweet;
import com.codepath.apps.sweetie.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetsAdapter extends ArrayAdapter<Tweet> {
	
	private int pos;
	
	public TweetsAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		pos = position;
		
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.tweet_item, null);
		}
		
		final Tweet tweet = getItem(pos);
		
		ImageView imageView = (ImageView) view.findViewById(R.id.ivProfile);
		ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImage(), imageView);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showUser(tweet.getUser()); 
			}
		});
		
		TextView nameView = (TextView) view.findViewById(R.id.tvName);
		String formattedName = "<b>" + tweet.getUser().getName() + "</b>" + " <small><font color='#777777'>@" +
						tweet.getUser().getScreenName() + "</font></small>";
		nameView.setText(Html.fromHtml(formattedName));
		
		TextView bodyView = (TextView) view.findViewById(R.id.tvBody);
		bodyView.setText(Html.fromHtml(tweet.getBody()));
		
		TextView createdAtView = (TextView) view.findViewById(R.id.tvCreatedAt);
		String dateFormat = "EEE MMM dd HH:mm:ss ZZZZ yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		sdf.setLenient(true);
		Date createdDate = null;
		try {
			createdDate = sdf.parse(tweet.getCreatedAt());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String dateString = (String) DateUtils.getRelativeDateTimeString(view.getContext(), createdDate.getTime(), 
				DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0);
		
//		String formattedTS = " <small><font color='#777777'>" +
//				tweet.getCreatedAt() + "</font></small>";
		String formattedTS = " <small><font color='#777777'>" +
				dateString + "</font></small>";

		createdAtView.setText(Html.fromHtml(formattedTS));
	
		return view;
	}
	
	public void showUser(User user) {
		Intent i = new Intent(getContext(), ProfileActivity.class);
		i.putExtra("twitterUser", user);
		getContext().startActivity(i);
	}
}
