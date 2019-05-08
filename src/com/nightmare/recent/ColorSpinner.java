package com.nightmare.recent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ColorSpinner extends LinearLayout {

	static List<String> colorRange = Arrays.asList(
			"#aa353b",
			"#d7711d",
			"#dca93d",
			"#5d9944",
			"#3ebbda",
			"#206096",
			"#8a256f",
			"#90634b",
			"#e0bebf");
	List<ColorBlock> colorList;
	
	static String selectColorCode;
	
	
	public ColorSpinner(Context context, AttributeSet attrs){
		super(context,attrs);
		Log.d("recent", "create spinner2");
		LayoutInflater.from(context).inflate(R.layout.color_spinner, this);
		
		
		initColors(colorRange);
		//�Զ���spinnerԪ�ز���
		final ColorAdapter adapter = new ColorAdapter(((Activity) getContext()),R.layout.color, colorList);
		Spinner spinner = (Spinner) findViewById(R.id.spinner0);
		spinner.setAdapter(adapter);
		//���spinner�¼�
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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
		});
	}  
	
	

	
	
	

	
	
	
	//��ʼ����ɫ�б�	
	void initColors(List<String> list) {
		colorList = new ArrayList<ColorBlock>();
		for(int i=0;i<list.size();i++){
			ColorBlock color = new ColorBlock(list.get(i));
			colorList.add(color);
		}
	}

}
