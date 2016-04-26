package com.csci5210.se.visualnovelengine;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Stack;

import com.csci5210.se.visualnovelengine.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Game extends Activity {

	public static Stack<DialogueType> dialogues; 

	public static Hashtable<String, String> keyvalues; 
	public static Hashtable<String, MediaPlayer> mediaPlayers; 

	public static RelativeLayout layout; 
	public static TextView textView; 
	public static ImageView imageView; 
	public static Button button1, button2; 
	public static Button button3; 
	public static EditText editText1; 
	public static String editTextName; 
	public static MenuItem[] items; 

	public static boolean can; 
	public static boolean mute; 
	public static String script; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chapter);

		/* Initialization */
		layout = (RelativeLayout)findViewById(R.id.relativeLayout1);
		textView = (TextView)findViewById(R.id.textView);
		imageView = (ImageView)findViewById(R.id.imageView);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		editText1 = (EditText)findViewById(R.id.editText1);

		editTextName = null;
		can = true;
		mediaPlayers = new Hashtable<String, MediaPlayer>();

		Intent intent = getIntent();
		mute = intent.getBooleanExtra("mute", true);
		script = intent.getStringExtra("script");
		if(script == null || script.equals(""))
			script = "script";

		keyvalues = Memory.getKeyValues(this);

		try {
			InputStream stream = getResources().openRawResource(getResources().getIdentifier("raw/" + script, null, this.getPackageName()));
			dialogues = Parser.parseXML(stream);
			stream.close();
		}
		catch(Exception e) {
			Utils.error(this, e);
			finish();
			startActivity(new Intent(this, Start.class));
		}

		if(dialogues != null && !dialogues.empty())
			Dialogue.print(Game.this);
		else {
			Utils.error(this, getString(R.string.error_reading_script));
			finish();
			startActivity(new Intent(this, Start.class));
		}

		button3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		    	if(button1.getVisibility() == View.VISIBLE || button2.getVisibility() == View.VISIBLE)
		    		return;

		    	if(editText1.getVisibility() == View.VISIBLE && editText1.getText().length() == 0)
		    		return;

		    	if(!dialogues.empty())
		    		Dialogue.print(Game.this);
		    }
		});
	}

	@Override
	public void onStop() {
		Utils.saveAll(this);

		super.onStop();
	}

	@Override
	public void onDestroy() {
		Utils.saveAll(this);

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
	    items = new MenuItem[] { menu.getItem(1), menu.getItem(2) };
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.home:
				finish();
				startActivity(new Intent(this, Start.class));
	            return true;
	        case R.id.mute:
	        	MediaPlayerHandler.setVolume(Game.mediaPlayers, 0.0f, 0.0f);
	    		Game.mute = true;
	    		items[0].setVisible(false); // mute
	    		items[1].setVisible(true); // unmute
	            return true;
	        case R.id.unmute:
	        	MediaPlayerHandler.setVolume(Game.mediaPlayers, 0.5f, 0.5f);
	    		Game.mute = false;
	    		items[0].setVisible(true); // mute
	    		items[1].setVisible(false); // unmute
	            return true;
            default:
            	return true;
	     }
	}

}
