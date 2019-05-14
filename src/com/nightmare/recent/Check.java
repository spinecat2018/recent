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
		//check box all/color spinners/instead color spinner textView
		
		cbAll = (CheckBox)findViewById(R.id.checkBox_all);
		css = (LinearLayout)findViewById(R.id.color_spinners);
		
		cb1 = (CheckBox)findViewById(R.id.checkBox1);
		cs1 = (com.nightmare.recent.ColorSpinner)findViewById(R.id.cs_1);
		ics1 = (TextView)findViewById(R.id.ics_1);
		cs1.spinner.setSelection(0);//设置当前值
		
		
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
         // 如果选中，隐藏三个colorspinner所在的view 
                if(isChecked){ 
                	
                	css.setVisibility(View.INVISIBLE);
                	//cb1.setChecked(true);
                	//cb2.setChecked(false);
                	//cb3.setChecked(false);
                }else{ 
         //如果未选中，显示三个spinner 	
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
	//如果取消，显示替代控件，撤去spinner，隐藏选取框			
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
                //如果三个选取框都未选中，勾选cball，撤去替代控件，显示选取框1，勾选cb1，显示spinner1，
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
		
		
	//点击，撤去替代1，	显示选框1，勾选1，显示spinner1，调整spinner1，2，3，
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

	
//根据另外两个spinner的当前值，更新当前spinner的选项列表	/id changed
	
	void setupSpinner(ColorSpinner spChanged,ColorSpinner sp1,ColorSpinner sp2){
		//Log.d("recent", "-------------setup");
		
		int idC=100,id2=100,id1=100;
	//获取设置spinner的当前值，转化为根颜色表的索引号
		idC = com.nightmare.recent.ColorSpinner.colorRange.indexOf(spChanged.selectedColor());
		Log.d("recent","-------changed code:"+idC);
		
		//Log.d("recent","invisible="+View.VISIBLE);
		//Log.d("recent",""+spChanged.getVisibility());
	//如果不可见，返回
		if(spChanged.getVisibility()!=View.VISIBLE){
			Log.d("recent", "out---------");
			return;
		}
	//如果当前值与其他spinner不同，保存当前值
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
		
		
		
	//获取另外两个spinner的当前值，从list中删除，初始化spchanged

		
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
		
		
	//复制根颜色表
		List<String> cR;
		cR = new ArrayList<String>();
		cR.addAll(com.nightmare.recent.ColorSpinner.colorRange); 
	//删除重复颜色	
		cR.remove(sp1V);
		Log.d("recent","remove:"+id1);
		cR.remove(sp2V);
		Log.d("recent","remove:"+id2);
		Log.d("recent","colorlist change:"+cR.toString());
		
	//重设spchanged列表，当前值将为0
		ArrayList<ColorBlock> cL;
		cL= new ArrayList<ColorBlock>();		
		spChanged.initColors(cR,cL);
		ColorAdapter adapter = new ColorAdapter(Check.this,R.layout.color, cL);
		spChanged.resetSpinner(adapter);
		Log.d("recent","--select--"+ spChanged.selectedColor());
	//如果保存了之前的颜色，设置spchanged的当前值为对应索引号	
		if(saveColor!= ""){//已初始化
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
