package enders.visual.novel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.text.TextPaint;
import java.util.ArrayList;

public class Menu
{
  private int choice;
  private boolean choiceClicked;
  private ArrayList<String> choices = new ArrayList();
  private Point clickArea = new Point();
  private boolean clicked = false;
  private ArrayList<String> jumps = new ArrayList();
  private Novel novel;
  
  public boolean click(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat3 == 0.0F)
    {
      this.clickArea.x = ((int)paramFloat1);
      this.clickArea.y = ((int)paramFloat2);
      return false;
    }
    if (paramFloat3 == 2.0F)
    {
      this.clickArea.x = ((int)paramFloat1);
      this.clickArea.y = ((int)paramFloat2);
      return false;
    }
    if (paramFloat3 == 1.0F)
    {
      this.clickArea.x = -1;
      this.clickArea.y = -1;
      if (this.choiceClicked)
      {
        if (this.choice != -1)
        {
          this.choiceClicked = false;
          this.novel.setFrame((String)getJumps().get(this.choice));
          this.choice = -1;
          this.novel.onLine = 0;
          return true;
        }
        this.choiceClicked = false;
        return false;
      }
    }
    return false;
  }
  
  public void doAction(Novel paramNovel)
  {
    if (!paramNovel.getGame().getMenus().contains(this))
    {
      this.novel = paramNovel;
      paramNovel.getGame().getMenus().add(this);
    }
  }
  
  public void draw(Canvas paramCanvas, int paramInt)
  {
    this.choice = -1;
    this.choiceClicked = false;
    for (int i = 0;; i++)
    {
      if (i >= getChoices().size()) {
        return;
      }
      Paint localPaint = new Paint();
      TextPaint localTextPaint = new TextPaint();
      localTextPaint.setColor(-1);
      localTextPaint.setTextSize(paramInt);
      localTextPaint.setAntiAlias(true);
      int j = (int)localTextPaint.measureText((String)getChoices().get(i));
      RectF localRectF = new RectF(paramCanvas.getWidth() / 2 - j / 2 - paramInt / 2, 60.0F + 2.5F * paramInt * i - paramInt * 4 / 3, paramCanvas.getWidth() / 2 + j / 2 + paramInt / 2, 60.0F + 2.5F * paramInt * i + paramInt / 2);
      localPaint.setColor(Color.parseColor("#ff0050a0"));
      if (localRectF.contains(this.clickArea.x, this.clickArea.y))
      {
        this.choiceClicked = true;
        this.choice = i;
        localPaint.setColor(Color.parseColor("#ff00a0ff"));
      }
      paramCanvas.drawRoundRect(localRectF, 10.0F, 10.0F, localPaint);
      paramCanvas.drawText((String)getChoices().get(i), paramCanvas.getWidth() / 2 - j / 2, 60.0F + 2.5F * paramInt * i, localTextPaint);
    }
  }
  
  public ArrayList<String> getChoices()
  {
    return this.choices;
  }
  
  public ArrayList<String> getJumps()
  {
    return this.jumps;
  }
  
  public boolean isClicked()
  {
    return this.clicked;
  }
  
  public void setChoices(ArrayList<String> paramArrayList)
  {
    this.choices = paramArrayList;
  }
  
  public void setClicked(boolean paramBoolean)
  {
    this.clicked = paramBoolean;
  }
  
  public void setJumps(ArrayList<String> paramArrayList)
  {
    this.jumps = paramArrayList;
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\Menu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */