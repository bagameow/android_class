package com.codepath.apps.basictwitter;

import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TimelineActivity extends Activity {

	private TwitterClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		client = TwitterApplication.getRestClient();
		populateTimeline();
	}
	
	public void populateTimeline() {
		client.getHomeTimeline(new JsonHttpResponseHandler() {
		});
	}
}
