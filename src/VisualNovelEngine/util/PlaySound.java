package enders.visual.novel;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

final class PlaySound
{
  private static HashSet<MediaPlayer> mpSet = new HashSet();
  
  static void play(Context paramContext, String paramString)
  {
    MediaPlayer localMediaPlayer = new MediaPlayer();
    try
    {
      AssetFileDescriptor localAssetFileDescriptor = paramContext.getAssets().openFd(paramString);
      localMediaPlayer.setDataSource(localAssetFileDescriptor.getFileDescriptor(), localAssetFileDescriptor.getStartOffset(), localAssetFileDescriptor.getLength());
      localMediaPlayer.prepare();
      mpSet.add(localMediaPlayer);
      localMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
      {
        public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
        {
          if (!paramAnonymousMediaPlayer.isLooping())
          {
            PlaySound.mpSet.remove(paramAnonymousMediaPlayer);
            paramAnonymousMediaPlayer.stop();
            paramAnonymousMediaPlayer.release();
          }
        }
      });
      localMediaPlayer.start();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        localIllegalArgumentException.printStackTrace();
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        localIllegalStateException.printStackTrace();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  static void stop()
  {
    Iterator localIterator = mpSet.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        mpSet.clear();
        return;
      }
      MediaPlayer localMediaPlayer = (MediaPlayer)localIterator.next();
      if (localMediaPlayer != null)
      {
        localMediaPlayer.stop();
        localMediaPlayer.release();
      }
    }
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\PlaySound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */