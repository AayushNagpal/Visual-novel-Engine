package com.csci5210.se.visualnovelengine;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerHandler {

	@SuppressWarnings("rawtypes")
	public static boolean stop(Hashtable<String, MediaPlayer> mediaPlayers) {
		boolean done = false;

	    Iterator<Entry<String, MediaPlayer>> it = mediaPlayers.entrySet().iterator();
	    MediaPlayer mediaPlayer;

	    while(it.hasNext()) {
	    	mediaPlayer = (MediaPlayer)(((Map.Entry)it.next()).getValue());
			if(mediaPlayer != null) {
				done = true;
				mediaPlayer.reset();
			}
	    }

	    return done;
	}

	public static boolean stop(Hashtable<String, MediaPlayer> mediaPlayers, String resource) {
		MediaPlayer mediaPlayer = mediaPlayers.get(resource);

		if(mediaPlayer == null) {
			Utils.print(mediaPlayers);
			return false;
		}

		mediaPlayer.reset();

		return !mediaPlayer.isPlaying();
	}


	@SuppressWarnings("rawtypes")
	public static void setVolume(Hashtable<String, MediaPlayer> mediaPlayers, float leftVolume, float rightVolume) {
	    Iterator<Entry<String, MediaPlayer>> it = mediaPlayers.entrySet().iterator();
	    MediaPlayer mediaPlayer;

	    while(it.hasNext()) {
	    	mediaPlayer = (MediaPlayer)(((Map.Entry)it.next()).getValue());
			if(mediaPlayer != null)
				mediaPlayer.setVolume(leftVolume, rightVolume);
	    }
	}

	public static boolean create(Hashtable<String, MediaPlayer> mediaPlayers, Context context, int id, String resource, boolean loop, float leftVolume, float rightVolume, boolean start) {
		MediaPlayer mediaPlayer = MediaPlayer.create(context, id);
		if(mediaPlayer != null) {
			mediaPlayer.setLooping(loop);
			mediaPlayer.setVolume(leftVolume, rightVolume);
			if(start)
				mediaPlayer.start();
			mediaPlayers.put(resource, mediaPlayer);
			return true;
		}

		return false;
	}

}
