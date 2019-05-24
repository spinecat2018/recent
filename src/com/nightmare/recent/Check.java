package com.nightmare.recent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Check extends Activity implements OnDateChangedListener {

	private int year, month, day;

	private StringBuffer date = new StringBuffer("");
	//private StringBuffer date1 = new StringBuffer("");
	private TextView dateBegin,dateEnd;
	
	
	View dialogView;
	com.nightmare.recent.ColorSpinner cs1,cs2,cs3;
	
	CheckBox cbAll,cb1,cb2,cb3;
	LinearLayout css;
	TextView ics1,ics2,ics3;
	
	ColorBase colorBaseHelper= new ColorBase(this, "colorDataBase.db", null, 1);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.d("recent", "setup layout");
		setContentView(R.layout.check_setup);
		Log.d("check", "oncreate");
//date picker
		Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
		
		dateBegin = (TextView) findViewById(R.id.date_begin);
		dateBegin.setText(
				date.append(String.valueOf(year)).append("��")
            	.append(String.valueOf(month+1)).append("��")
            	.append(day).append("��"));
		date.delete(0, date.length());
		dateEnd = (TextView) findViewById(R.id.date_end);
		dateEnd.setText(
				date.append(String.valueOf(year)).append("��")
            	.append(String.valueOf(month+1)).append("��")
            	.append(day).append("��"));
		
     
	    	    
		dateBegin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialogView = View.inflate(Check.this, R.layout.date_select, null);
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(Check.this);
			    builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dd, int which) {
			        	Log.d("recent", "click");
			            if (date.length() > 0) { //����ϴμ�¼������
			                date.delete(0, date.length());
			            }
			            dateBegin.setText(
			            	date.append(String.valueOf(year)).append("��")
			            	.append(String.valueOf(month+1)).append("��")
			            	.append(day).append("��"));
			            dd.dismiss();	            
			            Log.d("recent", "after click");
			        }
			    });
			    builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dd, int which) {
			            dd.dismiss();
			            //((ViewGroup)dialogView.getParent()).removeView(dialogView);
			            Log.d("recent", "cancel");
			        }
			    });
			    
			    DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);
			    
			    AlertDialog dialog = builder.create();
			    dialog.setTitle("������ʼ����");
			    dialog.setView(dialogView);
				
				dialog.show();
				
			    //��ʼ�����ڼ����¼�
			    datePicker.init(year, month, day,Check.this);
			    Log.d("recent", "after click begin");				
			}
		});
		
		
		dateEnd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialogView = View.inflate(Check.this, R.layout.date_select, null);
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(Check.this);
			    builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dd, int which) {
			        	Log.d("recent", "click");
			            if (date.length() > 0) { //����ϴμ�¼������
			                date.delete(0, date.length());
			            }
			            dateEnd.setText(
			            	date.append(String.valueOf(year)).append("��")
			            	.append(String.valueOf(month+1)).append("��")
			            	.append(day).append("��"));
			            dd.dismiss();	            
			            Log.d("recent", "after click");
			        }
			    });
			    builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dd, int which) {
			            dd.dismiss();
			            //((ViewGroup)dialogView.getParent()).removeView(dialogView);
			            Log.d("recent", "cancel");
			        }
			    });
			    
			    DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);
			    
			    AlertDialog dialog = builder.create();
			    dialog.setTitle("������ʼ����");
			    dialog.setView(dialogView);
				
				dialog.show();
				
			    //��ʼ�����ڼ����¼�
			    datePicker.init(year, month, day,Check.this);
			    Log.d("recent", "after click begin");				
			}
		});	

		
		
