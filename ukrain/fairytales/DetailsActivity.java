package com.fromwolfgar.ukrain.fairytales;

import com.fromwolfgar.entity.ModelObject;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	
	private TextView textView;
	private String about;
	private ImageView imageViewPicture;
	private WebView myWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		
		imageViewPicture=(ImageView)findViewById(R.id.imageViewPicture);
		textView=(TextView)findViewById(R.id.textView);
		myWebView = (WebView) findViewById(R.id.webview);
	

		ModelObject item = ModelObject.selectedItem;
		
		textView.setText(item.getTitle());
		
		
		
		
	}
}
