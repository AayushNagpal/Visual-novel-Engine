package com.example.gametemplate;

import android.media.MediaPlayer;
import android.widget.Toast;

public class Audio {

	public void startMusic(MediaPlayer mPlayer)
	{
	    if(!mPlayer.isPlaying())
	    {
	        mPlayer.start();
	       // length=mPlayer.getCurrentPosition();
	    }
	}
	public void pauseMusic(MediaPlayer mPlayer)
	{
	    if(mPlayer.isPlaying())
	    {
	        mPlayer.pause();
	       // length=mPlayer.getCurrentPosition();
	    }
	}
	public void resumeMusic(MediaPlayer mPlayer)
	{
	    if(mPlayer.isPlaying()==false)
	    {
	        mPlayer.start();
	    }
	}

	public void stopMusic(MediaPlayer mPlayer)
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
	
	
}
