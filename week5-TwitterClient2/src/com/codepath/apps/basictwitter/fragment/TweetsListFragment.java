package com.codepath.apps.basictwitter.fragment;

import java.util.ArrayList;
import java.util.List;


import com.codepath.apps.basictwitter.EndlessScrollListener;
import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.TweetArrayAdapter;
import com.codepath.apps.basictwitter.TwitterApplication;
import com.codepath.apps.basictwitter.TwitterClient;
import com.codepath.apps.restclienttemplate.models.Tweet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TweetsListFragment extends Fragment {
	private List<Tweet> tweets;
	private ListView lvTweets;
	private TweetArrayAdapter aTweets;
	protected TwitterClient client;

	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);
		lvTweets = (ListView)v.findViewById(R.id.lvTweets);
		lvTweets.setAdapter(aTweets);
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// Triggered only when new data needs to be appended to the list
				// Add whatever code is needed to append new items to your AdapterView
				customLoadMoreDataFromApi(aTweets); 
			}
		});
		
		
		return v;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		client = TwitterApplication.getRestClient();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(getActivity(),  tweets);


	}
	
	public TweetArrayAdapter getAdapter() {
		return aTweets;
	}
	
	public void addAll(List<Tweet> tweets) {
		aTweets.addAll(tweets);
	}

	
	protected void customLoadMoreDataFromApi(android.widget.ArrayAdapter<Tweet> adapter) {
		populateTimeline(adapter);;
	}
	
	public void populateTimeline(ArrayAdapter<Tweet> adpater) {
		
	}
		
	

	
}