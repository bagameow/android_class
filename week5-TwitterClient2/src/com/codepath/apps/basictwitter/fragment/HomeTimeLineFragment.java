package com.codepath.apps.basictwitter.fragment;


import org.json.JSONArray;

import com.codepath.apps.basictwitter.TweetArrayAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class HomeTimeLineFragment extends TweetsListFragment {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		populateTimeline(getAdapter());
	}
	
	public void populateTimeline(ArrayAdapter<Tweet> adpater) {
		client.getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, JSONArray jsonArray) {
				getAdapter().addAll(Tweet.fromJsonArray(jsonArray));
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				Log.d("debug",arg0.toString());
				Log.d("debug",arg1.toString());
			}
		}, ((TweetArrayAdapter)adpater).nextMaxId);
	}
	

	

	
}
