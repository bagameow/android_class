package com.codepath.apps.basictwitter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TimelineActivity extends Activity {

	private TwitterClient client;
	private List<Tweet> tweets;
	private ListView lvTweets;
	private ArrayAdapter<Tweet> aTweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		client = TwitterApplication.getRestClient();
		tweets = new ArrayList<Tweet>();
		lvTweets = (ListView)findViewById(R.id.lvTweets);
		aTweets = new TweetArrayAdapter(this,  tweets);
		lvTweets.setAdapter(aTweets);
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	            // Triggered only when new data needs to be appended to the list
	            // Add whatever code is needed to append new items to your AdapterView
	            customLoadMoreDataFromApi(aTweets); 
	        }
	        });
		populateTimeline(aTweets);
	}
	
	protected void customLoadMoreDataFromApi(ArrayAdapter<Tweet> adapter) {
		populateTimeline(adapter);
	}

	public void populateTimeline(ArrayAdapter<Tweet> adpater) {
		client.getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, JSONArray jsonArray) {
				aTweets.addAll(Tweet.fromJsonArray(jsonArray));
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				Log.d("debug",arg0.toString());
				Log.d("debug",arg1.toString());
			}
		}, ((TweetArrayAdapter)adpater).nextMaxId);
	}
}
