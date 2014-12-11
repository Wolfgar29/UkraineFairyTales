package com.fromwolfgar.ukrain.fairytales;

import java.util.ArrayList;
import com.fromwolfgar.entity.ModelObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements  

View.OnClickListener,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

	private ArrayList<ModelObject> list;
	private CustomAdapter adapter;
	private ListView listView;
	private Button bAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		bAdd=(Button)findViewById(R.id.bAdd);
		bAdd.setOnClickListener(this);

		listView = (ListView)findViewById(R.id.listView);
		
		list = FairyTalesDbManager.getInstance(this).getContacts();  
        adapter = new CustomAdapter(this, list);
        listView.setAdapter(adapter);    

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, 
			View arg1, int position, long arg3) {
		
	/*	ModelObject item = adapter.getItem(position);   	
		ModelObject.selectedItem = item;
	    Intent intent = new Intent();
	    intent.setClass(this, DetailsActivity.class);
	    startActivity(intent);   */
	     
	 // Позиция элемента, по которому кликнули
		String itemname = new Integer(position).toString();

		// Создаем новый intent
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, ViewSecondActivity.class);
		Bundle b = new Bundle();
		b.putString("defStrID", itemname); // defStrID - уникальная
											// строка, отправим itemname
											// в другое Activity
		intent.putExtras(b);
		startActivity(intent); // запускаем intent
	    
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		ModelObject item = adapter.getItem(pos);
		openDeleteDialog(item);
		return false;
	}
	


	private void openDeleteDialog(final ModelObject item) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage(
	    		String.format("Delete Fairy Tale %s?", item.getTitle()));
	    builder.setPositiveButton("Delete", 
	    		new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	        	FairyTalesDbManager.getInstance(
	        			getApplicationContext()).deleteFairyTale(item.getID());
	        	refreshList();
	        }
	    });
	    
	    builder.setNegativeButton("Cancel", 
	    		new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	            dialog.cancel();
	        }
	    });
	    
	    builder.setCancelable(false);
	    builder.create();
	    builder.show();
	}

	private void refreshList() {
	    list = FairyTalesDbManager.getInstance(this).getContacts();
	    adapter = new CustomAdapter(this, list);
	    listView.setAdapter(adapter); 
	}

	@Override
	public void onClick(View v) {
		
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		
		Toast.makeText(getBaseContext(), "Loading......... " ,
							Toast.LENGTH_SHORT).show();
		
	}
	
}