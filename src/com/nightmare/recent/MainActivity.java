package com.nightmare.recent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	private ColorBase colorBaseHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	
	long nowCode = System.currentTimeMillis();
	SimpleDateFormat dateformatD = new SimpleDateFormat("yyyy-MM-dd");
	String nowString = dateformatD.format(nowCode);
	
	Log.d("recent", "now : " + nowString);
	Log.d("recent", "now code : " + nowCode);
	
	String time1=nowString+" 00:00:00";
	String time2=nowString+" 23:59:59";

	ArrayList<Point> day1 = new ArrayList<Point>();
	
	
	SimpleDateFormat dateformatS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try {
		long timeCode1 = dateformatS.parse(time1).getTime()/1000;
		long timeCode2 = dateformatS.parse(time2).getTime()/1000;

		Log.d("recent", time1+":" + timeCode1);
		Log.d("recent", time2+":" + timeCode2);
	
	//查找数据库，获取前7天的数据
	
	colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
	
	SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
//  查询Book 表中所有的数据
	
	
	Cursor cursor = colorBase.rawQuery(
			"SELECT * FROM colors WHERE moment >= ? AND moment <= ?", 
			new String[]{timeCode1+"",timeCode2+""});
	//Cursor cursor = colorBase.rawQuery(
	//		"SELECT * FROM colors ", 
	//		null);

	if (cursor.moveToFirst()) {
		
		do {
			//  遍历Cursor 对象，取出数据并打印
			long moment = cursor.getInt(cursor.getColumnIndex("moment"));			
			int colorId = cursor.getInt(cursor.getColumnIndex("colorId"));
			String description = cursor.getString(cursor.getColumnIndex("description"));
			
			day1.add(new Point(moment,colorId,description));
			
			
			Log.d("recent", "time-code : " + day1.get(day1.size()-1).moment);
			Log.d("recent", "color-code : " + day1.get(day1.size()-1).colorId);
			Log.d("recent", "description : " + day1.get(day1.size()-1).description);
			
		} while (cursor.moveToNext());
	}
	
	cursor.close();
	
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
}
