package com.fromwolfgar.ukrain.fairytales;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.BaseColumns;
import android.widget.Toast;

public class FairyTalesDbHelper extends SQLiteOpenHelper 
		implements BaseColumns {
    public static final String DB_FAIRIES = "fairies.db";
    public static final String TABLE_NAME = "tales";
    public static final String TITLES = "titles";
    public static final String DESCRIPTIONS = "descriptions";
    public static final String PICTURE = "picture";
    public static final String ABOUT = "about";
    
	public static final String PHOTOS_DIR = "UkraineFairyTales";
	
	private Context context;
	
    public FairyTalesDbHelper(Context con) {
        super(con, DB_FAIRIES, null, 1);
        this.context = con;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME 
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + TITLES + " TEXT, "                
                + DESCRIPTIONS + " TEXT, " 
                + PICTURE + " TEXT, "
                + ABOUT + " TEXT);");     
        
        String dir = String.format("%s/%s", 
            Environment.getExternalStorageDirectory().getPath(), PHOTOS_DIR);  
        copyMedia(dir);
        
        ContentValues values = new ContentValues();
                      
        values.put(TITLES, "Kings and Castels");
        values.put(DESCRIPTIONS, "Many years ago.......");
        values.put(PICTURE, dir + "/a1.png");
        values.put(ABOUT, dir + "/a1.txt");
        db.insert(TABLE_NAME, null, values);  
        
        values.put(TITLES, "Wolf and read heat");
        values.put(DESCRIPTIONS, "She was very beautifl....");
        values.put(PICTURE, dir + "/a2.png");
        values.put(ABOUT, dir + "/a2.txt");
        db.insert(TABLE_NAME, null, values);               
        
        values.put(TITLES, "Lunely Knight");
        values.put(DESCRIPTIONS, "This tales about lonely man....");
        values.put(PICTURE, dir + "/a3.png");
        values.put(ABOUT, dir + "/a3.txt");
        db.insert(TABLE_NAME, null, values);      
        
        values.put(TITLES, "Mause in glover");
        values.put(DESCRIPTIONS, "Once, one litlle mause run accross....");
        values.put(PICTURE, dir + "/a4.png");
        values.put(ABOUT, dir + "/a4.txt");
        db.insert(TABLE_NAME, null, values);  
        
      }
    
    @Override
    public void onUpgrade(
            SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }   
    
	private void copyMedia(String destDir) {
        try {
            File folder = new File(destDir);
            // проверяем, существует ли каталог UkraineFairyTales, 
            // если нет - создаем
            if (!folder.exists())
               folder.mkdir();
            AssetManager mgr = context.getAssets();
            final String assetSubDir = "photos";
            String[] files = mgr.list(assetSubDir);
            
            for (String fileName : files) {
	   			 InputStream is = mgr.open(assetSubDir + "/" + fileName); 			 
	   			 byte[] buffer = new byte[is.available()];
	   			 is.read(buffer);
	   			 is.close();
	   			 
	   			 File destFile = new File(destDir, fileName);
	   			 FileOutputStream output = new FileOutputStream(destFile);
	   			 output.write(buffer);
	   			 output.close();
	        }
        }
	    catch (Exception e) {
	        Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
	    }
	}
}