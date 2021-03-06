package com.example.counters;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		CounterList list = new CounterList(this);
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	//when clicked go to othercounter activity that displays the other counters
	public void othercounter(View view) {
	    // Do something in response to other counters button
		Intent intent = new Intent(this, DisplayOtherCounters.class);
		startActivity(intent);
	}
	
	public void stats(View view) {
	    // Do something in response to stats button
		Intent intent = new Intent(this, DisplayStats.class);
		startActivity(intent);
	}
	//when clicked add a new counter
	public void new_counter(View view) {
	    // Do something in response to new counter button
		Intent intent = new Intent(this, NewcounternameActivity.class);
		startActivity(intent);
	}
	


}
