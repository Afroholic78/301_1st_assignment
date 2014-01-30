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
	public ArrayList<Counters> list_counters; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		exists = false;
		list_counters = loadFromFile();
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
			
		for(int i = 0; i < list_counters.size(); ++i){

			if((list_counters.get(i)).getText().equals(message)){
				Log.d("TEST", "gets in if");
				nameview.setText((list_counters.get(i)).getText());
				cview.setText(String.valueOf((list_counters.get(i)).getCount()));
				count = list_counters.get(i);
				exists = true;
			}
		}
		
		if(!exists){
			count = new Counters();
			nameview.setText(message);
			count.setText(message);
			cview.setText(String.valueOf(count.getCount()));
			
		}
        
		
		
	}
	

    protected void onStop(){
    	super.onStop();
    	SaveInFile(count);
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
		
		
		
	}
	
	public void reset(View view) {
		
		count.reset();
		cview.setTextSize(50);
		cview.setText(String.valueOf(count.getCount()));
		
	}
	
	
	
	public void delete(View view){
		
		
	}
	private ArrayList<Counters> loadFromFile(){
		ArrayList<Counters> counters = new ArrayList<Counters>();
		try{
			FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();      
                    
            while (line != null) {
            		Counters counter = deserialize(line);
                    counters.add(counter);
                    line = in.readLine();
            }

    } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
    return counters;
		}
	// TODO ADD THE SAVE TO SOMETHING... SAVE WHEN IT IS MODIFIED AFTER CHECK IF IT ALREADY EXISTS and update timestamps
	public void SaveInFile(Counters counter){
		try{
			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
			fos.write((serialize(counter) + "\n").getBytes());
			fos.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String serialize(Counters counter){
		Gson new_item = new Gson();
		String item = new_item.toJson(counter);
		return item;
	}
	
	public static Counters deserialize(String retrieved_item){
		Gson new_item = new Gson();
		Counters counter_retrieved = new_item.fromJson(retrieved_item, Counters.class);
		return counter_retrieved;
	}
	
	
}
