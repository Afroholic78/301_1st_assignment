package com.example.counters;

import java.util.ArrayList;
import java.util.Date;

import android.util.Log;

public class Counters {

	private String text;
	private int count;
	
	private ArrayList<String> hours_list= new ArrayList<String>();
	private int prev_count_hours;
	private ArrayList<String> day_list= new ArrayList<String>();
	private int prev_count_day;
	private ArrayList<String> week_list= new ArrayList<String>();
	private int prev_count_week;
	private ArrayList<String> month_list= new ArrayList<String>();
	private int prev_count_month;
	private boolean check;
	
	public Counters() {
		super();
		this.count = 0;
	}
	public String toString(){
		return text + " : " + count;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}


	
	public void increment(){
		this.count += 1;
		
	}
	
	public void reset(){
		this.count = 0;
		
	}
	public ArrayList<String> getHours() {
		return hours_list;
	}
	public void setHours(String hours) {
		check_if_exist(hours_list, hours + ":00", prev_count_hours);
		
	}
	public ArrayList<String> getDay() {
		return day_list;
	}
	public void setDay(String string) {
		check_if_exist(day_list, string, prev_count_day);
	}
	public ArrayList<String> getWeek() {
		return week_list;
	}
	public void setWeek(String week) {
		check_if_exist(week_list, week, prev_count_week);
	}
	public ArrayList<String> getMonth() {
		return month_list;
	}
	public void setMonth(String month) {
		check_if_exist(month_list, month, prev_count_month);
	}
	
	private void check_if_exist(ArrayList<String> list, String time, int prev_count){
		check = false;
		for(int i = 0; i < list.size(); i++ ){
			
			if(((((list.get(i)).split(" "))[0]) + " " +((list.get(i)).split(" "))[1]).equals(time)){
				prev_count = count;
				list.set(i, time + " " + "-" + " " + count);
				check = true;
			}
		}
		if(!check){
			int temp = count - prev_count;
			list.add(time + " " + "-" + " " + temp );
			
			
		}
		
		
	}
	
	
	
}
