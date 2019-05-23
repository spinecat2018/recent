package com.nightmare.recent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditPoint extends Activity {

	private ColorBase colorBaseHelper;
	
	int selectColorId;
	com.nightmare.recent.ColorSpinner spinner;
	String selectDescription;
	EditText editText;
	SQLiteDatabase colorBase;
	String data;
	AlertDialog dialog;
	ArrayList<Point> list;
	int index;
	TextView pageName;
	String showTime;
	SimpleDateFormat dateformatS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_point);
	//从启动此活动的intent中获取信息：时间码 颜色
		//Log.d("recent", "get data");
		Intent intent = getIntent();
		//Log.d("recent", "get intent");
		data = intent.getStringExtra("extra_data");
		Log.d("recent", "get:" + data);
		colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
		colorBase= colorBaseHelper.getWritableDatabase();
		//获取时间范围	
		TimeRange selectDay = com.nightmare.recent.MainActivity.findRange(Long.parseLong(data));
		list = com.nightmare.recent.MainActivity.select(colorBase ,selectDay.start,selectDay.end);
		
		
		showTime = dateformatS.format(Long.parseLong(data)*1000);
		pageName = (TextView)findViewById(R.id.text_view1);
		pageName.setText("修改记录："+ showTime);
		//Log.d("recent", "find");
		Cursor cursor = colorBase.rawQuery(
				"select * from colors where moment = ?", new String[] { Long.parseLong(data)+"" });
				//"select * from colors", null);
		//Log.d("recent", "cursor");
		cursor.moveToFirst();
		selectColorId = cursor.getInt(
				cursor.getColumnIndex("colorId"));
		Log.d("recent", selectColorId+"");
		selectDescription = cursor.getString(
				cursor.getColumnIndex("description"));
		Log.d("recent", selectDescription);
		
		
		//初始化颜色和备注的值
		spinner = (com.nightmare.recent.ColorSpinner)findViewById(R.id.color_spinner);
		spinner.spinner.setSelection(selectColorId,true);
		
		editText = (EditText)findViewById(R.id.edit_text1);
		editText.setText(selectDescription);
		
	//定义保存修改按钮功能
		Button confirm = (Button)findViewById(R.id.button_save);
		confirm.setText("确认");
		
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		
				//获取当前值
				selectColorId = com.nightmare.recent.ColorSpinner.colorRange.indexOf(spinner.selectedColor());//getSelectedItemPosition();
				selectDescription = editText.getText().toString();
				//组装数据，写入数据库
				ContentValues values = new ContentValues();
				values.put("colorId", selectColorId);
				values.put("description", selectDescription);
				colorBase.update(
						"colors", values, "moment = ?", new String[] { Long.parseLong(data)+"" });
				values.clear();
				//退出当前活动
				EditPoint.this.finish();	
			}
		});
		
	//定义删除按钮功能
		Button delete = (Button)findViewById(R.id.button_del);
		
		dialog = new AlertDialog.Builder(this).create();//创建对话框
        //dialog.setIcon(R.mipmap.ic_launcher);//设置对话框icon
        dialog.setTitle("确定要删除这一条记录吗？");//设置对话框标题
       // dialog.setMessage("Hello world");//设置文字显示内容
        //设置button
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	colorBase.delete("colors", "moment = ?", new String[] { data  });
            	
                dialog.dismiss();//关闭对话框
				EditPoint.this.finish();	
            }
        });
        
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//关闭对话框
            }
        });
        
        
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.show();//显示对话框
								
			}
		});
	//定义上一个按钮功能		
		
		Point selectPoint = new Point(Long.parseLong(data),selectColorId,selectDescription);
		
		//Log.d("last", "selectPoint" + selectPoint.moment +":"+selectPoint.colorId+":"+selectPoint.description);
		index = list.indexOf(selectPoint);
		
		Button last = (Button)findViewById(R.id.button_last);
		
		last.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Log.d("last", "click      " + data +":"+selectColorId+":"+selectDescription);
				
			//获取当前表单填写的值
				//data =  pageName.getText().toString().substring(5) ;
				Log.d("last", data);				
				
				Log.d("last", index+"of"+list.size());
				if(index==0){
					Toast.makeText(EditPoint.this, "已到达当天第一条记录",
							Toast.LENGTH_SHORT).show();
				}else{
					
					data = list.get(index-1).moment+"";
					
					showTime = dateformatS.format( Long.parseLong(data)*1000);
					pageName.setText("修改记录："+ showTime);
					
					selectColorId = list.get(index-1).colorId;
					spinner.spinner.setSelection(selectColorId,true);
					
					selectDescription = list.get(index-1).description;
					editText.setText(selectDescription );
					
					index--;
				}
												
			}
		});
	
		//定义下一个按钮功能		
				Button next = (Button)findViewById(R.id.button_next);
				
				next.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						//Log.d("last", "click      " + data +":"+selectColorId+":"+selectDescription);
						//data =  pageName.getText().toString().substring(5) ;
						Log.d("last", data);	
						//Point selectPoint = new Point(Long.parseLong(data),selectColorId,selectDescription);
						
						//Log.d("last", "selectPoint" + selectPoint.moment +":"+selectPoint.colorId+":"+selectPoint.description);
						//index = list.indexOf(selectPoint);
						Log.d("last", index+"of"+list.size());
						if(index==list.size()-1){
							Toast.makeText(EditPoint.this, "已到达当天最后一条记录",
									Toast.LENGTH_SHORT).show();
						}else{
							
							data = list.get(index+1).moment+"";
							
							showTime = dateformatS.format( Long.parseLong(data)*1000);
							pageName.setText("修改记录："+ showTime);
							
							selectColorId = list.get(index+1).colorId;
							spinner.spinner.setSelection(selectColorId,true);
							
							selectDescription = list.get(index+1).description;
							editText.setText(selectDescription );
							
							index++;
						}
														
					}
				});	
		
		
		
		
	}	
	
	
	
	
}







