package com.example.gametemplate;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;

public class GameController extends Activity {

	MediaPlayer mPlayer;
	Audio audio;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//bg is the sound file in the raw folder
		mPlayer = MediaPlayer.create(this, R.raw.bg);
		mPlayer.setOnErrorListener((OnErrorListener) this);
		audio.startMusic(mPlayer);
		
		
		setContentView(R.layout.activity_main);
	}
}
