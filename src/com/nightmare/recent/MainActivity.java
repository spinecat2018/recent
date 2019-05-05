package com.nightmare.recent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ColorBase colorBaseHelper;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		Log.d("recent", "oncreate");

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("recent", "onResume");
//清空fix_area和days_area
		LinearLayout fixLine = (LinearLayout) findViewById(R.id.fix_area);
		LinearLayout daysArea = (LinearLayout) findViewById(R.id.days_area);
		fixLine.removeAllViews();
		daysArea.removeAllViews();
//获取当天数据，存放在list day1  
		//获取当前秒数
		long nowCode;	
		nowCode = System.currentTimeMillis()/1000;
		//转化成当天秒范围
		long todayHead,todayEnd;
		todayHead = findRange(nowCode).start;
		todayEnd = findRange(nowCode).end;	
		Log.d("recent", ""+ nowCode +"-->"+todayHead+"-"+todayEnd);
		//新建存放当前天数据的list
		ArrayList<Point> today = new ArrayList<Point>();
		//新建数据库helper
		colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
		//创建数据库，或获得已存在的数据库
		SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
		
		today = select(colorBase,todayHead,todayEnd);
//refine data in dababase
		//ContentValues values = new ContentValues();
		//values.put("colorId", 0);
		//colorBase.update("colors", values, "moment = ?", new String[] { "1555399568" });
		//colorBase.delete("colors", "moment = ?", new String[] { "1555400549" });
		
		//数据写入主页fix控件		
		fillWithColor(today,fixLine);
		
		
		
		
//插入前27天
		//find days_area
		//计算前一天秒范围
		TimeRange todayRange = new TimeRange(todayHead,todayEnd);
		TimeRange yesterdayRange = todayRange.yesterday(todayRange);
		//yesterdayRange.start=todayHead;
		//yesterdayRange.end=todayEnd;
		for(int i=0;i<27;i++){
			ArrayList<Point> yesterday = new ArrayList<Point>();
			//查前一天数据存入yesterday 
			yesterday = select(colorBase,yesterdayRange.start,yesterdayRange.end);
			LinearLayout day = new LinearLayout(MainActivity.this);
			LinearLayout.LayoutParams dayLP = new LinearLayout.LayoutParams(  
		            ViewGroup.LayoutParams.MATCH_PARENT,  
		            0,1);  
			day.setLayoutParams(dayLP);
			day.setOrientation(LinearLayout.HORIZONTAL);
			fillWithColor(yesterday, day);
			daysArea.addView(day);
			yesterdayRange = yesterdayRange.yesterday(yesterdayRange);	
		}
//定义按钮活动
		Button button1 = (Button) findViewById(R.id.new_record);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//点击进入addpoint活动
				Intent intent = new Intent(MainActivity.this, AddPoint.class);
				startActivity(intent);
			}
		});
		
	}
	
	
	
//根据两个10位秒码查找指定数据库，选取结果存入list
	public static ArrayList<Point> select(SQLiteDatabase db,long start,long end){
		ArrayList<Point> result = new ArrayList<Point> ();
		long min=start;
		long max=end;
		if(start>end){
			min=end;
			max=start;
		}
		SimpleDateFormat dateformatD = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateformatD.format(min*1000);
		Log.d("recent", "find from:" + dateString);
		Cursor cursor = db.rawQuery(
				"SELECT * FROM colors WHERE moment >= ? AND moment <= ?", 
				new String[]{min+"",max+""});
		Log.d("recent", "get:" + cursor.getCount());
		//如果选取不为空
		if (cursor.moveToFirst()) {
			do {//  遍历Cursor对象，取出数据
				long moment = cursor.getInt(cursor.getColumnIndex("moment"));			
				int colorId = cursor.getInt(cursor.getColumnIndex("colorId"));
				String description = cursor.getString(cursor.getColumnIndex("description"));
				//把数据写入当日list
				result.add(new Point(moment,colorId,description));
				//SimpleDateFormat dateformatMin = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				//String dateString = dateformatMin.format(((Point)point.getTag()).moment*1000);
				//new SimpleDateFormat("yyyy-MM-dd hh:mm").format(result.get(result.size()-1).moment*1000);
				Log.d("recent", 
						"add " + (new SimpleDateFormat("yyyy-MM-dd hh:mm")).format(result.get(result.size()-1).moment*1000));
			} while (cursor.moveToNext());
		}
		//关闭cursor
		cursor.close();
		
		return result;
		
	}
	
	
