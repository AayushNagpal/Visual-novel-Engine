package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;

public class GifWebView extends WebView {

	public GifWebView(Context context, String path) {
		super(context);
		loadUrl(path);
		// TODO Auto-generated constructor stub
	}
	
	

}
