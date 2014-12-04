package com.codepath.apps.basictwitter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.codepath.apps.basictwitter.fragment.HomeTimeLineFragment;
import com.codepath.apps.basictwitter.fragment.MentionsTimelineFragment;
import com.codepath.apps.basictwitter.fragment.TweetsListFragment;
import com.codepath.apps.basictwitter.listeners.FragmentTabListener;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TimelineActivity extends FragmentActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupTabs();
	}
	
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tab1 = actionBar
			.newTab()
			.setText("Home")
			.setIcon(R.drawable.ic_name)
			.setTag("HomeTimeLineFragment")
			.setTabListener(
				new FragmentTabListener<HomeTimeLineFragment>(R.id.flContainer, this, "home",
								HomeTimeLineFragment.class));
		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);
		Tab tab2 = actionBar
			.newTab()
			.setText("Mentions")
			.setIcon(R.drawable.ic_mentions)
			.setTag("MentionsTimelineFragment")
			.setTabListener(
			    new FragmentTabListener<MentionsTimelineFragment>(R.id.flContainer, this, "mention",
								MentionsTimelineFragment.class));

		actionBar.addTab(tab2);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	
	public void onComposeAction(MenuItem menu) {
		Intent i = new Intent(this,ComposeActivity.class);
		startActivity(i);
	}
	
	public void onProfileView(MenuItem menu) {
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);
	}

}
