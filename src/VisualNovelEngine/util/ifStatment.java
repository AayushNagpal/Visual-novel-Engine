package enders.visual.novel;

import android.graphics.Color;
import android.util.Log;
import java.util.ArrayList;
import java.util.Hashtable;

public class ifStatment
{
  private ArrayList<ScriptLine> list = new ArrayList();
  private Novel novel;
  private int onLine = 0;
  
  public void doAction(Novel paramNovel)
  {
    if (this.novel == null)
    {
      this.novel = paramNovel;
      playNovel();
      return;
    }
    playNovel();
  }
  
  public ArrayList<ScriptLine> getList()
  {
    return this.list;
  }
  
  public void playNovel()
  {
    ScriptLine localScriptLine;
    if ((!this.novel.ignoreClicks) && (!this.novel.paused) && (!this.novel.waitForClick))
    {
      Log.v("Ifstatement", this.novel.getFrame() + " Line " + this.onLine + " " + this.list.size());
      if (this.onLine >= this.list.size()) {
        break label471;
      }
      localScriptLine = (ScriptLine)this.list.get(this.onLine);
      if (!localScriptLine.getType().equals("actor")) {
        break label259;
      }
      if (localScriptLine.getName() == null) {
        break label218;
      }
      if (localScriptLine.getName().equals("")) {
        break label177;
      }
      Object localObject3 = Novel.getActors().get(localScriptLine.getName());
      if ((localObject3 instanceof Character)) {
        ((Character)localObject3).doAction(this.novel, localScriptLine);
      }
    }
    label176:
    label177:
    label218:
    label259:
    Object localObject1;
    do
    {
      Object localObject2;
      do
      {
        do
        {
          break label176;
          do
          {
            return;
          } while (!localScriptLine.getAction().contains("say"));
          this.novel.getGame().text(localScriptLine.getValue(), Color.parseColor("#FF000000"), null);
          this.novel.waitForClick = true;
          return;
        } while (!localScriptLine.getAction().contains("say"));
        this.novel.getGame().text(localScriptLine.getValue(), Color.parseColor("#FF000000"), null);
        this.novel.waitForClick = true;
        return;
        if (localScriptLine.getType().equals("function"))
        {
          String str = localScriptLine.getAction();
          if (str.equals("changeBG"))
          {
            this.novel.changeBG(localScriptLine.getSrc(), localScriptLine.getValue());
            return;
          }
          if (str.equals("changeFlag"))
          {
            this.onLine = (1 + this.onLine);
            return;
          }
          if (str.equals("jump"))
          {
            this.novel.setFrame(localScriptLine.getValue());
            this.novel.onLine = 0;
            this.onLine = 0;
            return;
          }
          this.onLine = (1 + this.onLine);
          return;
        }
        if (!localScriptLine.getType().equals("menu")) {
          break;
        }
        localObject2 = Novel.getActors().get(localScriptLine.getAction());
      } while (!(localObject2 instanceof Menu));
      ((Menu)localObject2).doAction(this.novel);
      return;
      if (!localScriptLine.getType().equals("if")) {
        break;
      }
      localObject1 = Novel.getActors().get(localScriptLine.getAction());
    } while (!(localObject1 instanceof ifStatment));
    ((ifStatment)localObject1).doAction(this.novel);
    return;
    this.onLine = (1 + this.onLine);
    return;
    label471:
    Novel localNovel = this.novel;
    localNovel.onLine = (1 + localNovel.onLine);
    this.onLine = 0;
  }
  
  public void setList(ArrayList<ScriptLine> paramArrayList)
  {
    this.list = paramArrayList;
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\ifStatment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */