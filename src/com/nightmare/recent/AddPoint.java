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
import android.widget.Spinner;
import android.widget.Toast;



public class AddPoint extends Activity {

	List<String> colorRange = Arrays.asList(
			"#925649",
			"#aa24a8",
			"#f62541");
	
	
	
	private List<Fruit> fruitList = new ArrayList<Fruit>();
	
	String colorCode,description;
	
	public static String colorTableCode;
		
	private ColorBase colorBaseHelper;
	
	 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//Log.d("recent","create");	
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_point);
		
				
		initFruits();
						
		final FruitAdapter adapter = new FruitAdapter(AddPoint.this,R.layout.color, fruitList);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		
		spinner.setAdapter(adapter);
								
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		     public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				Fruit selectColor = adapter.getItem(position);
				colorCode = selectColor.getName();
				
		     }

		     @Override
		     public void onNothingSelected(AdapterView<?> parent) {
		         // TODO Auto-generated method stub    
		     }

		});
		//创建数据库
		
		//Log.d("recent","before db");	
		colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
		
		final SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
		
		//Log.d("recent","after db");			
		//保存按钮
		
		Button button1 = (Button) findViewById(R.id.buttonSave);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//点击按钮，添加数据	
				int colorId	= colorRange.indexOf(colorCode);//获取颜色编码在数组中的序号
				
				EditText detail = (EditText) findViewById(R.id.edit_text1);
				description = detail.getText().toString();
				
				ContentValues values = new ContentValues();
				//  开始组装第一条数据
				values.put("moment", System.currentTimeMillis()/1000+"");
				values.put("description", description);
				
				colorBase.insert("c"+ colorId, null, values); //  插入第一条数据
				
				values.clear();
				Log.d("recent","record"+System.currentTimeMillis()/1000+description);		
				
														
				
				
				//Date moment = new Date();
				
				//Log.d("recent",System.currentTimeMillis()+" + "+ sdf.format(moment));	
				//Toast.makeText(AddPoint.this, colorId +"+"+description ,
				//Toast.LENGTH_SHORT).show();
						
			}
		});
		
		
	}	
	
	private void initFruits() {
		Fruit apple = new Fruit(colorRange.get(0));
		fruitList.add(apple);
		Fruit banana = new Fruit(colorRange.get(1));
		fruitList.add(banana);
		Fruit orange = new Fruit(colorRange.get(2));
		fruitList.add(orange);
		//Log.d("recent","init");	
		}

	
	 
	
	
}
