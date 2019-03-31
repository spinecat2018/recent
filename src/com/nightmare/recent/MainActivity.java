package com.nightmare.recent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
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
/*		
//��ȡ�������ݣ������list day1  
		//��ȡ��ǰ����
		long nowCode = System.currentTimeMillis()/1000;
		//ת���ɵ����뷶Χ
		long todayHead = findRange(nowCode).start;
		long todayEnd = findRange(nowCode).end;	
		Log.d("recent", ""+nowCode+"-->"+todayHead+"-"+todayEnd);
		//�½���ŵ�ǰ�����ݵ�list
		ArrayList<Point> today = new ArrayList<Point>();
		//�½����ݿ�helper
		colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
		//�������ݿ⣬�����Ѵ��ڵ����ݿ�
		SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
		
		today = select(colorBase,todayHead,todayEnd);
		//block1
		//����д����ҳfix�ؼ�
		LinearLayout fixLine = (LinearLayout) findViewById(R.id.fix_area);//find fix_area
		
		fillWithColor(today,fixLine);
		//block2
//����ǰ6��
		LinearLayout daysArea = (LinearLayout) findViewById(R.id.days_area);//find days_area
		//����ǰһ���뷶Χ
		TimeRange yesterdayRange = new TimeRange(todayHead,todayEnd).yesterday();
		//yesterdayRange.start=todayHead;
		//yesterdayRange.end=todayEnd;
		for(int i=0;i<6;i++){
			ArrayList<Point> yesterday = new ArrayList<Point>();
			//��ǰһ�����ݴ���yesterday 
			yesterday = select(colorBase,yesterdayRange.start,yesterdayRange.end);
			LinearLayout day = new LinearLayout(MainActivity.this);
			LinearLayout.LayoutParams dayLP = new LinearLayout.LayoutParams(  
		            ViewGroup.LayoutParams.MATCH_PARENT,  
		            ViewGroup.LayoutParams.WRAP_CONTENT);  
			day.setLayoutParams(dayLP);
			day.setOrientation(LinearLayout.HORIZONTAL);
			fillWithColor(yesterday, day);
			daysArea.addView(day);
			yesterdayRange = yesterdayRange.yesterday();	
		}
//���尴ť�
		Button button1 = (Button) findViewById(R.id.new_record);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//�������addpoint�
				Intent intent = new Intent(MainActivity.this, AddPoint.class);
				startActivity(intent);
			}
		});
	*/	
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("recent", "onResume");
//���fix_area��days_area
		LinearLayout fixLine = (LinearLayout) findViewById(R.id.fix_area);
		LinearLayout daysArea = (LinearLayout) findViewById(R.id.days_area);
		fixLine.removeAllViews();
		daysArea.removeAllViews();
//��ȡ�������ݣ������list day1  
		//��ȡ��ǰ����
		long nowCode;	
		nowCode = System.currentTimeMillis()/1000;
		//ת���ɵ����뷶Χ
		long todayHead,todayEnd;
		todayHead = findRange(nowCode).start;
		todayEnd = findRange(nowCode).end;	
		Log.d("recent", ""+nowCode+"-->"+todayHead+"-"+todayEnd);
		//�½���ŵ�ǰ�����ݵ�list
		ArrayList<Point> today = new ArrayList<Point>();
		//�½����ݿ�helper
		colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
		//�������ݿ⣬�����Ѵ��ڵ����ݿ�
		SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
		
		today = select(colorBase,todayHead,todayEnd);
		//block1
		//����д����ҳfix�ؼ�
		//find fix_area
		
		fillWithColor(today,fixLine);
		//block2
