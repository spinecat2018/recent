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


public class FruitAdapter extends ArrayAdapter<Fruit> {
	
	private int resourceId;
	
	public FruitAdapter(Context context, int textViewResourceId,List<Fruit> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Fruit fruit = getItem(position); // 获取当前项的Fruit实例
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
		
		fruitName.setBackgroundColor(Color.parseColor(fruit.getName()));
		
		return view;
	}
	
	@Override
	public View getDropDownView(int position, View convertView,
			ViewGroup parent) {
		Fruit fruit = getItem(position); // 获取当前项的Fruit实例
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
		
		fruitName.setBackgroundColor(Color.parseColor(fruit.getName()));
		
		return view;
	}

	
	
	
}
