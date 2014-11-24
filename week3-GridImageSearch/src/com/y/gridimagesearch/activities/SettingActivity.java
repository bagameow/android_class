package com.y.gridimagesearch.activities;

import com.y.gridimagesearch.R;
import com.y.gridimagesearch.R.id;
import com.y.gridimagesearch.R.layout;
import com.y.gridimagesearch.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingActivity extends Activity {
	
	private String[] cImageSizes = {"small", "medium", "large", "extra-large" };
	private String cSizeChozen = "small";
	private ArrayAdapter<String> aImageSizes;
	private Spinner spSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		aImageSizes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cImageSizes);
		
		spSize = (Spinner) findViewById(R.id.spSize);
		spSize.setAdapter(aImageSizes);
		spSize.setOnItemSelectedListener(new OnItemSelectedListener(){

		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
		    	cSizeChozen = cImageSizes[position];
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		       // TODO Auto-generated method stub
		    }
		});
	}
	
	public void onSaveClick(View v) {
		Intent i = new Intent(this, MainActivity.class);
		i.putExtra("size", cSizeChozen);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
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
