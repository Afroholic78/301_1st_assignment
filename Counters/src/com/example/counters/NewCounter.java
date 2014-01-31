package com.example.counters;





import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class NewCounter extends Activity {
	private Counters count;
	private TextView cview; 
	public static final String FILENAME = "file.sav";
	private boolean exists; 
	public ArrayList<Counters> list_counters2; 
	private Date date = new Date();
	private Calendar calendar2 = GregorianCalendar.getInstance();
	public final static String EXTRA_MESSAGE = "com.example.counters.MESSAGE";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		exists = false;
		list_counters2 = CounterList.list_counters; 
		Intent intent = getIntent();
        String message = intent.getStringExtra(NewcounternameActivity.EXTRA_MESSAGE);
        setContentView(R.layout.activity_new_counter);
        cview = (TextView) findViewById(R.id.numbody);
		EditText nameview = (EditText) findViewById(R.id.body);
		nameview.setTextSize(50);
		cview.setTextSize(50);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
			
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
		calendar2.setTime(date);
		count.increment();
		cview.setText(String.valueOf(count.getCount()));
		
		count.setHours((Integer.toString(calendar2.get(Calendar.MONTH) +1 )) + "/" + (Integer.toString(calendar2.get(Calendar.DAY_OF_MONTH))) + " " +(Integer.toString(calendar2.get(Calendar.HOUR_OF_DAY)))  );
		count.setMonth(Integer.toString(calendar2.get(Calendar.MONTH) +1 ));
		count.setWeek(Integer.toString(calendar2.get(Calendar.WEEK_OF_MONTH)));
		count.setDay((Integer.toString(calendar2.get(Calendar.MONTH) +1 )) + "/" + Integer.toString((calendar2.get(Calendar.DAY_OF_MONTH))));
		
		
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
	
	public void change_name(View view){
		EditText editText = (EditText) findViewById(R.id.body);
		String message = editText.getText().toString();
		CounterList.Already_exist_check(count);
		count.setText(message);
		list_counters2.add(count);
		CounterList.SaveInFile(list_counters2, this);
		
	}
	
	public void stats(View view) {
	    // Do something in response to stats button
		EditText editText = (EditText) findViewById(R.id.body);
		String message = editText.getText().toString();
		Intent intent = new Intent(this, DisplayStats.class);
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	
	
}
