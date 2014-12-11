package com.fromwolfgar.ukrain.fairytales;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fromwolfgar.entity.ModelObject;

public class CustomAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private ArrayList<ModelObject> data;
    private Context context;
    
	public CustomAdapter(Context context, ArrayList<ModelObject> data) {
		
		this.context = context;
		this.data = data;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}
	
	@Override
	public ModelObject getItem(int position) {
		return data.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		
        if (convertView == null) {
        	view = inflater.inflate(R.layout.list_item, null);
        }
        final ModelObject item = data.get(position);
        
		ImageView image = (ImageView)view.findViewById(R.id.icon);
		TextView title = (TextView)view.findViewById(R.id.title);
		TextView desc = (TextView)view.findViewById(R.id.desc);
		
		image.setImageDrawable(Drawable.createFromPath(item.getImageId()));	
		title.setText(item.getTitle());
		desc.setText(item.getDesc());
		
		return view;
	}
}
