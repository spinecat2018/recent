package com.nightmare.recent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditAndAdd extends LinearLayout {
	
/*	static List<String> colorRange = Arrays.asList(
			"#aa353b",
			"#d7711d",
			"#dca93d",
			"#5d9944",
			"#3ebbda",
			"#206096",
			"#8a256f",
			"#90634b",
			"#e0bebf");     */
	
	//新建颜色块list，用于adapter	
	//List<ColorBlock> colorList = new ArrayList<ColorBlock>();
	
//	List<ColorBlock> colorList;
	
	//initColors(colorRange);
	
	//用于存储spinner选取的颜色号
	String selectColorCode;
	
	com.nightmare.recent.ColorSpinner colorSpinner;
	//public static String colorTableCode;
	//ColorBase colorBaseHelper;
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			
	
	public EditAndAdd(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.edit_and_add, this);
		
		
		
		
//		initColors(colorRange);
		
		//自定义spinner元素布局
//		final ColorAdapter adapter = new ColorAdapter(((Activity) getContext()),R.layout.color, colorList);
		colorSpinner = (com.nightmare.recent.ColorSpinner) findViewById(R.id.color_spinner);
		selectColorCode = colorSpinner.selectedColor();
		
//		spinner.setAdapter(adapter);
		//添加spinner事件
/*		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		     public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				ColorBlock selectColor = adapter.getItem(position);
				//保存颜色号
				selectColorCode = selectColor.getName();
		     }

		     @Override
		     public void onNothingSelected(AdapterView<?> parent) {
		         // TODO Auto-generated method stub    
		     }
		});    */

//返回按钮		
		Button back = (Button) findViewById(R.id.button_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity) getContext()).finish();
			}
		});
				
	}	
	
	
//初始化颜色列表	
/*	void initColors(List<String> list) {
		colorList = new ArrayList<ColorBlock>();
		for(int i=0;i<list.size();i++){
			ColorBlock color = new ColorBlock(list.get(i));
			colorList.add(color);
		}
	}   */
	
	
String selectedColor(){
		
		return colorSpinner.selectedColor();
	
	}
	
}
	
	
	
	