//color select
		//check box all/color spinners/instead color spinner textView
		
		cbAll = (CheckBox)findViewById(R.id.checkBox_all);
		
		//Log.d("check",cbAll.isSelected()+"?selectall");
		css = (LinearLayout)findViewById(R.id.color_spinners);
		
		cb1 = (CheckBox)findViewById(R.id.checkBox1);
		cs1 = (com.nightmare.recent.ColorSpinner)findViewById(R.id.cs_1);
		ics1 = (TextView)findViewById(R.id.ics_1);
		cs1.spinner.setSelection(0);//���õ�ǰֵ
		
		
		cb2 = (CheckBox)findViewById(R.id.checkBox2);
		cs2 = (com.nightmare.recent.ColorSpinner)findViewById(R.id.cs_2);
		ics2 = (TextView)findViewById(R.id.ics_2);
		cs2.spinner.setSelection(1);
		
		cb3 = (CheckBox)findViewById(R.id.checkBox3);
		cs3 = (com.nightmare.recent.ColorSpinner)findViewById(R.id.cs_3);
		ics3 = (TextView)findViewById(R.id.ics_3);
		cs3.spinner.setSelection(2);
		
				
		cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
         // ���ѡ�У���������colorspinner���ڵ�view 
                if(isChecked){ 
                	
                	css.setVisibility(View.INVISIBLE);
                	
                }else{ 
         //���δѡ�У���ʾ����spinner 	
                	css.setVisibility(View.VISIBLE);
                	
                	
                } 
            } 
        });


	
		
		ColorDrawable colorDrawable= (ColorDrawable) ics1.getBackground();//��ȡ������ɫ
		final int c = colorDrawable.getColor();
		
		final String s = (String) ics1.getText();
		
	//���ȡ������ѡcb1����ѡcbALL����������spinner���֣�
		cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(!isChecked){ 
                	cb1.setChecked(true);
                	//css.setVisibility(View.INVISIBLE);
                	cbAll.setChecked(true);
               	
                }
            } 
        });
		
	//������ics2������cb1����ʾics1����ics1����ɫ��Ϊcs1��ֵ����������Ϊ����ѡ������
	//��ȥcs1����ȥics2����ʾcb2����ѡcb2����ʾcs2������cs2
	//��ʾics3
		ics2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cb1.setVisibility(View.INVISIBLE);
				ics1.setVisibility(View.VISIBLE);
				ics1.setBackgroundColor(Color.parseColor(cs1.selectedColor()));
				ics1.setText("��ѡ��");
				cs1.setVisibility(View.GONE);
				
				ics2.setVisibility(View.GONE);
				
				cb2.setVisibility(View.VISIBLE);
            	cb2.setChecked(true);
				cs2.setVisibility(View.VISIBLE);

            	setupSpinner(cs2,cs1.selectedColor(),null);
            	
            	ics3.setVisibility(View.VISIBLE);

			}
		});
	
	//���ȡ��cb2����ѡcb2������cb2����ȥcs2����ʾics2���ָ�ԭ����ɫ���ָ�ԭ���֣���ȥics1����ʾcb1����ʾcs1��  
		cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(!isChecked){ 
                	
                	cb2.setChecked(true);
                	cb2.setVisibility(View.INVISIBLE);
                	cs2.setVisibility(View.GONE);
                	ics2.setVisibility(View.VISIBLE);
                	
                	ics2.setBackgroundColor(c);
                	ics2.setText(s);

                	
                	ics1.setVisibility(View.GONE);
                	cb1.setVisibility(View.VISIBLE);
                	cs1.setVisibility(View.VISIBLE);
                	                	
                } 
            } 
        });
		
		
	//������ics3������cb2����ʾics2����ics2����ɫ��Ϊcs2��ֵ����������Ϊ����ѡ������
	//��ȥcs2����ȥics3����ʾcb3����ѡcb3����ʾcs3������cs3

			ics3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					cb2.setVisibility(View.INVISIBLE);
					ics2.setVisibility(View.VISIBLE);
					ics2.setBackgroundColor(Color.parseColor(cs2.selectedColor()));
					ics2.setText("��ѡ��");
					cs2.setVisibility(View.GONE);
					
					ics3.setVisibility(View.GONE);
					
					cb3.setVisibility(View.VISIBLE);
	            	cb3.setChecked(true);
					cs3.setVisibility(View.VISIBLE);

	            	setupSpinner(cs3,cs1.selectedColor(),cs2.selectedColor());

	            	
	            	ics2.setOnClickListener(new OnClickListener() {
	        			
	        			@Override
	        			public void onClick(View v) {
	        				// TODO Auto-generated method stub
	        				
	        			}
	        		});
	            	
	            	
				}
			});
		
	//���ȡ��cb3����ѡcb3������cb3����ȥcs3����ʾics3����ȥics2����ʾcb2����ʾcs2��  
			cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
	            @Override 
	            public void onCheckedChanged(CompoundButton buttonView, 
	                    boolean isChecked) { 
	                // TODO Auto-generated method stub 
	                if(!isChecked){ 
	                	
	                	cb3.setChecked(true);
	                	cb3.setVisibility(View.INVISIBLE);
	                	cs3.setVisibility(View.GONE);
	                	ics3.setVisibility(View.VISIBLE);
	                	
	                	ics3.setBackgroundColor(c);
	                	ics3.setText(s);
	                	
	                	ics2.setVisibility(View.GONE);
	                	cb2.setVisibility(View.VISIBLE);
	                	cs2.setVisibility(View.VISIBLE);
	                	
	                	
	                	ics2.setOnClickListener(new OnClickListener() {
	            			
	            			@Override
	            			public void onClick(View v) {
	            				// TODO Auto-generated method stub
	            				cb1.setVisibility(View.INVISIBLE);
	            				ics1.setVisibility(View.VISIBLE);
	            				ics1.setBackgroundColor(Color.parseColor(cs1.selectedColor()));
	            				ics1.setText("��ѡ��");
	            				cs1.setVisibility(View.GONE);
	            				
	            				ics2.setVisibility(View.GONE);
	            				
	            				cb2.setVisibility(View.VISIBLE);
	                        	cb2.setChecked(true);
	            				cs2.setVisibility(View.VISIBLE);

	                        	setupSpinner(cs2,cs1.selectedColor(),null);
	                        	
	                        	ics3.setVisibility(View.VISIBLE);

	            			}
	            		});
	                	                	
	                } 
	            } 
	        });
			
	//���尴ť
			
			final LinearLayout scroll = (LinearLayout)findViewById(R.id.scroll);
			
			Button backOfCheck = (Button) findViewById(R.id.back_check);
			backOfCheck.setOnClickListener( new OnClickListener() {
				@Override
				public void onClick(View v) {
				//����˳�check�
					
					Log.d("check", "click back");
					finish();
				}
			});


			Button check = (Button) findViewById(R.id.check);
			check.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				Log.d("check", "click check");	
				
				scroll.removeAllViews();
				
				Log.d("check", "clear");
				//��ȡ���ؼ���ǰֵ�����ɲ�ѯ��������ѯ���ݿ⣬�õ����ݣ����scrollview
				//ʱ�䷶Χ ��ѯ����ʼʱ��Ϊ���տ�ʼʱ�̣���ֹʱ��Ϊ���ս���ʱ��
				String date1 = (String) dateBegin.getText()+"0ʱ0��0��";	//2019��5��7��
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");
				
				long dateCode1=1000;
				try {
					dateCode1 = sdf.parse(date1).getTime()/1000;
					Log.d("check", dateCode1+"<-dateCode1");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
								
				String date2 = 	(String) dateEnd.getText()+"23ʱ59��59��";
				
				long dateCode2=1000;
				try {
					dateCode2 = sdf.parse(date2).getTime()/1000;
					Log.d("check", dateCode2+"<-dateCode2");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//��ѯ ������ɫѡ�������ɲ�ͬ�Ĳ�ѯ����
				
				//�������ݿ⣬�����Ѵ��ڵ����ݿ�
				SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
				Cursor cursor;
				
				
				//��ɫ��Χ  �����ѡȫ����ɫ
				
				//Log.d("check",cbAll.isChecked()+"?check all");
				
				if(cbAll.isChecked()==true){
					Log.d("check","All");
					cursor = colorBase.rawQuery(
							"SELECT * FROM colors WHERE moment >= ? AND moment <= ?", 
							new String[]{dateCode1+"",dateCode2+""});
				}else {//���ȡ����ѡȫ����ɫ�����ÿ��spinner��checkbox�Ƿ�ѡ���γɲ�ѯ����
					
					int color1=1000,color2=1000,color3=1000;
					//checkbox��ѡ�ҿɼ�
					if(cb1.isChecked()==true&&cb1.getVisibility()==View.VISIBLE){//�����ѡ����ȡ��ѡ��ɫ��ת���ɶ�Ӧ�ڸ���ɫ���ڵ����
						
						color1 = com.nightmare.recent.ColorSpinner.
									colorRange.indexOf(cs1.selectedColor());
						Log.d("check","color1c="+cs1.selectedColor());
					}
					//ics�ɼ�
					if(ics1.getVisibility()==View.VISIBLE&&
							((ColorDrawable)ics1.getBackground()) .getColor()!=c){//�����ѡ����ȡ��ѡ��ɫ��ת���ɶ�Ӧ�ڸ���ɫ���ڵ����
						
						color1 = com.nightmare.recent.ColorSpinner.
									colorRange.indexOf(cs1.selectedColor());
										//colorRange.indexOf( ((ColorDrawable)ics1.getBackground()) .getColor() );
						
						Log.d("check","color1i="+((ColorDrawable)ics1.getBackground()) .getColor());
					}
						//ColorDrawable colorDrawable= (ColorDrawable) ics1.getBackground();//��ȡ������ɫ
						//final int c = colorDrawable.getColor();
						
						
					
					if(cb2.isChecked()==true&&cb2.getVisibility()==View.VISIBLE){//�����ѡ����ȡ��ѡ��ɫ��ת���ɶ�Ӧ�ڸ���ɫ���ڵ����
						Log.d("check","color2");
						color2 = com.nightmare.recent.ColorSpinner.
									colorRange.indexOf(cs2.selectedColor());
					}
					//ics�ɼ�
					if(ics2.getVisibility()==View.VISIBLE&&
							((ColorDrawable)ics2.getBackground()) .getColor()!=c){//�����ѡ����ȡ��ѡ��ɫ��ת���ɶ�Ӧ�ڸ���ɫ���ڵ����
						Log.d("check","color2");
						color2 = com.nightmare.recent.ColorSpinner.
								colorRange.indexOf(cs2.selectedColor());
										//colorRange.indexOf( ((ColorDrawable)ics2.getBackground()) .getColor() );
					}
					
					if(cb3.isChecked()==true&&cb3.getVisibility()==View.VISIBLE){//�����ѡ����ȡ��ѡ��ɫ��ת���ɶ�Ӧ�ڸ���ɫ���ڵ����
						Log.d("check","color3");
						color3 = com.nightmare.recent.ColorSpinner.
									colorRange.indexOf(cs3.selectedColor());
					}
					//ics�ɼ�
					if(ics3.getVisibility()==View.VISIBLE&&
							((ColorDrawable)ics3.getBackground()) .getColor()!=c){//�����ѡ����ȡ��ѡ��ɫ��ת���ɶ�Ӧ�ڸ���ɫ���ڵ����
						Log.d("check","color3");
						color3 = com.nightmare.recent.ColorSpinner.
								colorRange.indexOf(cs3.selectedColor());
										//colorRange.indexOf( ((ColorDrawable)ics3.getBackground()) .getColor() );
					}
					
					
					cursor = colorBase.rawQuery(
							"SELECT * FROM colors WHERE moment >= ? AND moment <= ? AND (colorId = ? OR colorId = ? OR colorId = ?)", 
							new String[]{dateCode1+"",dateCode2+"",color1+"",color2+"",color3+""});
				}
				//���Ʋ�ѯ����
				
				ArrayList<Point> result = new ArrayList<Point> ();
				
				if (cursor.moveToFirst()) {
					do {//  ����Cursor����ȡ������
						long moment = cursor.getInt(cursor.getColumnIndex("moment"));			
						int colorId = cursor.getInt(cursor.getColumnIndex("colorId"));
						String description = cursor.getString(cursor.getColumnIndex("description"));
						//������д�뵱��list
						result.add(new Point(moment,colorId,description));
						//SimpleDateFormat dateformatMin = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						//String dateString = dateformatMin.format(((Point)point.getTag()).moment*1000);
						//new SimpleDateFormat("yyyy-MM-dd hh:mm").format(result.get(result.size()-1).moment*1000);
						Log.d("check", 
								"add " + (new SimpleDateFormat("yyyy-MM-dd hh:mm")).format(result.get(result.size()-1).moment*1000));
					} while (cursor.moveToNext());
				}
				//�ر�cursor
				cursor.close();
				
				Log.d("check",result.size()+"select");
				
				if(result.size()==0){
									
					Toast.makeText(Check.this, "δ������ָ�����ݣ�����Ĳ�ѯ������",
							Toast.LENGTH_LONG).show();	
					return;
									
				}
				
				
				//���
				
				TimeRange tR = new TimeRange();
				
				
				for(int i= 0;i<result.size();){
					//Log.d("check","for");
					//��������ݵ㲻�ڵ�һ�췶Χ�ڣ����ɿ���list������list���ѵ�һ�����һ�츳ֵ����һ�죬���ݵ㲻�ƶ�,
					ArrayList<Point> l = new ArrayList<Point>();
					
					while(result.get(i).moment > com.nightmare.recent.MainActivity.findRange(dateCode1).end){
						
						//Log.d("check","while > ");
						
						if(i!=0){
							LinearLayout day = new LinearLayout(Check.this);
							LinearLayout.LayoutParams dayLP = new LinearLayout.LayoutParams(  
						            ViewGroup.LayoutParams.MATCH_PARENT,  
						            40);  
							day.setLayoutParams(dayLP);
							day.setOrientation(LinearLayout.HORIZONTAL);
							
							//ArrayList<Point> l = new ArrayList<Point>();
							fillWithColor(l, day);
							//Log.d("check","fill");
							scroll.addView(day);
							//Log.d("check","add");
							
						}
						
						
						tR.start = com.nightmare.recent.MainActivity.findRange(dateCode1).start;
						tR.end = com.nightmare.recent.MainActivity.findRange(dateCode1).end;
						dateCode1 = TimeRange.nextDay(tR).start;
						
					}
					//��������ݵ��ʱ��ֵ�ڵ�һ�췶Χ�ڣ��Ѵ˵����list��ֱ�����뵱���������ݵ㣬���ں���һ��
					while(i<result.size()&&
							(result.get(i).moment > com.nightmare.recent.MainActivity.findRange(dateCode1).start)&&
							(result.get(i).moment < com.nightmare.recent.MainActivity.findRange(dateCode1).end)){
						//Log.d("check","while ");
						//ArrayList<Point> l = new ArrayList<Point>();
						l.add(result.get(i));
						i++;
					}
					//Log.d("check","out while");
					//��list����scrollview
					LinearLayout day = new LinearLayout(Check.this);
					LinearLayout.LayoutParams dayLP = new LinearLayout.LayoutParams(  
				            ViewGroup.LayoutParams.MATCH_PARENT,  
				            40);  
					day.setLayoutParams(dayLP);
					day.setOrientation(LinearLayout.HORIZONTAL);
					
					fillWithColor(l, day);
					scroll.addView(day);
					
					tR.start = com.nightmare.recent.MainActivity.findRange(dateCode1).start;
					tR.end = com.nightmare.recent.MainActivity.findRange(dateCode1).end;
					dateCode1 = TimeRange.nextDay(tR).start;
				}
				
				//�������ݵ��Ŀհ���
				/*while(com.nightmare.recent.MainActivity.findRange(dateCode1).end<=
						com.nightmare.recent.MainActivity.findRange(dateCode2).end){
					
					ArrayList<Point> l = new ArrayList<Point>();
						
					LinearLayout day = new LinearLayout(Check.this);
					LinearLayout.LayoutParams dayLP = new LinearLayout.LayoutParams(  
					           ViewGroup.LayoutParams.MATCH_PARENT,40);  
					day.setLayoutParams(dayLP);
					day.setOrientation(LinearLayout.HORIZONTAL);
						
						//ArrayList<Point> l = new ArrayList<Point>();
					fillWithColor(l, day);
					
					scroll.addView(day);
						
				
					tR.start = com.nightmare.recent.MainActivity.findRange(dateCode1).start;
					tR.end = com.nightmare.recent.MainActivity.findRange(dateCode1).end;
					dateCode1 = TimeRange.nextDay(tR).start;
						
					
				}  */
				
				
					
				}
			});
		
		
	}


	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
		// TODO Auto-generated method stub
		this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
       
	}

	
