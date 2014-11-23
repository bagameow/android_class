package com.y.gridimagesearch.activities;

import com.squareup.picasso.Picasso;
import com.y.gridimagesearch.R;
import com.y.gridimagesearch.R.id;
import com.y.gridimagesearch.R.layout;
import com.y.gridimagesearch.R.menu;
import com.y.gridimagesearch.models.ImageResult;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class ImageDisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		String url = getIntent().getStringExtra("url");
		ImageView ivImageResult = (ImageView)findViewById(R.id.ivImageResult);
		Picasso.with(this).load(url).into(ivImageResult);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
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