//����ǰ6��
		//find days_area
		//����ǰһ���뷶Χ
		TimeRange todayRange = new TimeRange(todayHead,todayEnd);
		TimeRange yesterdayRange = todayRange.yesterday(todayRange);
		//yesterdayRange.start=todayHead;
		//yesterdayRange.end=todayEnd;
		for(int i=0;i<6;i++){
			ArrayList<Point> yesterday = new ArrayList<Point>();
			//��ǰһ�����ݴ���yesterday 
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
//���尴ť�
		Button button1 = (Button) findViewById(R.id.new_record);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//�������addpoint�
				Intent intent = new Intent(MainActivity.this, AddPoint.class);
				startActivity(intent);
			}
		});
		
	}
	
	//��������10λ�������ָ�����ݿ⣬ѡȡ�������list
	public ArrayList<Point> select(SQLiteDatabase db,long start,long end){
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
		//���ѡȡ��Ϊ��
		if (cursor.moveToFirst()) {
			do {//  ����Cursor����ȡ������
				long moment = cursor.getInt(cursor.getColumnIndex("moment"));			
				int colorId = cursor.getInt(cursor.getColumnIndex("colorId"));
				String description = cursor.getString(cursor.getColumnIndex("description"));
				//������д�뵱��list
				result.add(new Point(moment,colorId,description));
				//SimpleDateFormat dateformatMin = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				//String dateString = dateformatMin.format(((Point)point.getTag()).moment*1000);
				//new SimpleDateFormat("yyyy-MM-dd hh:mm").format(result.get(result.size()-1).moment*1000);
				Log.d("recent", 
						"add " + (new SimpleDateFormat("yyyy-MM-dd hh:mm")).format(result.get(result.size()-1).moment*1000));
			} while (cursor.moveToNext());
		}
		//�ر�cursor
		cursor.close();
		
		return result;
		
	}
	
	
	//����list������ɫ�飬����ָ������
	public void fillWithColor(ArrayList<Point> list, LinearLayout layout){
		
		TextView tail = new TextView(this);
		if(list.size()!=0){//���list��Ϊ��
			//����list��������ڵ�������Χ
			TimeRange tr= findRange(list.get(0).moment);
			//�½�ɫ�飬����main	
			for(int i=0;i<list.size();i++){
				//�½�textview
				final TextView point = new TextView(this);
				//���ñ߽�
				point.setText("|");
				point.setGravity(Gravity.RIGHT|Gravity.FILL_VERTICAL);
				//����tag		
				point.setTag(list.get(i));//attach Point as tag
				//������ɫ
				String s=com.nightmare.recent.AddPoint.colorRange.get(
						list.get(i).colorId);
				Log.d("recent","color code:"+ s);
				point.setBackgroundColor(Color.parseColor(s));//Color.parseColor("#00FF00")
				//����weight
				int weight;
				//���ڵ�һ��ɫ��
				if(i==0){
					weight= (int) (list.get(i).moment-tr.start);
				}else{//���ں����ɫ��
					weight=(int)(list.get(i).moment-list.get(i-1).moment);
				}
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( //��ʽ
			            0, LayoutParams.MATCH_PARENT,weight); 
				point.setLayoutParams(lp);
				//add click activity
				point.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						SimpleDateFormat dateformatMin = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						String dateString = dateformatMin.format(((Point)point.getTag()).moment*1000);
						Toast.makeText(MainActivity.this, 
								dateString+":\n"+((Point)point.getTag()).description,
								Toast.LENGTH_SHORT).show();
					}
				});
				//����main
				layout.addView(point);
			}
			//���ý�βtextview
			int tWeight=(int)(tr.end - list.get(list.size()-1).moment);
			tail.setLayoutParams(new LinearLayout.LayoutParams( //��ʽ
			            0, LayoutParams.MATCH_PARENT,tWeight));
		}else{//listΪ��
			tail.setLayoutParams(new LinearLayout.LayoutParams( //��ʽ
					0, LayoutParams.MATCH_PARENT,1));
		}
		//���ý�β��ɫ
		tail.setBackgroundColor(Color.parseColor("#cccccc"));
		//���ý�β���� 
		//tail.setText("-");
		//���ý�β����¼�
		tail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(
						MainActivity.this, "empty",Toast.LENGTH_SHORT).show();
			}
		});
		//��ӽ�β
		layout.addView(tail);
	}
	
	//get the day's range from timecode(10-bit)
	public static TimeRange findRange(long timeCode){
		//Log.d("recent","find");
		TimeRange tr = new TimeRange();
		//������ת��Ϊ������
		SimpleDateFormat dateformatD = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateformatD.format(timeCode*1000);
		//�������ռӹ��������յ��뼶����
		String time1=dateString+" 00:00:00";
		String time2=dateString+" 23:59:59";
		//��ʱ��������Χת��������
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

/* 1 {  //��ѯ��������
Cursor cursor = colorBase.rawQuery(
		"SELECT * FROM colors WHERE moment >= ? AND moment <= ?", 
		new String[]{todayHead+"",todayEnd+""});
Log.d("recent", "get:" + cursor.getCount());
//���ѡȡ��Ϊ��
if (cursor.moveToFirst()) {
	do {//  ����Cursor����ȡ������
		long moment = cursor.getInt(cursor.getColumnIndex("moment"));			
		int colorId = cursor.getInt(cursor.getColumnIndex("colorId"));
		String description = cursor.getString(cursor.getColumnIndex("description"));
		//������д�뵱��list
		today.add(new Point(moment,colorId,description));
		Log.d("recent", "add " + today.get(today.size()-1).moment);
	} while (cursor.moveToNext());
}
//�ر�cursor
cursor.close();}
*/


/*2for(int i=0;i<today.size();i++){
//�½�textview
final TextView point = new TextView(this);
//���ñ߽�
point.setText("|");
point.setGravity(Gravity.RIGHT);
//����tag		
point.setTag(today.get(i));//attach Point as tag
//������ɫ
String s=com.nightmare.recent.AddPoint.colorRange.get(
		today.get(i).colorId);
Log.d("recent","color code:"+ s);
point.setBackgroundColor(Color.parseColor(s));//Color.parseColor("#00FF00")
//����weight
int weight;
if(i==0){
	weight= (int) (today.get(i).moment-todayHead);
}else{
	weight=(int)(today.get(i).moment-today.get(i-1).moment);
}
LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( //��ʽ
        0, LayoutParams.WRAP_CONTENT,weight); 
point.setLayoutParams(lp);
//add click activity
point.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		Toast.makeText(MainActivity.this, 
				((Point)point.getTag()).moment+", to start new activity...",
				Toast.LENGTH_SHORT).show();
	}
});
//����main
fixLine.addView(point);
}
//�����βtextview
TextView tail = new TextView(this);
int tWeight=(int)(todayEnd - today.get(today.size()-1).moment);
tail.setLayoutParams(new LinearLayout.LayoutParams( //��ʽ
        0, LayoutParams.WRAP_CONTENT,tWeight));
tail.setBackgroundColor(Color.parseColor("#cccccc"));
fixLine.addView(tail);*/


