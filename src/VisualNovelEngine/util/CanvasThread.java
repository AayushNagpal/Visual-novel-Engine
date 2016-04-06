package enders.visual.novel;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class CanvasThread
  extends Thread
{
  static final long FPS = 10L;
  private Game game;
  private boolean run = false;
  private SurfaceHolder surfaceHolder;
  
  public CanvasThread(SurfaceHolder paramSurfaceHolder, Game paramGame)
  {
    this.surfaceHolder = paramSurfaceHolder;
    this.game = paramGame;
  }
  
  public void run()
  {
    for (;;)
    {
      if (!this.run) {
        return;
      }
      Canvas localCanvas = null;
      long l1 = System.currentTimeMillis();
      try
      {
        localCanvas = this.surfaceHolder.lockCanvas(null);
        synchronized (this.surfaceHolder)
        {
          this.game.onDraw(localCanvas);
          if (localCanvas != null) {
            this.surfaceHolder.unlockCanvasAndPost(localCanvas);
          }
          long l2 = 100L - (System.currentTimeMillis() - l1);
          if (l2 > 0L) {
            try
            {
              sleep(l2);
            }
            catch (Exception localException) {}
          }
        }
        sleep(10L);
      }
      finally
      {
        if (localCanvas != null) {
          this.surfaceHolder.unlockCanvasAndPost(localCanvas);
        }
      }
    }
  }
  
  public void setRunning(boolean paramBoolean)
  {
    this.run = paramBoolean;
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\CanvasThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */