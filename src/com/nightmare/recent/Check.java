package com.nightmare.recent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
                	//cb1.setChecked(true);
                	//cb2.setChecked(false);
                	//cb3.setChecked(false);
                }else{ 
         //���δѡ�У���ʾ����spinner 	
                	css.setVisibility(View.VISIBLE);
                	
                	//Log.d("recent", "show 1:"+cs1.selectedColor());
                	//Log.d("recent", "show 2:"+cs2.selectedColor());
                	//Log.d("recent", "ALL");
                //	setupSpinner(cs1,cs2,cs3);
                	
                //	setupSpinner(cs2,cs1,cs3);
                	
               // 	setupSpinner(cs3,cs1,cs2);
                	
                	//cb1.setVisibility(View.VISIBLE);
                	//cs1.setVisibility(View.VISIBLE);
                	//ics1.setVisibility(View.GONE);
                	//cb1.setChecked(true);
                	//cb2.setChecked(false);
                	//cb3.setChecked(false);
                } 
            } 
        });

//spinner1		
		
		
		//Spinner spinner1 = (Spinner)cs1.findViewById(R.id.spinner0);
		//spinner1.setId(R.id.spinner0);
	//���ȡ������ʾ����ؼ�����ȥspinner������ѡȡ��			
		cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(!isChecked){ 
                	ics1.setVisibility(View.VISIBLE);
                	
                	cs1.setVisibility(View.GONE);
                	cb1.setVisibility(View.INVISIBLE);
                	Log.d("recent", "uncheck1");
                //�������ѡȡ��δѡ�У���ѡcball����ȥ����ؼ�����ʾѡȡ��1����ѡcb1����ʾspinner1��
                	if(!cb1.isChecked()&&!cb2.isChecked()&&!cb3.isChecked()==true){
                	//if(!cb1.isChecked()&&!cb2.isChecked()==true){
                		cbAll.setChecked(true);
                		ics1.setVisibility(View.GONE);
                		cb1.setVisibility(View.VISIBLE);
                		cb1.setChecked(true);
                		cs1.setVisibility(View.VISIBLE);
                	}
                	setupSpinner(cs2,cs1,cs3);
                	setupSpinner(cs3,cs1,cs2);
                }
            } 
        });
		
		
	//�������ȥ���1��	��ʾѡ��1����ѡ1����ʾspinner1������spinner1��2��3��
		ics1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ics1.setVisibility(View.GONE);
				cb1.setVisibility(View.VISIBLE);
            	cb1.setChecked(true);
            	cs1.setVisibility(View.VISIBLE);
            	
            	Log.d("recent", "1:");
            	setupSpinner(cs1,cs2,cs3);
            	
            	setupSpinner(cs2,cs1,cs3);
            	
            	setupSpinner(cs3,cs1,cs2);            
			}
		});
		
//spinner2		
		
		//Spinner spinner2 = (Spinner)cs2.findViewById(R.id.spinner0);
		//spinner2.setSelection(0);
		
		cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(!isChecked){ 
                	ics2.setVisibility(View.VISIBLE);
                	cs2.setVisibility(View.GONE);
                	cb2.setVisibility(View.INVISIBLE);
                	Log.d("recent", "uncheck2");
                	
                	if(!cb1.isChecked()&&!cb2.isChecked()&&!cb3.isChecked()==true){
                	//if(!cb1.isChecked()&&!cb2.isChecked()==true){
                		cbAll.setChecked(true);
                		cb2.setChecked(true);
                		ics2.setVisibility(View.GONE);
                		cs2.setVisibility(View.VISIBLE);
                		cb2.setVisibility(View.VISIBLE);
                	}
                	
                	setupSpinner(cs1,cs2,cs3);
                	setupSpinner(cs3,cs1,cs2);
                } 
            } 
        });
		
		ics2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ics2.setVisibility(View.GONE);
            	cs2.setVisibility(View.VISIBLE);
            	Log.d("recent", "2:");
            	setupSpinner(cs2,cs1,cs3);
            	
            	setupSpinner(cs1,cs2,cs3);
            	
            	setupSpinner(cs3,cs1,cs2);
            	
            	cb2.setVisibility(View.VISIBLE);
            	cb2.setChecked(true);
			}
		});
		
