package com.example.counters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;



import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class DisplayOtherCounters extends ListActivity {
	//EXTRA_MESSAGE is used to pass a string from one activity to another
	public final static String EXTRA_MESSAGE = "com.example.counters.MESSAGE";
	private ListView oldCounterList;
	private ArrayAdapter<Counters> adapter;
	
	//Set the button and list layouts on Create, if the order button is clicked call the order function
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_other_counters);		
		
		Button orderButton = (Button) findViewById(R.id.order);
		oldCounterList = (ListView) findViewById(android.R.id.list);
		
		orderButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);

				Order();
			}
		});

		// Show the Up button in the action bar.
		setupActionBar();
		
	}
	//Set the list to project the list of counters
	@Override
	protected void onStart() {
		
		super.onStart();
		ArrayList<Counters> count = CounterList.list_counters;
		adapter = new ArrayAdapter<Counters>(this,
				android.R.layout.simple_list_item_1, count);
		oldCounterList.setAdapter(adapter);
	}
	
	//Set a listener to wait for a click on a list item and then associate it with a counter name and pass the name to the next activity
	//in this case NewCounter
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
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
	
	
	//Order is based on selection sort. Finds the highest count and appends it to an empty list and do so for each element (runtime n^2)
	// once it is all sorted make the list of counters equal to the sorted list, reset the listview display to match the sorted list and save it to memory 
	public void Order() {
	    
		// Do something in response to Order counters button
		final ArrayList<Counters> count = CounterList.list_counters;
		ArrayList<Counters> new_count = new ArrayList<Counters>();
		ArrayList<Counters> new_count_copy = (ArrayList<Counters>) CounterList.list_counters.clone();
		Counters count_max = new Counters();
		
		int position = -2;
		int len = count.size();
		int len2 = new_count_copy.size();
		
		for(int c = 0; c < len; c++){
		
			int max = -1;
		for(int i = 0; i < len2; i++){
		
			if(max < new_count_copy.get(i).getCount()){
				max = new_count_copy.get(i).getCount();
				count_max = new_count_copy.get(i);
				position = i;
			}
		}
		new_count.add(count_max);
		new_count_copy.remove(position);
		--len2;
		}
		
		CounterList.list_counters = new_count;
		CounterList.SaveInFile(CounterList.list_counters, this);
		
		adapter = new ArrayAdapter<Counters>(this,
				android.R.layout.simple_list_item_1, CounterList.list_counters);
		oldCounterList.setAdapter(adapter);
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
