package com.nightmare.recent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
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
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.d("recent", "setup layout");
		setContentView(R.layout.check_setup);
		Log.d("recent", "oncreate");
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
	
		
}
