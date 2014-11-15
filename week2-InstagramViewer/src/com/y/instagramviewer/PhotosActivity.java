package com.y.instagramviewer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class PhotosActivity extends Activity {
	private static String CLIENT_ID = "8b438327d1c544aaa9022564ff21dcc9";
	private List<InstagramPhoto> photos;
	private InstagramPhotosAdapter aPhotos;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        fetchPopularPhotos();
    }


    private void fetchPopularPhotos() {
    	photos = new ArrayList<InstagramPhoto>();
    	aPhotos = new InstagramPhotosAdapter(this, photos);
    	ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);
    	lvPhotos.setAdapter(aPhotos);
    	String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

    	AsyncHttpClient client = new AsyncHttpClient();
    	client.get(url, new JsonHttpResponseHandler(){
    		@Override
    		public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONObject response) {
    			Log.i("INFO", response.toString());
    			
    			aPhotos.clear();
    			try {
    				JSONArray photosJson = response.getJSONArray("data");
    				for (int i=0; i < photosJson.length(); i++) {
    					JSONObject photoJson = photosJson.getJSONObject(i);
    					InstagramPhoto photo = new InstagramPhoto();
    					photo.username = photoJson.getJSONObject("user").getString("username");
    					photo.caption = photoJson.getJSONObject("caption").getString("text");
    					photo.imageUrl = photoJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
    					photo.imageHeight = photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
    					photo.likesCount = photoJson.getJSONObject("likes").getInt("count");
    					photos.add(photo);
    					Log.i("INFO", photo.toString());
    				}
    			} catch (JSONException e ) {
    				e.printStackTrace();
    			}
    			aPhotos.notifyDataSetChanged();
    			
    		};
    		
    		@Override
    		public void onFailure(int statusCode, org.apache.http.Header[] headers, String responseString, Throwable throwable) {
    			Log.i("INFO", responseString);
    		};
    	}
    	);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photos, menu);
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
