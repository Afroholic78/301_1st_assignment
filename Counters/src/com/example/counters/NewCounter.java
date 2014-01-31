package com.example.counters;





import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;


public class NewCounter extends Activity {
	private Counters count;
	private TextView cview; 
	public static final String FILENAME = "file.sav";
	private boolean exists; 
	public ArrayList<Counters> list_counters2; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		exists = false;
		list_counters2 = CounterList.list_counters; 
		Intent intent = getIntent();
        String message = intent.getStringExtra(NewcounternameActivity.EXTRA_MESSAGE);
        setContentView(R.layout.activity_new_counter);
        cview = (TextView) findViewById(R.id.numbody);
		TextView nameview = (TextView) findViewById(R.id.body);
		nameview.setTextSize(50);
		cview.setTextSize(50);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Log.d("TEST", "hello");
			
		for(int i = 0; i < list_counters2.size(); ++i){

			if((list_counters2.get(i)).getText().equals(message)){
				Log.d("TEST", "gets in if");
				nameview.setText((list_counters2.get(i)).getText());
				cview.setText(String.valueOf((list_counters2.get(i)).getCount()));
				count = list_counters2.get(i);
				list_counters2.remove(i);
				exists = true;
			}
		}
		
		if(!exists){
			count = new Counters();
			nameview.setText(message);
			count.setText(message);
			cview.setText(String.valueOf(count.getCount()));
			
		}
		
		list_counters2.add(count);
    	CounterList.SaveInFile(list_counters2, this);
        
		
		
	}
	

    

    

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_counter, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called when the user clicks the Send button*/ 
	public void add(View view) {
		count.increment();
		cview.setText(String.valueOf(count.getCount()));
		
		CounterList.Already_exist_check(count);
		list_counters2.add(count);
		CounterList.SaveInFile(list_counters2, this);
		
	}
	
	public void reset(View view) {
		
		count.reset();
		cview.setText(String.valueOf(count.getCount()));
		
		CounterList.Already_exist_check(count);
		list_counters2.add(count);
		CounterList.SaveInFile(list_counters2, this);
	}
	
	
	
	public void delete(View view){
		
		for(int i = 0; i < list_counters2.size(); i++){
			if((list_counters2.get(i)).getText().equals(count.getText())){
				list_counters2.remove(i);
			}
		}
		
		CounterList.SaveInFile(list_counters2, this);
		NavUtils.navigateUpFromSameTask(this);
		
	}
	
	
}
