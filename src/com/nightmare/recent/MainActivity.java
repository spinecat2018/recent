package com.nightmare.recent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private ColorBase colorBaseHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	
	long nowCode = System.currentTimeMillis()/1000;
	
	long todayHead = findRange(nowCode).start;
	long todayEnd = findRange(nowCode).end;
	
	//SimpleDateFormat dateformatD = new SimpleDateFormat("yyyy-MM-dd");
	//String nowString = dateformatD.format(nowCode);
	//Log.d("recent", "now : " + nowString);
	Log.d("recent", "now code : " + nowCode);
	
	ArrayList<Point> day1 = new ArrayList<Point>();
	
	Log.d("recent", "today from:" +todayHead);
	Log.d("recent", "to:"+ todayEnd);
	
	//查找数据库，获取前7天的数据
	
	colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
	
	SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
//  查询Book 表中所有的数据
	
	
	Cursor cursor = colorBase.rawQuery(
			"SELECT * FROM colors WHERE moment >= ? AND moment <= ?", 
			new String[]{todayHead+"",todayEnd+""});


	if (cursor.moveToFirst()) {
		
		do {
			//  遍历Cursor 对象，取出数据并打印
			long moment = cursor.getInt(cursor.getColumnIndex("moment"));			
			int colorId = cursor.getInt(cursor.getColumnIndex("colorId"));
			String description = cursor.getString(cursor.getColumnIndex("description"));
			
			day1.add(new Point(moment,colorId,description));
			
			//Log.d("recent", "time-code : " + day1.get(day1.size()-1).moment);
			Log.d("recent", "color-code : " + day1.get(day1.size()-1).colorId);
			//Log.d("recent", "description : " + day1.get(day1.size()-1).description);
			
		} while (cursor.moveToNext());
	}
	
	cursor.close();
	
	//divide pointlist to am and pm
	SplitList sl=new SplitList(day1);
	
	
	SomePoints sp0 = new SomePoints(getBaseContext(), null, sl.amList);//新建控件
	SomePoints sp1 = new SomePoints(getBaseContext(), null, sl.pmList);

	
	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( //格式
            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); 
	
	sp0.setLayoutParams(lp);
	sp1.setLayoutParams(lp);
	
	LinearLayout layoutAm = (LinearLayout) findViewById(R.id.am_area);//找到 主页上的父控件
	LinearLayout layoutPm = (LinearLayout) findViewById(R.id.pm_area);
	layoutAm.addView(sp0);//插入新的控件
	layoutPm.addView(sp1);
	
	
	
	}
	
	
	public static TimeRange findRange(long timeCode){//get the day's range from timecode(10-bit)
		Log.d("recent","find");

		TimeRange tr = new TimeRange();
		
		SimpleDateFormat dateformatD = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateformatD.format(timeCode*1000);
		
		String time1=dateString+" 00:00:00";
		String time2=dateString+" 23:59:59";
		
		SimpleDateFormat dateformatS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			tr.start = dateformatS.parse(time1).getTime()/1000;
			tr.end = dateformatS.parse(time2).getTime()/1000;
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		Log.d("recent","out from find--"+ tr.start);

		return tr;
	}
	
	
	
}


