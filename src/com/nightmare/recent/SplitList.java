package com.nightmare.recent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.util.Log;

public class SplitList {
	
	ArrayList<Point> amList=new ArrayList<Point>();
	ArrayList<Point> pmList=new ArrayList<Point>();
	
	public SplitList(ArrayList<Point> list){
		Log.d("recent","splitlist");

		TimeRange tr = new TimeRange();
		
		if(!list.isEmpty()){
			
		
		
		long timeCode = list.get(0).moment; 
		
		tr = com.nightmare.recent.MainActivity.findRange(timeCode);
		
		long mid= (tr.start+tr.end+1)/2;
		
		for(int i=0;i<list.size();i++){
			Log.d("recent","in for"+list.size());
			Log.d("recent",i+"");
			if(list.get(i).moment<mid){
				Log.d("recent","to add amlist");
				Point p=list.get(i);
				Log.d("recent","adding amlist");
				amList.add(p);
				
				Log.d("recent","add amlist");
			}else {
				Log.d("recent","add pmlist");
				pmList.add(list.get(i));
			}
		}
		}
		Log.d("recent","out from split");

	}
	
}
	
	
