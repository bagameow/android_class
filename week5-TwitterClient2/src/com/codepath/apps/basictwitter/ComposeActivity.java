package com.codepath.apps.basictwitter;

import org.json.JSONArray;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.compose, menu);
//		return true;
//	}
	
	public void onSend(View v) {
		EditText etMessage = (EditText) findViewById(R.id.etMessage);
		String message = etMessage.getText().toString();
		composeToTwitter(message);
		etMessage.setText("");
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
	}

	private void composeToTwitter(String message) {
		TwitterClient client = new TwitterClient(this);
		client.newMessage(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int arg0, JSONArray jsonArray) {
				Log.d("debug", "success -> " + jsonArray.toString());
				
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				Log.d("debug",arg0.toString());
				Log.d("debug",arg1.toString());
			}
			
		}, message);
	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
