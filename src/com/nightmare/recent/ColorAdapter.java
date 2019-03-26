package com.nightmare.recent;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ColorAdapter extends ArrayAdapter<String> {

	private int resourceId;
	
	public ColorAdapter(Context context, int textViewResourceId,String[] objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String fruit = getItem(position); // 获取当前项的Fruit实例
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
		
		convertView.setBackgroundColor(Color.parseColor(fruit));
		
		return view;
	}
	
	
}
