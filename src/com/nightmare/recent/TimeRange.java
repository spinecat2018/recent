package com.nightmare.recent;

public class TimeRange {
	long start,end;
	public TimeRange(){
		start=0;
		end=0;
	}
	public TimeRange(long a,long b){
		start=a;
		end=b;
	}
	public TimeRange yesterday(TimeRange today){
		TimeRange tr= new TimeRange();
		tr.start=today.start-24*60*60;
		tr.end=today.end-24*60*60;
		return tr;
	}
	
}
