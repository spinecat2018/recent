package com.nightmare.recent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class ColorBase extends SQLiteOpenHelper {
	
	
	static String colorTableCode;
	
	private Context mContext;
	
	public ColorBase(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
			mContext = context;
			Log.d("recent","construct");
			}
	
	
	@Override
	public void onCreate(SQLiteDatabase colorBase) {
		Log.d("recent","create");
		
		colorTableCodeGen("colors");
		colorBase.execSQL(colorTableCode);
		
		
		Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		

	}

	public  void colorTableCodeGen(String tableName){
		
		colorTableCode = "create table "+ tableName + " ("
				+ "id integer primary key autoincrement, "
				+ "moment integer, "
				+ "colorId integer, "
				+ "description text)";
	}
	
}
