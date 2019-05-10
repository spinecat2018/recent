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
				date.append(String.valueOf(year)).append("年")
            	.append(String.valueOf(month+1)).append("月")
            	.append(day).append("日"));
		date.delete(0, date.length());
		dateEnd = (TextView) findViewById(R.id.date_end);
		dateEnd.setText(
				date.append(String.valueOf(year)).append("年")
            	.append(String.valueOf(month+1)).append("月")
            	.append(day).append("日"));
		
     
	    	    
		dateBegin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialogView = View.inflate(Check.this, R.layout.date_select, null);
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(Check.this);
			    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dd, int which) {
			        	Log.d("recent", "click");
			            if (date.length() > 0) { //清除上次记录的日期
			                date.delete(0, date.length());
			            }
			            dateBegin.setText(
			            	date.append(String.valueOf(year)).append("年")
			            	.append(String.valueOf(month+1)).append("月")
			            	.append(day).append("日"));
			            dd.dismiss();	            
			            Log.d("recent", "after click");
			        }
			    });
			    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dd, int which) {
			            dd.dismiss();
			            //((ViewGroup)dialogView.getParent()).removeView(dialogView);
			            Log.d("recent", "cancel");
			        }
			    });
			    
			    DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);
			    
			    AlertDialog dialog = builder.create();
			    dialog.setTitle("设置起始日期");
			    dialog.setView(dialogView);
				
				dialog.show();
				
			    //初始化日期监听事件
			    datePicker.init(year, month, day,Check.this);
			    Log.d("recent", "after click begin");				
			}
		});
		
		
		dateEnd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialogView = View.inflate(Check.this, R.layout.date_select, null);
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(Check.this);
			    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dd, int which) {
			        	Log.d("recent", "click");
			            if (date.length() > 0) { //清除上次记录的日期
			                date.delete(0, date.length());
			            }
			            dateEnd.setText(
			            	date.append(String.valueOf(year)).append("年")
			            	.append(String.valueOf(month+1)).append("月")
			            	.append(day).append("日"));
			            dd.dismiss();	            
			            Log.d("recent", "after click");
			        }
			    });
			    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dd, int which) {
			            dd.dismiss();
			            //((ViewGroup)dialogView.getParent()).removeView(dialogView);
			            Log.d("recent", "cancel");
			        }
			    });
			    
			    DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);
			    
			    AlertDialog dialog = builder.create();
			    dialog.setTitle("设置起始日期");
			    dialog.setView(dialogView);
				
				dialog.show();
				
			    //初始化日期监听事件
			    datePicker.init(year, month, day,Check.this);
			    Log.d("recent", "after click begin");				
			}
		});	

		
		
//color select
		
		cbAll = (CheckBox)findViewById(R.id.checkBox_all);
		css = (LinearLayout)findViewById(R.id.color_spinners);
		
		cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(isChecked){ 
                	
                	css.setVisibility(View.INVISIBLE);
                	//cb1.setChecked(true);
                	//cb2.setChecked(false);
                	//cb3.setChecked(false);
                }else{ 
                	
                	css.setVisibility(View.VISIBLE);
                	
                	Log.d("recent", "show 1:"+cs1.selectColorCode);
                	Log.d("recent", "show 2:"+cs2.selectColorCode);
                	
                	setupSpinner(cs1,cs2,cs3);
                	
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
		
		cb1 = (CheckBox)findViewById(R.id.checkBox1);
		cs1 = (com.nightmare.recent.ColorSpinner)findViewById(R.id.cs_1);
		ics1 = (TextView)findViewById(R.id.ics_1);
		//Spinner spinner1 = (Spinner)cs1.findViewById(R.id.spinner0);
		//spinner1.setId(R.id.spinner0);
				
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
                	if(!cb1.isChecked()&&!cb2.isChecked()&&!cb3.isChecked()==true){
                	//if(!cb1.isChecked()&&!cb2.isChecked()==true){
                		cbAll.setChecked(true);
                		cb1.setChecked(true);
                		ics1.setVisibility(View.GONE);
                		cs1.setVisibility(View.VISIBLE);
                		cb1.setVisibility(View.VISIBLE);
                		
                	}
                }
            } 
        });
		
		
		
		ics1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ics1.setVisibility(View.GONE);
            	cs1.setVisibility(View.VISIBLE);
            	
            	setupSpinner(cs1,cs2,cs3);
            	
            	cb1.setVisibility(View.VISIBLE);
            	
            	cb1.setChecked(true);
            
			}
		});
		
		
		
