package com.yahoo.tipcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void calcTip(View v) {
    	//percentage.
    	String percent = ((Button)v).getText().toString();
    	TextView tvResult = (TextView)findViewById(R.id.tvResult);
    	int parsedPercent = parsePercentString(percent);
    	
    	//total amount
    	TextView evTotalAmount = (TextView) findViewById(R.id.etTotalAmount);
    	String sAmount = evTotalAmount.getText().toString();
    	
    	
    	Float f = Float.parseFloat(sAmount);
    	f = f * parsedPercent / 100;
    	tvResult.setText("Tip is:    $" + String.format("%.2f", f));
    }
    
    protected int parsePercentString(String s) {
 
        Pattern pattern = Pattern.compile("\\d{1,3}");

        Matcher matcher = pattern.matcher(s);
        while(matcher.find()) {
            return Integer.valueOf(matcher.group()).intValue();
        }
        throw new IllegalArgumentException("can't parse percentage " + s);
    }
    
}
