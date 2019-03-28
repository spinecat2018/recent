package com.nightmare.recent;
import java.util.List;

import com.nightmare.recent.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ColorAdapter extends ArrayAdapter<ColorBlock> {
	
	private int resourceId;
	
	public ColorAdapter(Context context, int textViewResourceId,List<ColorBlock> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ColorBlock color = getItem(position); // 获取当前项的Fruit实例
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		TextView colorName = (TextView) view.findViewById(R.id.color_name);
		
		colorName.setBackgroundColor(Color.parseColor(color.getName()));
		
		return view;
	}
	
	@Override
	public View getDropDownView(int position, View convertView,
			ViewGroup parent) {
		ColorBlock color = getItem(position); // 获取当前项的Fruit实例
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		TextView colorName = (TextView) view.findViewById(R.id.color_name);
		
		colorName.setBackgroundColor(Color.parseColor(color.getName()));
		
		return view;
	}

	
	
	
}
