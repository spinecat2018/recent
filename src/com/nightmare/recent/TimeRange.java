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
	public TimeRange yesterday(){
		TimeRange tr= new TimeRange();
		tr.start-=24*60*60;
		tr.end-=24*60*60;
		return tr;
	}
	public TimeRange tomorrow(){
		TimeRange tr= new TimeRange();
		tr.start+=24*60*60;
		tr.end+=24*60*60;
		return tr;
	}
}
