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
	
	//�½���ɫ��list������adapter	
	//List<ColorBlock> colorList = new ArrayList<ColorBlock>();
	
//	List<ColorBlock> colorList;
	
	//initColors(colorRange);
	
	//���ڴ洢spinnerѡȡ����ɫ��
	String selectColorCode;
	
	com.nightmare.recent.ColorSpinner colorSpinner;
	//public static String colorTableCode;
	//ColorBase colorBaseHelper;
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			
	
	public EditAndAdd(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.edit_and_add, this);
		
		
		
		
//		initColors(colorRange);
		
		//�Զ���spinnerԪ�ز���
//		final ColorAdapter adapter = new ColorAdapter(((Activity) getContext()),R.layout.color, colorList);
		colorSpinner = (com.nightmare.recent.ColorSpinner) findViewById(R.id.color_spinner);
		selectColorCode = colorSpinner.selectedColor();
		
//		spinner.setAdapter(adapter);
		//���spinner�¼�
/*		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		     public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				ColorBlock selectColor = adapter.getItem(position);
				//������ɫ��
				selectColorCode = selectColor.getName();
		     }

		     @Override
		     public void onNothingSelected(AdapterView<?> parent) {
		         // TODO Auto-generated method stub    
		     }
		});    */

//���ذ�ť		
		Button back = (Button) findViewById(R.id.button_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity) getContext()).finish();
			}
		});
				
	}	
	
	
//��ʼ����ɫ�б�	
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
	
	
	
	

