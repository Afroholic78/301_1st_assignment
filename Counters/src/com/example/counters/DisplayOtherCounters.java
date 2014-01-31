package com.example.counters;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DisplayOtherCounters extends ListActivity {
	public final static String EXTRA_MESSAGE = "com.example.counters.MESSAGE";
	private ListView oldCounterList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayList<Counters> count = CounterList.list_counters;	
		setListAdapter(new ArrayAdapter<Counters>(this,
                android.R.layout.simple_list_item_1, count));
		//setContentView(R.layout.activity_display_other_counters);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//oldCounterList = (ListView) findViewById(R.id.othercounters);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		try{
		final ArrayList<Counters> count = CounterList.list_counters;
		Counters current = count.get(position);
		Class ourClass = Class.forName("com.example.counters.NewCounter");
		Intent ourIntent = new Intent(this, ourClass);
		ourIntent.putExtra(EXTRA_MESSAGE, current.getText());
		startActivity(ourIntent);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

	//TODO implement sorting
	public void Order(View view) {
	    // Do something in response to Order counters button
		final ArrayList<Counters> count = CounterList.list_counters;
		
		for(int i = 0; i < count.size(); ++i){
			
			count.get(i).getCount();
		}
		
	}

	protected void onStart(){
		super.onStart();
        
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
		getMenuInflater().inflate(R.menu.display_other_counters, menu);
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
	

	
	

}
