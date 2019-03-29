package com.nightmare.recent;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SomePoints extends LinearLayout {
	//int num = 1;
	public SomePoints(Context context, AttributeSet attrs, ArrayList<Point> list) {
		super(context, attrs);
		Log.d("recent","somepoints"+ list.size());
		
		for(int i=0;i<list.size();i++){
			LayoutInflater.from(context).inflate(R.layout.one_point, this);
			final TextView colorPoint = (TextView) findViewById(R.id.point_moment);//find textview
			colorPoint.setId(i);//change id
			colorPoint.setTag(list.get(i));//attach Point as tag
			String s=com.nightmare.recent.AddPoint.colorRange.get(
							list.get(i).colorId);
			Log.d("recent","color code:"+ s);
			colorPoint.setBackgroundColor(Color.parseColor(s));//Color.parseColor("#00FF00")
			
			//add click activity
			
			colorPoint.setOnClickListener(new OnClickListener() {

				@Override
				
				public void onClick(View v) {
					
					Toast.makeText(getContext(), 
							((Point)colorPoint.getTag()).description+"to start new activity...",
							Toast.LENGTH_SHORT).show();
				}
				
			});
			
		}
	
		

		
		}
	
	
}
