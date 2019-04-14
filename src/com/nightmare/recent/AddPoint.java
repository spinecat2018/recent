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
	//Ԥ����ɫ
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
	//�½���ɫ��list������adapter	
	private List<ColorBlock> colorList = new ArrayList<ColorBlock>();
	//���ڴ洢spinnerѡȡ����ɫ�ź�edittext�ڵı�ע
	String colorCode,description;
	//public static String colorTableCode;
	private ColorBase colorBaseHelper;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("recent","create");	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_point);
		//����ɫ������Ÿ���ɫ�鸳ֵ	
		initColors(colorRange);
		//�Զ���spinnerԪ�ز���
		final ColorAdapter adapter = new ColorAdapter(AddPoint.this,R.layout.color, colorList);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		//���spinner�¼�
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		     public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				ColorBlock selectColor = adapter.getItem(position);
				//������ɫ��
				colorCode = selectColor.getName();
		     }

		     @Override
		     public void onNothingSelected(AdapterView<?> parent) {
		         // TODO Auto-generated method stub    
		     }
		});
		//�������ݿ�
		//Log.d("recent","before db");	
		colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
		final SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
		//Log.d("recent","after db");			
//���水ť
		Button button1 = (Button) findViewById(R.id.button_save);
		Log.d("recent","find buttonSave");	
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//�����ť���������	
				//��ȡ��ɫ�����������е����
				int colorId	= colorRange.indexOf(colorCode);
				//���汸ע
				EditText detail = (EditText) findViewById(R.id.edit_text1);
				description = detail.getText().toString();
				//д�����ݿ�
				ContentValues values = new ContentValues();
				//��װ����
				values.put("moment", System.currentTimeMillis()/1000+"");
				values.put("colorId", colorId);
				values.put("description", description);
				//��������
				colorBase.insert("colors", null, values); 
				values.clear();
				Log.d("recent",
					"record "+ colorId +":"+System.currentTimeMillis()/1000+":"+description);		
				//��ձ�ע��
				//detail.setText("");
				//��ע��ʧ��
				//detail.clearFocus();
				//����ɹ�����
				Toast.makeText(
						AddPoint.this, "saved" ,Toast.LENGTH_SHORT).show();
				//����
				AddPoint.this.finish();
			}
		});
//���ذ�ť		
		Button back = (Button) findViewById(R.id.button_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddPoint.this.finish();
			}
		});
	}	
	
	private void initColors(List<String> list) {
		for(int i=0;i<list.size();i++){
			ColorBlock color = new ColorBlock(colorRange.get(i));
			colorList.add(color);
		}
	}
	
}