//spinner2		
		cb2 = (CheckBox)findViewById(R.id.checkBox2);
		cs2 = (com.nightmare.recent.ColorSpinner)findViewById(R.id.cs_2);
		ics2 = (TextView)findViewById(R.id.ics_2);
		
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
                } 
            } 
        });
		
		ics2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ics2.setVisibility(View.GONE);
            	cs2.setVisibility(View.VISIBLE);
            	
            	setupSpinner(cs2,cs1,cs3);
            	
            	cb2.setVisibility(View.VISIBLE);
            	cb2.setChecked(true);
			}
		});
		
//spinner3		
		
		cb3 = (CheckBox)findViewById(R.id.checkBox3);
		cs3 = (com.nightmare.recent.ColorSpinner)findViewById(R.id.cs_3);
		ics3 = (TextView)findViewById(R.id.ics_3);
		
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
                } 
            } 
        });
		
		ics3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ics3.setVisibility(View.GONE);
            	cs3.setVisibility(View.VISIBLE);
            	
            	setupSpinner(cs3,cs1,cs2);
            	
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
	
	void setupSpinner(ColorSpinner spChanged,ColorSpinner sp1,ColorSpinner sp2){
		
		//sp1获取另外两个spinner的当前值，从list中删除，初始化
		//sp2同样
		Log.d("recent", "setup");
		String spChangedV,sp1V = null,sp2V = null;
		
		spChangedV = spChanged.selectedColor();
		
		
		if(sp1.getVisibility()==View.VISIBLE){
			sp1V = sp1.selectedColor();
		}
		
		if(sp2.getVisibility()==View.VISIBLE){
			sp2V = sp2.selectedColor();
		}
		
		
		Log.d("recent","changed code:"+spChanged.selectColorCode);
		Log.d("recent","code 1:"+sp1.selectColorCode);
	//	Log.d("recent",sp2V+" 2");
		//ArrayList<ColorBlock> cL = (ArrayList<ColorBlock>) com.nightmare.recent.ColorSpinner.colorList.clone();
		
		ArrayList<ColorBlock> cL;
		cL= new ArrayList<ColorBlock>();
		//获取颜色表
		List<String> cR;
		cR = new ArrayList<String>();
		cR.addAll(com.nightmare.recent.ColorSpinner.colorRange); 
		
		Log.d("recent","colorlist:"+cR.toString());
		
		cR.remove(sp1V);
		Log.d("recent","remove:"+sp1V);
		cR.remove(sp2V);
		Log.d("recent","remove:"+sp2V);
		Log.d("recent","colorlist change:"+cR.toString());
		
		
		spChanged.initColors(cR,cL);
		ColorAdapter adapter = new ColorAdapter(Check.this,R.layout.color, cL);
		spChanged.resetSpinner(adapter);
		
		//Spinner spinner = (Spinner) spChanged.findViewById(idC);
		//spinner.setAdapter(adapter);
		//把颜色值指向第一项
		spChanged.selectColorCode=spChanged.selectedColor();
		
		//ColorBlock cB = new ColorBlock(spChanged.selectColorCode);
	
		//spinner.setSelection(cL.indexOf(cB));
		
		
		Log.d("recent","change code to:"+spChanged.selectColorCode);
		Log.d("recent","code 1:"+sp1.selectColorCode);
		//Log.d("recent",sp2V+" 2-");
		
	}
	
		
}
