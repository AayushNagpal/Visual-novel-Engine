
package com.csci5210.se.visualnovelengine;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Start extends Activity {

	public static boolean mute;
	public static MenuItem[] items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		/* Set background, if exists */
		int id = getResources().getIdentifier("drawable/start", null, getPackageName());
		if(id > 0)
			findViewById(R.id.relativeLayout1).setBackgroundResource(id);

		/* Gathering text */
		ListView listView = (ListView)findViewById(R.id.listView1);
		String[] menu = getResources().getStringArray(R.array.start);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
		listView.setAdapter(adapter);
		
		/* Initialization */
		mute = false;

		/* Listener */
		listView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	Intent intent;
		        switch(position) {
			       
			        case 0: // new game
			        	intent = new Intent(Start.this, Game.class);
			        	intent.putExtra("mute", mute);
			        	intent.putExtra("script", "");
			    		Start.this.startActivity(intent);
			        	break;
			        case 1: // quit
			    		Start.this.finish();
			        	break;
		        }
	        }
	    });
	}
}
