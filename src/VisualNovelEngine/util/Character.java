package enders.visual.novel;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class Character
{
  private float alpha = 1.0F;
  private String bimapString;
  private String color;
  private Bitmap image;
  private String name;
  private Point pos = new Point(0, 0);
  boolean visible = true;
  
  public Character(String paramString1, String paramString2)
  {
    setName(paramString1);
    this.color = paramString2;
  }
  
  public void changeImage(Novel paramNovel, String paramString)
  {
    setBimapString(paramString);
    try
    {
      InputStream localInputStream2 = paramNovel.assetManager.open(getBimapString());
      localInputStream1 = localInputStream2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        InputStream localInputStream1 = null;
      }
    }
    setImage(BitmapFactory.decodeStream(localInputStream1));
  }
  
  public void doAction(Novel paramNovel, ScriptLine paramScriptLine)
  {
    if (paramScriptLine.getAction().equals("image")) {
      if (!paramScriptLine.getSrc().equals(""))
      {
        this.visible = true;
        setBimapString(((Image)Novel.getImages().get(paramScriptLine.getSrc())).getImage());
      }
    }
    try
    {
      localInputStream2 = paramNovel.assetManager.open(getBimapString());
      localInputStream1 = localInputStream2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        InputStream localInputStream1 = null;
      }
    }
    setImage(BitmapFactory.decodeStream(localInputStream1));
    if (!paramNovel.getGame().getActors().contains(this)) {
      paramNovel.getGame().getActors().add(this);
    }
    paramNovel.onLine = (1 + paramNovel.onLine);
    while (!paramScriptLine.getAction().equals("say"))
    {
      InputStream localInputStream2;
      return;
      this.visible = false;
      paramNovel.onLine = (1 + paramNovel.onLine);
      return;
    }
    paramNovel.getGame().dialogVisible = true;
    paramNovel.getGame().text(paramScriptLine.getValue(), Color.parseColor(this.color), getName());
    paramNovel.waitForClick = true;
  }
  
  public String getBimapString()
  {
    return this.bimapString;
  }
  
  public Bitmap getImage()
  {
    return this.image;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setBimapString(String paramString)
  {
    this.bimapString = paramString;
  }
  
  public void setImage(Bitmap paramBitmap)
  {
    this.image = paramBitmap;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\Character.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */