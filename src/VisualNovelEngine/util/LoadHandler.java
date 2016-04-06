package enders.visual.novel;

import java.util.ArrayList;
import java.util.Hashtable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadHandler
  extends DefaultHandler
{
  private boolean inCharacters;
  private boolean inVars;
  private Novel novel;
  
  public LoadHandler(Novel paramNovel)
  {
    this.novel = paramNovel;
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if (paramString2.equals("characters")) {
      this.inCharacters = false;
    }
    if (paramString2.equals("userVars")) {
      this.inVars = false;
    }
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    if (paramString2.equals("onFrame")) {
      this.novel.setFrame(paramAttributes.getValue("frame"));
    }
    if (paramString2.equals("onLine")) {
      this.novel.onLine = Integer.valueOf(paramAttributes.getValue("line")).intValue();
    }
    if (paramString2.equals("BGimage"))
    {
      this.novel.paused = true;
      this.novel.getGame().getMenus().clear();
      this.novel.waitForClick = false;
      this.novel.loadBG(paramAttributes.getValue("image"));
      this.novel.paused = false;
    }
    if (paramString2.equals("characters")) {
      this.inCharacters = true;
    }
    if (paramString2.equals("userVars")) {
      this.inVars = true;
    }
    if ((this.inCharacters) && (paramString2.equals("actor")))
    {
      this.novel.getGame().getActors().add((Character)Novel.getActors().get(paramAttributes.getValue("name")));
      ((Character)Novel.getActors().get(paramAttributes.getValue("name"))).changeImage(this.novel, paramAttributes.getValue("image"));
    }
    if ((this.inVars) && (paramString2.equals("var"))) {
      Novel.getUserVar().put(paramAttributes.getValue("name"), paramAttributes.getValue("value"));
    }
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\LoadHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */