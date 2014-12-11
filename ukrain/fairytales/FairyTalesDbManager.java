package com.fromwolfgar.ukrain.fairytales;

import java.util.ArrayList;

import com.fromwolfgar.entity.ModelObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FairyTalesDbManager {
	private static FairyTalesDbManager instance;
	public static FairyTalesDbManager getInstance(Context con) {
		if (instance == null) {
			instance = new FairyTalesDbManager(con);		
		}
		return instance;
	}
	
	private SQLiteDatabase db;
	private final Context context;
	private FairyTalesDbHelper dbHelper;
	
	// проекция - массив с именами полей таблицы people
	private String[] columns = new String[] { 
			FairyTalesDbHelper.TITLES, 
			FairyTalesDbHelper.DESCRIPTIONS, 
			FairyTalesDbHelper.DESCRIPTIONS,
			FairyTalesDbHelper.ABOUT };
	
	public FairyTalesDbManager(Context c) {
		context = c;
		dbHelper = new FairyTalesDbHelper(context);
	}
    
	// открытие БД
	public void open() {
		db = dbHelper.getWritableDatabase();
	}
	
	// Закрытие БД
	public void close() {
		db.close();
	}
    
	// получение полного списка сказок
	public ArrayList<ModelObject> getContacts() {
		open();
		Cursor cursor = db.query(FairyTalesDbHelper.TABLE_NAME, null,
				null, null, null, null, null);
		ArrayList<ModelObject> list = new ArrayList<ModelObject>();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				ModelObject item = new ModelObject();
				item.setID(cursor.getInt(0));
				item.setTitle(cursor.getString(1));
				item.setDesc(cursor.getString(2));
				item.setImageId(cursor.getString(3));
				item.setAbout(cursor.getString(4));
				
				list.add(item);
			} 
			while (cursor.moveToNext());
		}	
		close();
		return list;
	}
	
	// добавление нового контакта в список
	public int addFairyTale(ModelObject entity) {
		open();
		ContentValues values = new ContentValues(4);

		values.put(FairyTalesDbHelper.TITLES, entity.getTitle());
		values.put(FairyTalesDbHelper.DESCRIPTIONS, entity.getDesc());
		values.put(FairyTalesDbHelper.PICTURE, entity.getImageId());
		values.put(FairyTalesDbHelper.ABOUT, entity.getAbout());
		
		int res = (int)db.insertOrThrow(
				FairyTalesDbHelper.TABLE_NAME, null, values);
		close();
		return res;
	}

	// обновление существующей сказки
	public int updateFairyTale(ModelObject entity) {
		open();
		ContentValues values = new ContentValues(4);

		values.put(FairyTalesDbHelper.TITLES, entity.getTitle());
		values.put(FairyTalesDbHelper.DESCRIPTIONS, entity.getDesc());
		values.put(FairyTalesDbHelper.PICTURE, entity.getImageId());
		values.put(FairyTalesDbHelper.ABOUT, entity.getAbout());
		
		String where = String.format("%s=%d", 
				FairyTalesDbHelper._ID, entity.getID());
		int res = db.update(FairyTalesDbHelper.TABLE_NAME, values, where, null);
		close();
		return res;
	}
	
	// удаление контакта
	public int deleteFairyTale(int fairytalesID) {
		open();
		String where = String.format("%s=%d", 
				FairyTalesDbHelper._ID, fairytalesID);
		int res = db.delete(FairyTalesDbHelper.TABLE_NAME, where, null);
		close();
		return res;
	}
}