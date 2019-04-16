package com.nightmare.recent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class EditPoint extends Activity {

	private ColorBase colorBaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.edit_point);
//从启动此活动的intent中获取信息：时间码 颜色
	//Log.d("recent", "get data");
	Intent intent = getIntent();
	//Log.d("recent", "get intent");
	String data = intent.getStringExtra("extra_data");
	Log.d("recent", "get:" + data);
	colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
	SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
	//获取时间范围	
	TimeRange selectDay = com.nightmare.recent.MainActivity.findRange(Long.parseLong(data));
	ArrayList<Point> list = com.nightmare.recent.MainActivity.select(colorBase ,selectDay.start,selectDay.end);
	
	SimpleDateFormat dateformatS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String showTime = dateformatS.format(Long.parseLong(data)*1000);
	TextView pageName = (TextView)findViewById(R.id.text_view1);
	pageName.setText("修改记录："+ showTime);
	//Log.d("recent", "find");
	Cursor cursor = colorBase.rawQuery(
			"select * from colors where moment = ?", new String[] { Long.parseLong(data)+"" });
			//"select * from colors", null);
	//Log.d("recent", "cursor");
	cursor.moveToFirst();
	int selectColorId = cursor.getInt(
			cursor.getColumnIndex("colorId"));
	Log.d("recent", selectColorId+"");
	String selectDescription = cursor.getString(
			cursor.getColumnIndex("description"));
	Log.d("recent", selectDescription);
	
	Spinner spinner = (Spinner)findViewById(R.id.spinner1);
	spinner.setSelection(selectColorId,true);
	
	EditText editText = (EditText)findViewById(R.id.edit_text1);
	editText.setText(selectDescription);
	
	//定义保存修改按钮功能
	Button confirm = (Button)findViewById(R.id.button_save);
	confirm.setText("确认");
	
	}
	
}
