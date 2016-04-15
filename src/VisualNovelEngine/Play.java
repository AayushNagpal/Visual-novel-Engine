package com.example.test;

import com.example.test.AudioService.AudioBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;


public class Play extends Activity {
	private boolean isConnected;
	private AudioService audioService;
	MediaPlayer mPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
	}
	
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent intent = new Intent(this, AudioService.class);
		this.bindService(intent, connectToService, Context.BIND_AUTO_CREATE);
		startService(intent);
		System.out.println("isconnected "+isConnected);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		audioService.stopMusic();
	}

		protected void onStop() {
		    super.onStop();
		    // Unbind from the service
		    if (isConnected) {
		        this.unbindService(connectToService);
		        isConnected = false;
		    }
		}

		/** Defines callbacks for service binding, passed to bindService() */
	    private ServiceConnection connectToService = new ServiceConnection() {
	        @Override
	        public void onServiceConnected(ComponentName className,
	                IBinder service) {
	            // We've bound to AudioService, cast the IBinder and get AudioService instance
	        	AudioBinder binder = (AudioBinder) service;
	        	audioService = binder.getService();
	        	isConnected = true;
	        	System.out.println("isconnected22222222 "+isConnected);
	        	//mPlayer.setOnPreparedListener(this);
	        	//mPlayer.prepareAsync();
	        }
	        
			@Override
			public void onServiceDisconnected(ComponentName arg0) {
				// TODO Auto-generated method stub
				
			}
	    };

	
}
