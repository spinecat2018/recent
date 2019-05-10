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

	static final List<String> colorRange = Arrays.asList(
			"#aa353b",
			"#d7711d",
			"#dca93d",
			"#5d9944",
			"#3ebbda",
			"#206096",
			"#8a256f",
			"#90634b",
			"#e0bebf");
	ArrayList<ColorBlock> colorList = new ArrayList<ColorBlock>();
	
	String selectColorCode;
	Spinner spinner ;
	
	public ColorSpinner(Context context, AttributeSet attrs){
		super(context,attrs);
		Log.d("recent", "create spinner2");
		LayoutInflater.from(context).inflate(R.layout.color_spinner, this);
		
		
		
		initColors(colorRange,colorList);
		
		
		//自定义spinner元素布局
		final ColorAdapter adapter = new ColorAdapter(((Activity) getContext()),R.layout.color, colorList);
		spinner = (Spinner) findViewById(R.id.spinner0);
		spinner.setAdapter(adapter);
		
		//spinner.setSelection(0,true);
		
		//selectColorCode = ((ColorBlock)spinner.getSelectedItem()).getName();
		
		//添加spinner事件
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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
		});
	}  
	
	

	
	
	

	
	
	
	//初始化颜色列表	
	void initColors(List<String> list,ArrayList<ColorBlock> cl) {
		cl.clear();
		for(int i=0;i<list.size();i++){
			ColorBlock color = new ColorBlock(list.get(i));
			cl.add(color);
		}
	}
	
	void resetSpinner(ColorAdapter a){
		spinner.setAdapter(a);
	}
	
	String selectedColor(){
		
		return ((ColorBlock)spinner.getSelectedItem()).getName();
	
	}

}
