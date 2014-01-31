package com.example.counters;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewcounternameActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.counters.MESSAGE";
	//use EXTRA MESSAGE to retrieve counter name from previous activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newcountername);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.newcountername, menu);
		return true;
	}
	//when button is pressed make sure it isnt an empty string, if it is send a toast otherwise start Newcounter activity and send the new counter's name
	public void sendcounter(View view) {
	    // Do something in response to new counter button
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		if(message == null || message.trim().equals("")){
			Context context = getApplicationContext();
			CharSequence text = "Input non-empty string!";
			int duration = 1;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		else{
		Intent intent = new Intent(this, NewCounter.class);
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		}
	}

}