//根据list生成颜色块，插入指定布局
	public void fillWithColor(ArrayList<Point> list, LinearLayout layout){
		
		Log.d("recent","start fill");
		TextView tail = new TextView(this);
		if(list.size()!=0){//如果list不为空
			//查找list代表的日期的秒数范围
			TimeRange tr= findRange(list.get(0).moment);
			//新建色块，插入main	
			for(int i=0;i<list.size();i++){
				Log.d("recent","to fill "+i);
				//新建textview
				final TextView point = new TextView(this);
				//设置边界
				point.setText("|");
				point.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
				//设置tag		
				point.setTag(list.get(i));//attach Point as tag
				//设置颜色
				Log.d("recent","find color"+list.get(i).colorId+":"+list.get(i).moment);
				String s=com.nightmare.recent.EditAndAdd.colorRange.get(
						list.get(i).colorId);
				Log.d("recent","color code:"+ s);
				point.setBackgroundColor(Color.parseColor(s));//Color.parseColor("#00FF00")
				//设置weight
				int weight;
				//对于第一个色块
				if(i==0){
					weight= (int) (list.get(i).moment-tr.start);
				}else{//对于后面的色块
					weight=(int)(list.get(i).moment-list.get(i-1).moment);
				}
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( //格式
			            0, LayoutParams.MATCH_PARENT,weight); 
				point.setLayoutParams(lp);
				
				//add click activity
				point.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						SimpleDateFormat dateformatMin = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						//String dateString = dateformatMin.format(((Point)point.getTag()).moment*1000);
						//Toast.makeText(MainActivity.this, 
						//		"\n"+ dateString+":\n"+((Point)point.getTag()).description,
						//		Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(MainActivity.this, EditPoint.class);
						intent.putExtra("extra_data",  ((Point)point.getTag()).moment+"");
						startActivity(intent);
					}
				});
				//插入main
				layout.addView(point);
				Log.d("recent","fill "+ i);
			}
			Log.d("recent","for tail");
			//设置结尾textview
			int tWeight=(int)(tr.end - list.get(list.size()-1).moment);
			tail.setLayoutParams(new LinearLayout.LayoutParams( //格式
			            0, LayoutParams.MATCH_PARENT,tWeight));
		}else{//list为空
			tail.setLayoutParams(new LinearLayout.LayoutParams( //格式
					0, LayoutParams.MATCH_PARENT,1));
		}
		//设置结尾颜色
		tail.setBackgroundColor(Color.parseColor("#f6f6f6"));
		//设置结尾文字 以标明空行
		tail.setText(".");
		tail.setGravity(Gravity.CENTER_VERTICAL);
		//设置结尾点击事件
		tail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(
						MainActivity.this, "empty",Toast.LENGTH_SHORT).show();
			}
		});
		//添加结尾
		layout.addView(tail);
		Log.d("recent","done fill");
	}
	
	
//get the day's range from timecode(10-bit)
	public static TimeRange findRange(long timeCode){
		//Log.d("recent","find");
		TimeRange tr = new TimeRange();
		//把秒数转化为年月日
		SimpleDateFormat dateformatD = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateformatD.format(timeCode*1000);
		//把年月日加工成所在日的秒级描述
		String time1=dateString+" 00:00:00";
		String time2=dateString+" 23:59:59";
		//把时间描述范围转化成秒数
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


