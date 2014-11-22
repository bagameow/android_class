package com.y.gridimagesearch.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.y.gridimagesearch.R;
import com.y.gridimagesearch.R.id;
import com.y.gridimagesearch.R.layout;
import com.y.gridimagesearch.R.menu;
import com.y.gridimagesearch.adapters.ImageResultsAdapter;
import com.y.gridimagesearch.models.ImageResult;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;


public class MainActivity extends Activity {
	private EditText etQuery;
	private GridView gvResults;
	private List<ImageResult> imageResults;
	private ImageResultsAdapter aImageResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        imageResults = new ArrayList<ImageResult>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);
    }

	private void setupViews() {
		etQuery = (EditText)findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
	}
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onImageSearch(View v) {
    	String query = etQuery.getText().toString();
    	String url = "https://ajax.googleapis.com/ajax/services/search/images?q=" + query + "&v=1.0&rsz=8";
    	AsyncHttpClient client = new AsyncHttpClient();
    	client.get(url, new JsonHttpResponseHandler() {
    		@Override
    		public void onSuccess(int statusCode, Header[] headers,
    				JSONObject response) {
    			//Log.d("INFO", response.toString());
    			JSONArray imageResultsJson = null;
    			try {
					imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear();
					aImageResults.addAll(ImageResult.fromJSONArray(imageResultsJson));
				} catch (JSONException e) {
					e.printStackTrace();
				}
    			System.out.println("");
    		}
    	});
    		
//    	client.get(url, new JsonHttpResponseHandler() {
//    		@Override
//    		public void onSuccess
//    	});
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
