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
import android.widget.TextView;

public class SettingActivity extends Activity {
	
	//size
	private String[] cImageSizes = {"n/a", "small", "medium", "large", "xlarge" };
	private String cSizeChozen = "";
	private Spinner spSize;
	private ArrayAdapter<String> aImageSizes;
	
	//color
	private String[] cImageColors = {"n/a", "black","blue", "brown", "gray" ,"green", "orange", "pink",  "purple",
			                          "red", "teal",  "white",  "yellow" };
	private String cColorChozen = "";
	private Spinner spColor;
	private ArrayAdapter<String> aImageColors;
	
	//type
	private String[] cImageTypes = { "n/a", "face", "photo", "clipart", "lineart"};
	private String cTypeChozen = "";
	private Spinner spType;
	private ArrayAdapter<String> aImageTypes;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initSpinners();
	}
	
	private void initSpinners() {
		//size
		aImageSizes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cImageSizes);
		spSize = (Spinner) findViewById(R.id.spSize);
		spSize.setAdapter(aImageSizes);
		spSize.setOnItemSelectedListener(new OnItemSelectedListener(){

		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
		    	if(position > 0) 
		    		cSizeChozen = cImageSizes[position];
		    	else 
		    		cSizeChozen = null;
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		       // TODO Auto-generated method stub
		    }
		});
		
		//color
		aImageColors = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cImageColors);
		spColor = (Spinner) findViewById(R.id.spColor);
		spColor.setAdapter(aImageColors);
		spColor.setOnItemSelectedListener(new OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
				if(position > 0)
					cColorChozen = cImageColors[position];
				else 
					cColorChozen = null;
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		//type
		aImageTypes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cImageTypes);
		spType = (Spinner) findViewById(R.id.spType);
		spType.setAdapter(aImageTypes);
		spType.setOnItemSelectedListener(new OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
				if (position > 0)
					cTypeChozen = cImageTypes[position];
				else 
					cTypeChozen = null;
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
		i.putExtra("color", cColorChozen);
		i.putExtra("type", cTypeChozen);
		TextView tvSite = (TextView)findViewById(R.id.etSite);
		if (tvSite.getText().length() > 0) {
			i.putExtra("site", tvSite.getText().toString());
		}
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
