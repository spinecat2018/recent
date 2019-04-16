package com.nightmare.recent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;



public class AddPoint extends Activity {
	
	String colorCode,description;
	//public static String colorTableCode;
	private ColorBase colorBaseHelper;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("recent","create");	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_point);
		
		LinearLayout forEdit=(LinearLayout)findViewById(R.id.l_slide);
		
		forEdit.removeAllViews();
		//创建数据库
		//Log.d("recent","before db");	
		colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
		final SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
		//Log.d("recent","after db");	
		
		
		//final ColorAdapter adapter = new ColorAdapter(this,R.layout.color, com.nightmare.recent.EditAndAdd.colorList);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		//spinner.setAdapter(adapter);
		//添加spinner事件
		/*spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		     public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				//ColorBlock selectColor = adapter.getItem(position);
				//保存颜色号
				//colorCode = selectColor.getName();
		     }

		     @Override
		     public void onNothingSelected(AdapterView<?> parent) {
		         // TODO Auto-generated method stub    
		     }
		});*/
		
		
//保存按钮
		Button button1 = (Button) findViewById(R.id.button_save);
		Log.d("recent","find buttonSave");	
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//点击按钮，添加数据	
				Log.d("recent","click save");
				//获取颜色编码在数组中的序号
				int colorId	= com.nightmare.recent.EditAndAdd.colorRange.indexOf(com.nightmare.recent.EditAndAdd.selectColorCode);
				//保存备注
				EditText detail = (EditText) findViewById(R.id.edit_text1);
				description = detail.getText().toString();
				//写入数据库
				ContentValues values = new ContentValues();
				//组装数据
				values.put("moment", System.currentTimeMillis()/1000+"");
				values.put("colorId", colorId);
				values.put("description", description);
				//插入数据
				colorBase.insert("colors", null, values); 
				values.clear();
				Log.d("recent",
					"record "+ colorId +":"+System.currentTimeMillis()/1000+":"+description);		
				//清空备注栏
				//detail.setText("");
				//备注栏失焦
				//detail.clearFocus();
				//保存成功反馈
				Toast.makeText(
						AddPoint.this, "saved" ,Toast.LENGTH_SHORT).show();
				//返回
				AddPoint.this.finish();
			}
		});
//返回按钮		
		
	}	
	
	
	
}
