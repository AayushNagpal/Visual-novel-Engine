package enders.visual.novel;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdView;

public class VisualNovelActivity
  extends Activity
  implements AdListener
{
  AdView _ad;
  private Game game;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setRequestedOrientation(0);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903040);
    this.game = ((Game)findViewById(2131099649));
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131034112, paramMenu);
    return true;
  }
  
  public void onDismissScreen(Ad paramAd) {}
  
  public void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode) {}
  
  public void onLeaveApplication(Ad paramAd) {}
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131099651: 
      this.game.restart();
      return true;
    case 2131099652: 
      this.game.load();
      return true;
    }
    this.game.save();
    return true;
  }
  
  public void onPresentScreen(Ad paramAd) {}
  
  public void onReceiveAd(Ad paramAd) {}
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\VisualNovelActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */