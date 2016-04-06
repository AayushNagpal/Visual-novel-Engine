package enders.visual.novel;

import android.util.Log;
import java.util.ArrayList;
import java.util.Hashtable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DataHandler
  extends DefaultHandler
{
  private ifStatment ifstatment;
  private boolean inFrame;
  private boolean inIfStatment;
  private boolean inMenu;
  private boolean inScript;
  private ArrayList<ScriptLine> list = null;
  private Menu menu;
  private Novel novel;
  private String temp;
  
  public DataHandler(Novel paramNovel)
  {
    this.novel = paramNovel;
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if (paramString2.equals("if"))
    {
      Novel.getActors().put(this.temp, this.ifstatment);
      this.inIfStatment = false;
    }
    if (paramString2.equals("menu"))
    {
      Log.v("menu end", this.menu);
      Novel.getActors().put(this.temp, this.menu);
      this.inMenu = false;
    }
    if (paramString2.equals("script")) {
      this.inScript = false;
    }
    if (paramString2.equals("frame")) {
      this.inFrame = false;
    }
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    if (paramString2.equals("game")) {
      this.novel.setName(paramAttributes.getValue("name"));
    }
    if (paramString2.equals("image"))
    {
      Image localImage = new Image();
      localImage.setImage(paramAttributes.getValue("src"));
      localImage.setName(paramAttributes.getValue("name"));
      Novel.getImages().put(paramAttributes.getValue("name"), localImage);
    }
    if (paramString2.equals("character")) {
      Novel.getActors().put(paramAttributes.getValue("name"), new Character(paramAttributes.getValue("name"), paramAttributes.getValue("color")));
    }
    if (paramString2.equals("script")) {
      this.inScript = true;
    }
    if (this.inScript)
    {
      if (paramString2.equals("frame"))
      {
        Novel.getLabels().add(paramAttributes.getValue("label"));
        this.list = new ArrayList();
        Novel.getLines().add(this.list);
        this.inFrame = true;
      }
      if (!this.inIfStatment) {
        break label311;
      }
      ScriptLine localScriptLine1 = new ScriptLine();
      localScriptLine1.setType(paramString2);
      localScriptLine1.setAction(paramAttributes.getValue("action"));
      localScriptLine1.setName(paramAttributes.getValue("name"));
      localScriptLine1.setSrc(paramAttributes.getValue("src"));
      localScriptLine1.setValue(paramAttributes.getValue("value"));
      this.ifstatment.getList().add(localScriptLine1);
    }
    for (;;)
    {
      paramString2.equals("sub");
      return;
      label311:
      if (this.inMenu)
      {
        if (paramString2.equals("choice"))
        {
          this.menu.getChoices().add(paramAttributes.getValue("value"));
          this.menu.getJumps().add(paramAttributes.getValue("name"));
          Log.v("menu choice", paramAttributes.getValue("value") + " " + paramAttributes.getValue("name"));
        }
      }
      else if (this.inFrame) {
        if (paramString2.equals("if"))
        {
          this.inIfStatment = true;
          this.ifstatment = new ifStatment();
          ScriptLine localScriptLine4 = new ScriptLine();
          localScriptLine4.setType(paramString2);
          localScriptLine4.setAction(paramAttributes.getValue("name"));
          localScriptLine4.setSrc(paramAttributes.getValue("src"));
          localScriptLine4.setValue(paramAttributes.getValue("value"));
          this.temp = paramAttributes.getValue("name");
          this.list.add(localScriptLine4);
        }
        else if (paramString2.equals("menu"))
        {
          this.inMenu = true;
          this.menu = new Menu();
          ScriptLine localScriptLine3 = new ScriptLine();
          localScriptLine3.setType(paramString2);
          localScriptLine3.setAction(paramAttributes.getValue("name"));
          this.temp = paramAttributes.getValue("name");
          this.list.add(localScriptLine3);
          Log.v("menu start", paramAttributes.getValue("name"));
        }
        else
        {
          ScriptLine localScriptLine2 = new ScriptLine();
          localScriptLine2.setType(paramString2);
          localScriptLine2.setAction(paramAttributes.getValue("action"));
          localScriptLine2.setName(paramAttributes.getValue("name"));
          localScriptLine2.setSrc(paramAttributes.getValue("src"));
          localScriptLine2.setValue(paramAttributes.getValue("value"));
          this.list.add(localScriptLine2);
        }
      }
    }
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\DataHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */