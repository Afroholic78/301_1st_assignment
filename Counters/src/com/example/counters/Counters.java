package com.example.counters;

import java.util.Date;

public class Counters {

	private String text;
	private int count;
	private long first_timestamp;
	private long current_timestamp;
	
	
	public Counters() {
		super();
		this.count = 0;
		this.first_timestamp = System.currentTimeMillis();
	}
	public String toString(){
		return text;
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
	public long getTimestamp() {
		return first_timestamp;
	}
	public long getCurrent_timestamp() {
		return current_timestamp;
	}
	public void setCurrent_timestamp(){
		this.current_timestamp = System.currentTimeMillis();
	}
	
	public void increment(){
		this.count += 1;
		
	}
	
	public void reset(){
		this.count = 0;
		
	}
	
	
	
}