//������������spinner�ĵ�ǰֵ�����µ�ǰspinner��ѡ���б�	/id changed
	
	void setupSpinner(ColorSpinner sp,String existColor1,String existColor2){
		//Log.d("recent", "-------------setup");

	//���Ƹ���ɫ��
		List<String> cR;
		cR = new ArrayList<String>();
		cR.addAll(com.nightmare.recent.ColorSpinner.colorRange); 
	//ɾ���ظ���ɫ	
		cR.remove(existColor1);
		Log.d("recent","remove:"+existColor1);
		
		cR.remove(existColor2);
		Log.d("recent","remove:"+existColor2);
		
		Log.d("recent","colorlist change:"+cR.toString());
		
	//����spchanged�б���ǰֵ��Ϊ0
		ArrayList<ColorBlock> cL;
		cL= new ArrayList<ColorBlock>();		
		sp.initColors(cR,cL);
		ColorAdapter adapter = new ColorAdapter(Check.this,R.layout.color, cL);
		sp.resetSpinner(adapter);
		Log.d("recent","--select--"+ sp.selectedColor());
	
	}
	
	
	
public void fillWithColor(ArrayList<Point> list, LinearLayout layout){
		
		Log.d("recent","start fill");
		TextView tail = new TextView(this);
		if(list.size()!=0){//���list��Ϊ��
			//����list��������ڵ�������Χ
			TimeRange tr= com.nightmare.recent.MainActivity.findRange(list.get(0).moment);
			//�½�ɫ�飬����main	
			TextView day = new TextView(this);
						
			SimpleDateFormat sdr1 = new SimpleDateFormat("MM/dd");
			day.setText(sdr1.format(new Date(list.get(0).moment*1000)));
			
			layout.addView(day);
			
			
			for(int i=0;i<list.size();i++){
				Log.d("recent","to fill "+i);
				//�½�textview
				final TextView point = new TextView(this);
				//���ñ߽�
				point.setText("|");
				point.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
				//����tag		
				point.setTag(list.get(i));//attach Point as tag
				//������ɫ
				Log.d("recent","find color"+list.get(i).colorId+":"+list.get(i).moment);
				String s=com.nightmare.recent.ColorSpinner.colorRange.get(
						list.get(i).colorId);
				Log.d("recent","color code:"+ s);
				point.setBackgroundColor(Color.parseColor(s));//Color.parseColor("#00FF00")
				//����weight
				int weight;
				//���ڵ�һ��ɫ��
				if(i==0){
					weight= (int) (list.get(i).moment-tr.start);
				}else{//���ں����ɫ��
					weight=(int)(list.get(i).moment-list.get(i-1).moment);
				}
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( //��ʽ
			            0, LayoutParams.MATCH_PARENT,weight); 
				point.setLayoutParams(lp);
				
				//add click activity
				point.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						SimpleDateFormat dateformatMin = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						//String dateString = dateformatMin.format(((Point)point.getTag()).moment*1000);
						//Toast.makeText(MainActivity.this, 
						//		"\n"+ dateString+":\n"+((Point)point.getTag()).description,
						//		Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(Check.this, EditPoint.class);
						intent.putExtra("extra_data",  ((Point)point.getTag()).moment+"");
						startActivity(intent);
					}
				});
				//����main
				layout.addView(point);
				Log.d("recent","fill "+ i);
			}
			Log.d("recent","for tail");
			//���ý�βtextview
			int tWeight=(int)(tr.end - list.get(list.size()-1).moment);
			tail.setLayoutParams(new LinearLayout.LayoutParams( //��ʽ
			            0, LayoutParams.MATCH_PARENT,tWeight));
		}else{//listΪ��
			tail.setLayoutParams(new LinearLayout.LayoutParams( //��ʽ
					0, LayoutParams.MATCH_PARENT,1));
		}
		//���ý�β��ɫ
		tail.setBackgroundColor(Color.parseColor("#f6f6f6"));
		//���ý�β���� �Ա�������
		tail.setText(".");
		tail.setGravity(Gravity.CENTER_VERTICAL);
		//���ý�β����¼�
		tail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(
						Check.this, "empty",Toast.LENGTH_SHORT).show();
			}
		});
		//��ӽ�β
		layout.addView(tail);
		Log.d("recent","done fill");
	}
	
	
}
