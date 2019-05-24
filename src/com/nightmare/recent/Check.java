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
		
		//Log.d("check",cbAll.isSelected()+"?selectall");
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
                	
                }else{ 
         //如果未选中，显示三个spinner 	
                	css.setVisibility(View.VISIBLE);
                	
                	
                } 
            } 
        });


	
		
		ColorDrawable colorDrawable= (ColorDrawable) ics1.getBackground();//获取背景颜色
		final int c = colorDrawable.getColor();
		
		final String s = (String) ics1.getText();
		
	//如果取消，勾选cb1，勾选cbALL（隐藏三个spinner布局）
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
		
	//如果点击ics2，隐藏cb1，显示ics1，把ics1的颜色设为cs1的值，把文字设为“已选定”，
	//撤去cs1，撤去ics2，显示cb2，勾选cb2，显示cs2，重设cs2
	//显示ics3
		ics2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cb1.setVisibility(View.INVISIBLE);
				ics1.setVisibility(View.VISIBLE);
				ics1.setBackgroundColor(Color.parseColor(cs1.selectedColor()));
				ics1.setText("已选定");
				cs1.setVisibility(View.GONE);
				
				ics2.setVisibility(View.GONE);
				
				cb2.setVisibility(View.VISIBLE);
            	cb2.setChecked(true);
				cs2.setVisibility(View.VISIBLE);

            	setupSpinner(cs2,cs1.selectedColor(),null);
            	
            	ics3.setVisibility(View.VISIBLE);

			}
		});
	
	//如果取消cb2，勾选cb2，隐藏cb2，撤去cs2，显示ics2，恢复原背景色，恢复原文字，撤去ics1，显示cb1，显示cs1，  
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
		
		
	//如果点击ics3，隐藏cb2，显示ics2，把ics2的颜色设为cs2的值，把文字设为“已选定”，
	//撤去cs2，撤去ics3，显示cb3，勾选cb3，显示cs3，重设cs3

			ics3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					cb2.setVisibility(View.INVISIBLE);
					ics2.setVisibility(View.VISIBLE);
					ics2.setBackgroundColor(Color.parseColor(cs2.selectedColor()));
					ics2.setText("已选定");
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
		
	//如果取消cb3，勾选cb3，隐藏cb3，撤去cs3，显示ics3，撤去ics2，显示cb2，显示cs2，  
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
	            				ics1.setText("已选定");
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
			
	//定义按钮
			
			final LinearLayout scroll = (LinearLayout)findViewById(R.id.scroll);
			
			Button backOfCheck = (Button) findViewById(R.id.back_check);
			backOfCheck.setOnClickListener( new OnClickListener() {
				@Override
				public void onClick(View v) {
				//点击退出check活动
					
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
				//获取各控件当前值，生成查询条件，查询数据库，得到数据，填充scrollview
				//时间范围 查询的起始时间为当日开始时刻，截止时间为当日结束时刻
				String date1 = (String) dateBegin.getText()+"0时0分0秒";	//2019年5月7日
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
				
				long dateCode1=1000;
				try {
					dateCode1 = sdf.parse(date1).getTime()/1000;
					Log.d("check", dateCode1+"<-dateCode1");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
								
				String date2 = 	(String) dateEnd.getText()+"23时59分59秒";
				
				long dateCode2=1000;
				try {
					dateCode2 = sdf.parse(date2).getTime()/1000;
					Log.d("check", dateCode2+"<-dateCode2");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//查询 根据颜色选择结果生成不同的查询条件
				
				//创建数据库，或获得已存在的数据库
				SQLiteDatabase colorBase= colorBaseHelper.getWritableDatabase();
				Cursor cursor;
				
				
				//颜色范围  如果勾选全部颜色
				
				//Log.d("check",cbAll.isChecked()+"?check all");
				
				if(cbAll.isChecked()==true){
					Log.d("check","All");
					cursor = colorBase.rawQuery(
							"SELECT * FROM colors WHERE moment >= ? AND moment <= ?", 
							new String[]{dateCode1+"",dateCode2+""});
				}else {//如果取消勾选全部颜色，检查每个spinner的checkbox是否勾选，形成查询条件
					
					int color1=1000,color2=1000,color3=1000;
					//checkbox勾选且可见
					if(cb1.isChecked()==true&&cb1.getVisibility()==View.VISIBLE){//如果勾选，获取所选颜色，转换成对应于根颜色表内的序号
						
						color1 = com.nightmare.recent.ColorSpinner.
									colorRange.indexOf(cs1.selectedColor());
						Log.d("check","color1c="+cs1.selectedColor());
					}
					//ics可见
					if(ics1.getVisibility()==View.VISIBLE&&
							((ColorDrawable)ics1.getBackground()) .getColor()!=c){//如果勾选，获取所选颜色，转换成对应于根颜色表内的序号
						
						color1 = com.nightmare.recent.ColorSpinner.
									colorRange.indexOf(cs1.selectedColor());
										//colorRange.indexOf( ((ColorDrawable)ics1.getBackground()) .getColor() );
						
						Log.d("check","color1i="+((ColorDrawable)ics1.getBackground()) .getColor());
					}
						//ColorDrawable colorDrawable= (ColorDrawable) ics1.getBackground();//获取背景颜色
						//final int c = colorDrawable.getColor();
						
						
					
					if(cb2.isChecked()==true&&cb2.getVisibility()==View.VISIBLE){//如果勾选，获取所选颜色，转换成对应于根颜色表内的序号
						Log.d("check","color2");
						color2 = com.nightmare.recent.ColorSpinner.
									colorRange.indexOf(cs2.selectedColor());
					}
					//ics可见
					if(ics2.getVisibility()==View.VISIBLE&&
							((ColorDrawable)ics2.getBackground()) .getColor()!=c){//如果勾选，获取所选颜色，转换成对应于根颜色表内的序号
						Log.d("check","color2");
						color2 = com.nightmare.recent.ColorSpinner.
								colorRange.indexOf(cs2.selectedColor());
										//colorRange.indexOf( ((ColorDrawable)ics2.getBackground()) .getColor() );
					}
					
					if(cb3.isChecked()==true&&cb3.getVisibility()==View.VISIBLE){//如果勾选，获取所选颜色，转换成对应于根颜色表内的序号
						Log.d("check","color3");
						color3 = com.nightmare.recent.ColorSpinner.
									colorRange.indexOf(cs3.selectedColor());
					}
					//ics可见
					if(ics3.getVisibility()==View.VISIBLE&&
							((ColorDrawable)ics3.getBackground()) .getColor()!=c){//如果勾选，获取所选颜色，转换成对应于根颜色表内的序号
						Log.d("check","color3");
						color3 = com.nightmare.recent.ColorSpinner.
								colorRange.indexOf(cs3.selectedColor());
										//colorRange.indexOf( ((ColorDrawable)ics3.getBackground()) .getColor() );
					}
					
					
					cursor = colorBase.rawQuery(
							"SELECT * FROM colors WHERE moment >= ? AND moment <= ? AND (colorId = ? OR colorId = ? OR colorId = ?)", 
							new String[]{dateCode1+"",dateCode2+"",color1+"",color2+"",color3+""});
				}
				//复制查询数据
				
				ArrayList<Point> result = new ArrayList<Point> ();
				
				if (cursor.moveToFirst()) {
					do {//  遍历Cursor对象，取出数据
						long moment = cursor.getInt(cursor.getColumnIndex("moment"));			
						int colorId = cursor.getInt(cursor.getColumnIndex("colorId"));
						String description = cursor.getString(cursor.getColumnIndex("description"));
						//把数据写入当日list
						result.add(new Point(moment,colorId,description));
						//SimpleDateFormat dateformatMin = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						//String dateString = dateformatMin.format(((Point)point.getTag()).moment*1000);
						//new SimpleDateFormat("yyyy-MM-dd hh:mm").format(result.get(result.size()-1).moment*1000);
						Log.d("check", 
								"add " + (new SimpleDateFormat("yyyy-MM-dd hh:mm")).format(result.get(result.size()-1).moment*1000));
					} while (cursor.moveToNext());
				}
				//关闭cursor
				cursor.close();
				
				Log.d("check",result.size()+"select");
				
				if(result.size()==0){
									
					Toast.makeText(Check.this, "未检索到指定数据，请更改查询条件。",
							Toast.LENGTH_LONG).show();	
					return;
									
				}
				
				
				//填充
				
				TimeRange tR = new TimeRange();
				
				
				for(int i= 0;i<result.size();){
					//Log.d("check","for");
					//如果此数据点不在第一天范围内，生成空子list，插入list，把第一天的下一天赋值给第一天，数据点不移动,
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
					//如果此数据点的时刻值在第一天范围内，把此点加入list，直到加入当天所有数据点，日期后推一天
					while(i<result.size()&&
							(result.get(i).moment > com.nightmare.recent.MainActivity.findRange(dateCode1).start)&&
							(result.get(i).moment < com.nightmare.recent.MainActivity.findRange(dateCode1).end)){
						//Log.d("check","while ");
						//ArrayList<Point> l = new ArrayList<Point>();
						l.add(result.get(i));
						i++;
					}
					//Log.d("check","out while");
					//把list填入scrollview
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
				
				//插入数据点后的空白天
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

	
//根据另外两个spinner的当前值，更新当前spinner的选项列表	/id changed
	
	void setupSpinner(ColorSpinner sp,String existColor1,String existColor2){
		//Log.d("recent", "-------------setup");

	//复制根颜色表
		List<String> cR;
		cR = new ArrayList<String>();
		cR.addAll(com.nightmare.recent.ColorSpinner.colorRange); 
	//删除重复颜色	
		cR.remove(existColor1);
		Log.d("recent","remove:"+existColor1);
		
		cR.remove(existColor2);
		Log.d("recent","remove:"+existColor2);
		
		Log.d("recent","colorlist change:"+cR.toString());
		
	//重设spchanged列表，当前值将为0
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
		if(list.size()!=0){//如果list不为空
			//查找list代表的日期的秒数范围
			TimeRange tr= com.nightmare.recent.MainActivity.findRange(list.get(0).moment);
			//新建色块，插入main	
			TextView day = new TextView(this);
						
			SimpleDateFormat sdr1 = new SimpleDateFormat("MM/dd");
			day.setText(sdr1.format(new Date(list.get(0).moment*1000)));
			
			layout.addView(day);
			
			
			for(int i=0;i<list.size();i++){
				Log.d("recent","to fill "+i);
				//新建textview
				final TextView point = new TextView(this);
				//设置边界
				point.setText("|");
				point.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
				//设置tag		
				point.setTag(list.get(i));//attach Point as tag
				//设置颜色
				Log.d("recent","find color"+list.get(i).colorId+":"+list.get(i).moment);
				String s=com.nightmare.recent.ColorSpinner.colorRange.get(
						list.get(i).colorId);
				Log.d("recent","color code:"+ s);
				point.setBackgroundColor(Color.parseColor(s));//Color.parseColor("#00FF00")
				//设置weight
				int weight;
				//对于第一个色块
				if(i==0){
					weight= (int) (list.get(i).moment-tr.start);
				}else{//对于后面的色块
					weight=(int)(list.get(i).moment-list.get(i-1).moment);
				}
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( //格式
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
				//插入main
				layout.addView(point);
				Log.d("recent","fill "+ i);
			}
			Log.d("recent","for tail");
			//设置结尾textview
			int tWeight=(int)(tr.end - list.get(list.size()-1).moment);
			tail.setLayoutParams(new LinearLayout.LayoutParams( //格式
			            0, LayoutParams.MATCH_PARENT,tWeight));
		}else{//list为空
			tail.setLayoutParams(new LinearLayout.LayoutParams( //格式
					0, LayoutParams.MATCH_PARENT,1));
		}
		//设置结尾颜色
		tail.setBackgroundColor(Color.parseColor("#f6f6f6"));
		//设置结尾文字 以标明空行
		tail.setText(".");
		tail.setGravity(Gravity.CENTER_VERTICAL);
		//设置结尾点击事件
		tail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(
						Check.this, "empty",Toast.LENGTH_SHORT).show();
			}
		});
		//添加结尾
		layout.addView(tail);
		Log.d("recent","done fill");
	}
	
	
}
