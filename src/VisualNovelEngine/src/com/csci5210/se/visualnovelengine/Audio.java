package com.example.dddd;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class AudioService extends Service implements MediaPlayer.OnPreparedListener{

	public static MediaPlayer mPlayer;
	// Binder given to Gamecontroller
    private final IBinder ABinder = new AudioBinder();
    
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return ABinder;
	}

	
	public class AudioBinder extends Binder{
		AudioService getService(){
			return AudioService.this;
		}
	}
	
	//Public methods
	public void startMusic(MediaPlayer mPlayer)
	{
	    if(!mPlayer.isPlaying())
	    {
	        mPlayer.start();
	    }
	}
	public void pauseMusic(MediaPlayer mPlayer)
	{
	    if(mPlayer.isPlaying())
	    {
	        mPlayer.pause();
	    }
	}
	public void resumeMusic(MediaPlayer mPlayer)
	{
	    if(mPlayer.isPlaying()==false)
	    {
	        mPlayer.start();
	    }
	}

	public void stopMusic()
	{
	    mPlayer.stop();
	    mPlayer.release();
	    mPlayer = null;
	}
	
	public void onDestroy (MediaPlayer mPlayer)
	{
	    
	    if(mPlayer != null)
	    {
	    try{
	     mPlayer.stop();
	     mPlayer.release();
	        }finally {
	            mPlayer = null;
	        }
	    }
	}

	public boolean onError(MediaPlayer mPlayer)
	{   
	    if(mPlayer != null)
	    {
	        try{
	            mPlayer.stop();
	            mPlayer.release();
	        }finally {
	            mPlayer = null;
	        }
	    }
	    return false;
	}
	public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.a);
        mPlayer.setLooping(true); // Set looping
        mPlayer.setVolume(100,100);

    }
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//mPlayer = MediaPlayer.create(this, R.raw.a);
		mPlayer.start();
		//mPlayer.setOnPreparedListener(this);
		//mPlayer.prepareAsync(); 
		return START_STICKY;
	}
	
	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mp.start();
	}
	
	
}
