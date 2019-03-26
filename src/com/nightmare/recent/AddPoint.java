package com.nightmare.recent;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
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

	String colorCode,description;
	
	
	
	private List<Fruit> fruitList = new ArrayList<Fruit>();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_point);
		
				
		initFruits();
		
		//ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(
			//	AddPoint.this, android.R.layout.simple_spinner_item, data);
				
		final FruitAdapter adapter = new FruitAdapter(
			AddPoint.this,R.layout.color, fruitList);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		
		spinner.setAdapter(adapter);
		
		
		
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		     public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
		         //System.out.println(spinner==parent);//true
		         //System.out.println(view);
		         //String data = adapter.getItem(position);//从适配器中获取被选择的数据项
		         //String data = list.get(position);//从集合中获取被选择的数据项
				Fruit selectColor = adapter.getItem(position);
				colorCode = selectColor.getName();
		        
		     }

		     @Override
		     public void onNothingSelected(AdapterView<?> parent) {
		         // TODO Auto-generated method stub    
		     }

		});

		//保存按钮
		Button button1 = (Button) findViewById(R.id.buttonSave);
		button1.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		//点击按钮，新建数据库，新建颜色表，添加数据	
			
		
		EditText detail = (EditText) findViewById(R.id.edit_text1);
		description = detail.getText().toString();
		
		Toast.makeText(AddPoint.this, colorCode +"+"+description ,
		Toast.LENGTH_SHORT).show();
		
		
		
		}
		});
		
		
		
		
		
	}	
	
	private void initFruits() {
		Fruit apple = new Fruit("#692fff");
		fruitList.add(apple);
		Fruit banana = new Fruit("#004768");
		fruitList.add(banana);
		Fruit orange = new Fruit("#007893");
		fruitList.add(orange);
		Fruit watermelon = new Fruit("#00d946");
		fruitList.add(watermelon);
		Fruit pear = new Fruit("#00e67f");
		fruitList.add(pear);
		Fruit grape = new Fruit("#00a546");
		fruitList.add(grape);
		Fruit pineapple = new Fruit("#00c576");
		fruitList.add(pineapple);
		Fruit strawberry = new Fruit("#00b34a");
		fruitList.add(strawberry);
		Fruit cherry = new Fruit("#00d235");
		fruitList.add(cherry);
		Fruit mango = new Fruit("#006784");
		fruitList.add(mango);
		}
	
	 
	
	
}
