package com.y.gridimagesearch.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.y.gridimagesearch.EndlessScrollListener;
import com.y.gridimagesearch.R;
import com.y.gridimagesearch.R.id;
import com.y.gridimagesearch.R.layout;
import com.y.gridimagesearch.R.menu;
import com.y.gridimagesearch.adapters.ImageResultsAdapter;
import com.y.gridimagesearch.models.ImageResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private EditText etQuery;
	private GridView gvResults;
	private List<ImageResult> imageResults;
	private ImageResultsAdapter aImageResults;
	private String cSizeChozen;
	private String cColorChozen;
	private String cTypeChozen;
	private String cSiteChozen;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        imageResults = new ArrayList<ImageResult>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);
        gvResults.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
	            customLoadMoreDataFromApi(page); 
			}
		});
        
        cSizeChozen = getIntent().getStringExtra("size");
        cColorChozen = getIntent().getStringExtra("color");
        cTypeChozen = getIntent().getStringExtra("type");
        cSiteChozen = getIntent().getStringExtra("site");
    }

	protected void customLoadMoreDataFromApi(int page) {
		
		String query = etQuery.getText().toString();
		String attr = composeUrlAttribute();
		Log.d("debug", "url attr = " + attr);
    	String url = "https://ajax.googleapis.com/ajax/services/search/images?q=" + query + "&v=1.0&rsz=8&start=" + page * 8 + attr;
    	AsyncHttpClient client = new AsyncHttpClient();
    	client.get(url, new JsonHttpResponseHandler() {
    		@Override
    		public void onSuccess(int statusCode, Header[] headers,
    				JSONObject response) {
    			//Log.d("INFO", response.toString());
    			JSONArray imageResultsJson = null;
    			try {
					imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
					//imageResults.clear();
					aImageResults.addAll(ImageResult.fromJSONArray(imageResultsJson));
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    	});
		
		
	}
	
	private String composeUrlAttribute() {
		StringBuffer sb = new StringBuffer();
		if (cSizeChozen != null && cSizeChozen.length() > 0) {
			sb.append("&imgsz=" + cSizeChozen);
		}
		if (cColorChozen != null && cColorChozen.length() > 0) {
			sb.append("&imgcolor=" + cColorChozen);
		}
		if (cTypeChozen != null && cTypeChozen.length() > 0) {
			sb.append("&imgtype=" + cTypeChozen);
		}
		if (cSiteChozen != null && cSiteChozen.length() > 0) {
			sb.append("&as_sitesearch=" + cSiteChozen);
		}
		return sb.toString();
	}

	private void setupViews() {
		etQuery = (EditText)findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this, ImageDisplayActivity.class);
				ImageResult result = imageResults.get(position);
				intent.putExtra("url", result.fullUrl);
				startActivity(intent);
			}
		});
	}
    
	
	public void onSettingAction(MenuItem mi) {
		Intent intent = new Intent(MainActivity.this, SettingActivity.class);
		startActivity(intent);
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onImageSearch(View v) {
    	String query = etQuery.getText().toString();
    	String url = "https://ajax.googleapis.com/ajax/services/search/images?q=" + query + "&v=1.0&rsz=8" + composeUrlAttribute();
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