//spinner3		
		
		
		
		cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(!isChecked){ 
                	
                	ics3.setVisibility(View.VISIBLE);
                	cs3.setVisibility(View.GONE);
                	cb3.setVisibility(View.INVISIBLE);
                	Log.d("recent", "uncheck");
                	if(!cb1.isChecked()&&!cb2.isChecked()&&!cb3.isChecked()==true){
                		cbAll.setChecked(true);
                		cb3.setChecked(true);
                		cs3.setVisibility(View.VISIBLE);
                		ics3.setVisibility(View.GONE);
                		cb3.setVisibility(View.VISIBLE);
                	}
                	
                	setupSpinner(cs1,cs2,cs3);
                	setupSpinner(cs2,cs1,cs3);
                	
                	
                } 
            } 
        });
		
		ics3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ics3.setVisibility(View.GONE);
            	cs3.setVisibility(View.VISIBLE);
            	
            	Log.d("recent", "3:");
            	setupSpinner(cs3,cs1,cs2);
            	
            	setupSpinner(cs1,cs2,cs3);
            	
            	setupSpinner(cs2,cs1,cs3);
            	
            	cb3.setVisibility(View.VISIBLE);
            	cb3.setChecked(true);
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
	
	void setupSpinner(ColorSpinner spChanged,ColorSpinner sp1,ColorSpinner sp2){
		//Log.d("recent", "-------------setup");
		
		int idC=100,id2=100,id1=100;
	//��ȡ����spinner�ĵ�ǰֵ��ת��Ϊ����ɫ���������
		idC = com.nightmare.recent.ColorSpinner.colorRange.indexOf(spChanged.selectedColor());
		Log.d("recent","-------changed code:"+idC);
		
		//Log.d("recent","invisible="+View.VISIBLE);
		//Log.d("recent",""+spChanged.getVisibility());
	//������ɼ�������
		if(spChanged.getVisibility()!=View.VISIBLE){
			Log.d("recent", "out---------");
			return;
		}
	//�����ǰֵ������spinner��ͬ�����浱ǰֵ
		String color1="",color2="";
		if(sp1.getVisibility()==View.VISIBLE){
			color1=sp1.selectedColor();
		}
		if(sp2.getVisibility()==View.VISIBLE){
			color2=sp2.selectedColor();
		}
		
		String saveColor = "";
		
		if(!spChanged.selectedColor().equals(color1) && 
				!spChanged.selectedColor().equals(color2)){
			
			saveColor = spChanged.selectedColor();
		}
		
		
		
	//��ȡ��������spinner�ĵ�ǰֵ����list��ɾ������ʼ��spchanged

		
		String spChangedV,sp1V = null,sp2V = null;
		
		spChangedV = spChanged.selectedColor();
		
		
		if(sp1.getVisibility()==View.VISIBLE){
			sp1V = sp1.selectedColor();
			id1 = com.nightmare.recent.ColorSpinner.colorRange.indexOf(sp1.selectedColor());
			Log.d("recent","code 1:"+id1);
		}
		
		if(sp2.getVisibility()==View.VISIBLE){
			sp2V = sp2.selectedColor();
			id2 = com.nightmare.recent.ColorSpinner.colorRange.indexOf(sp2.selectedColor());
			Log.d("recent","code 2:"+id2);
		}
		
		
	//���Ƹ���ɫ��
		List<String> cR;
		cR = new ArrayList<String>();
		cR.addAll(com.nightmare.recent.ColorSpinner.colorRange); 
	//ɾ���ظ���ɫ	
		cR.remove(sp1V);
		Log.d("recent","remove:"+id1);
		cR.remove(sp2V);
		Log.d("recent","remove:"+id2);
		Log.d("recent","colorlist change:"+cR.toString());
		
	//����spchanged�б���ǰֵ��Ϊ0
		ArrayList<ColorBlock> cL;
		cL= new ArrayList<ColorBlock>();		
		spChanged.initColors(cR,cL);
		ColorAdapter adapter = new ColorAdapter(Check.this,R.layout.color, cL);
		spChanged.resetSpinner(adapter);
		Log.d("recent","--select--"+ spChanged.selectedColor());
	//���������֮ǰ����ɫ������spchanged�ĵ�ǰֵΪ��Ӧ������	
		if(saveColor!= ""){//�ѳ�ʼ��
			spChanged.spinner.setSelection(cR.indexOf(saveColor)); ;
			
		}
		
		Log.d("recent","--select--"+ spChanged.selectedColor());
		
		Log.d("recent","colorlist change:"+cR.toString());
		
		//Log.d("recent","spChanged.selectedColor():"+spChanged.selectedColor());
		
		idC = cR.indexOf(spChanged.selectColorCode);
		Log.d("recent","change code to:"+idC);

		id1 = cR.indexOf(sp1.selectedColor());
		Log.d("recent","code 1:"+id1);
		

		id2 = cR.indexOf(sp2.selectedColor());
		Log.d("recent","code 2:"+id2);		
	}
	
		
}
