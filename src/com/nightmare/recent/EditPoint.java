package com.nightmare.recent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditPoint extends Activity {

	private ColorBase colorBaseHelper;
	
	int selectColorId;
	com.nightmare.recent.ColorSpinner spinner;
	String selectDescription;
	EditText editText;
	SQLiteDatabase colorBase;
	String data;
	AlertDialog dialog;
	ArrayList<Point> list;
	int index;
	TextView pageName;
	String showTime;
	SimpleDateFormat dateformatS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_point);
	//�������˻��intent�л�ȡ��Ϣ��ʱ���� ��ɫ
		//Log.d("recent", "get data");
		Intent intent = getIntent();
		//Log.d("recent", "get intent");
		data = intent.getStringExtra("extra_data");
		Log.d("recent", "get:" + data);
		colorBaseHelper = new ColorBase(this, "colorDataBase.db", null, 1);
		colorBase= colorBaseHelper.getWritableDatabase();
		//��ȡʱ�䷶Χ	
		TimeRange selectDay = com.nightmare.recent.MainActivity.findRange(Long.parseLong(data));
		list = com.nightmare.recent.MainActivity.select(colorBase ,selectDay.start,selectDay.end);
		
		
		showTime = dateformatS.format(Long.parseLong(data)*1000);
		pageName = (TextView)findViewById(R.id.text_view1);
		pageName.setText("�޸ļ�¼��"+ showTime);
		//Log.d("recent", "find");
		Cursor cursor = colorBase.rawQuery(
				"select * from colors where moment = ?", new String[] { Long.parseLong(data)+"" });
				//"select * from colors", null);
		//Log.d("recent", "cursor");
		cursor.moveToFirst();
		selectColorId = cursor.getInt(
				cursor.getColumnIndex("colorId"));
		Log.d("recent", selectColorId+"");
		selectDescription = cursor.getString(
				cursor.getColumnIndex("description"));
		Log.d("recent", selectDescription);
		
		
		//��ʼ����ɫ�ͱ�ע��ֵ
		spinner = (com.nightmare.recent.ColorSpinner)findViewById(R.id.color_spinner);
		spinner.spinner.setSelection(selectColorId,true);
		
		editText = (EditText)findViewById(R.id.edit_text1);
		editText.setText(selectDescription);
		
	//���屣���޸İ�ť����
		Button confirm = (Button)findViewById(R.id.button_save);
		confirm.setText("ȷ��");
		
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		
				//��ȡ��ǰֵ
				selectColorId = com.nightmare.recent.ColorSpinner.colorRange.indexOf(spinner.selectedColor());//getSelectedItemPosition();
				selectDescription = editText.getText().toString();
				//��װ���ݣ�д�����ݿ�
				ContentValues values = new ContentValues();
				values.put("colorId", selectColorId);
				values.put("description", selectDescription);
				colorBase.update(
						"colors", values, "moment = ?", new String[] { Long.parseLong(data)+"" });
				values.clear();
				//�˳���ǰ�
				EditPoint.this.finish();	
			}
		});
		
	//����ɾ����ť����
		Button delete = (Button)findViewById(R.id.button_del);
		
		dialog = new AlertDialog.Builder(this).create();//�����Ի���
        //dialog.setIcon(R.mipmap.ic_launcher);//���öԻ���icon
        dialog.setTitle("ȷ��Ҫɾ����һ����¼��");//���öԻ������
       // dialog.setMessage("Hello world");//����������ʾ����
        //����button
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"ȷ��", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	colorBase.delete("colors", "moment = ?", new String[] { data  });
            	
                dialog.dismiss();//�رնԻ���
				EditPoint.this.finish();	
            }
        });
        
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "ȡ��", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//�رնԻ���
            }
        });
        
        
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.show();//��ʾ�Ի���
								
			}
		});
	//������һ����ť����		
		
		Point selectPoint = new Point(Long.parseLong(data),selectColorId,selectDescription);
		
		//Log.d("last", "selectPoint" + selectPoint.moment +":"+selectPoint.colorId+":"+selectPoint.description);
		index = list.indexOf(selectPoint);
		
		Button last = (Button)findViewById(R.id.button_last);
		
		last.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Log.d("last", "click      " + data +":"+selectColorId+":"+selectDescription);
				
			//��ȡ��ǰ����д��ֵ
				//data =  pageName.getText().toString().substring(5) ;
				Log.d("last", data);				
				
				Log.d("last", index+"of"+list.size());
				if(index==0){
					Toast.makeText(EditPoint.this, "�ѵ��ﵱ���һ����¼",
							Toast.LENGTH_SHORT).show();
				}else{
					
					data = list.get(index-1).moment+"";
					
					showTime = dateformatS.format( Long.parseLong(data)*1000);
					pageName.setText("�޸ļ�¼��"+ showTime);
					
					selectColorId = list.get(index-1).colorId;
					spinner.spinner.setSelection(selectColorId,true);
					
					selectDescription = list.get(index-1).description;
					editText.setText(selectDescription );
					
					index--;
				}
												
			}
		});
	
		//������һ����ť����		
				Button next = (Button)findViewById(R.id.button_next);
				
				next.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						//Log.d("last", "click      " + data +":"+selectColorId+":"+selectDescription);
						//data =  pageName.getText().toString().substring(5) ;
						Log.d("last", data);	
						//Point selectPoint = new Point(Long.parseLong(data),selectColorId,selectDescription);
						
						//Log.d("last", "selectPoint" + selectPoint.moment +":"+selectPoint.colorId+":"+selectPoint.description);
						//index = list.indexOf(selectPoint);
						Log.d("last", index+"of"+list.size());
						if(index==list.size()-1){
							Toast.makeText(EditPoint.this, "�ѵ��ﵱ�����һ����¼",
									Toast.LENGTH_SHORT).show();
						}else{
							
							data = list.get(index+1).moment+"";
							
							showTime = dateformatS.format( Long.parseLong(data)*1000);
							pageName.setText("�޸ļ�¼��"+ showTime);
							
							selectColorId = list.get(index+1).colorId;
							spinner.spinner.setSelection(selectColorId,true);
							
							selectDescription = list.get(index+1).description;
							editText.setText(selectDescription );
							
							index++;
						}
														
					}
				});	
		
		
		
		
	}	
	
	
	
	
}







