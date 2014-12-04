package com.codepath.apps.basictwitter;

import java.util.Date;
import java.util.List;


import com.codepath.apps.restclienttemplate.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
	
	public String nextMaxId = null;

	public TweetArrayAdapter(Context context, List<Tweet> tweets) {
		super(context,0, tweets);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 // Get the data item for this position
		Log.d("debug", String.valueOf(position));
	       Tweet tweet = getItem(position);    
	       // Check if an existing view is being reused, otherwise inflate the view
	       View v;
	       if (convertView == null) {
	          v = LayoutInflater.from(getContext()).inflate(R.layout.tweet_item, parent, false);
	       } else {
	    	   v = convertView;
	       }
	       // Lookup view for data population
	       //Log.d("debug", String.valueOf(tweet.getUid()));
	       if (nextMaxId == null) {
	    	   Log.d("debug", "original=" + nextMaxId + ", new one=" + tweet.getUid());
	    	   nextMaxId = String.valueOf(tweet.getUid());
	       } else  {
	    	   long o = Long.valueOf(nextMaxId);
	    	   long n = Long.valueOf(tweet.getUid());
	    	   if (n < o) {
	    		   Log.d("debug", "original=" + nextMaxId + ", new one=" + tweet.getUid());
	    		   nextMaxId = String.valueOf(tweet.getUid());
	    	   }
	       }
	       
	       ImageView ivProfileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
	       TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
	       TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
	       TextView tvRelativeTime = (TextView) v.findViewById(R.id.tvRelativeTime);
	       ivProfileImage.setImageResource(android.R.color.transparent);
	       ImageLoader imageLoader = ImageLoader.getInstance();
	       imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImage);
	       // Populate the data into the template view using the data object
	       tvUserName.setText(tweet.getUser().getName());
	       tvBody.setText(tweet.getBody());
	       tvRelativeTime.setText(convertRelativeTime(tweet.getCreateAt()));
	       // Return the completed view to render on screen
	       return v;
	}
	
	String convertRelativeTime(String past) {
		Date now = new Date(past);
		String s = DateUtils.getRelativeDateTimeString(
		getContext(), // Suppose you are in an activity or other Context subclass
		now.getTime(), // The time to display
		DateUtils.MINUTE_IN_MILLIS, // The resolution. This will display only 
		// minutes (no "3 seconds ago") 
		DateUtils.WEEK_IN_MILLIS, // The maximum resolution at which the time will switch 
		// to default date instead of spans. This will not 
		// display "3 weeks ago" but a full date instead
		0).toString(); // Eventual flags
		return s;
	}
}
