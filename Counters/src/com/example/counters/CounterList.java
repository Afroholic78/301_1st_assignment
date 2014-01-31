package com.example.counters;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


public class CounterList {
	public static ArrayList<Counters> list_counters; // = loadFromFile();
	public static final String FILENAME = "file.sav";
	
	public CounterList(Activity mainactivity){
		list_counters = loadFromFile(mainactivity);
	}
	
	//Load the list of counters into an ArrayList<counters> named list_counters
	private ArrayList<Counters> loadFromFile(Activity mainactivity){
		ArrayList<Counters> counters = new ArrayList<Counters>();
		try{
			FileInputStream fis = mainactivity.openFileInput(FILENAME);
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
	
	//Deserialize for loading
	private static Counters deserialize(String retrieved_item){
		Gson new_item = new Gson();
		Counters counter_retrieved = new_item.fromJson(retrieved_item, Counters.class);
		return counter_retrieved;
	}
	//Save the array list to FILENAME
	public static void SaveInFile(ArrayList<Counters> counter, Activity newcounteractivity){
		try{
			FileOutputStream fos = newcounteractivity.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			Counters count;
			for(int i = 0; i < list_counters.size(); ++i){
		    	count = list_counters.get(i);
		    	fos.write((serialize(count) + "\n").getBytes());	    	
			
			}
			fos.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Serialize for saving purposes
	private static String serialize(Counters counter){
		Gson new_item = new Gson();
		String item = new_item.toJson(counter);
		return item;
	}
	//Check if a certain counter exists within the list if it does delete it since it will be re-added later
	public static void Already_exist_check(Counters counter){
		for(int i = 0; i < list_counters.size(); i++){
			if((list_counters.get(i)).getText().equals(counter.getText())){
				list_counters.remove(i);
			}
		}
	}
	
}

