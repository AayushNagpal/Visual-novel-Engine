package enders.visual.novel;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.util.Xml;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlSerializer;

public class Novel
{
  private static ArrayList<ArrayList<ScriptLine>> Lines;
  private static Hashtable actors = new Hashtable();
  private static Hashtable images;
  private static ArrayList<String> labels = new ArrayList();
  private static Hashtable userVar = new Hashtable();
  private AlertDialog alertDialog = null;
  AssetManager assetManager;
  private Bitmap backgroundImage;
  private Context context;
  private int currentPlaying = 0;
  private boolean fadingIn;
  protected String filename = null;
  private String frame = null;
  private Game game;
  private int ifLevel = 0;
  boolean ignoreClicks = false;
  private String name = "test";
  int onFrame = 0;
  int onLine = 0;
  boolean paused = true;
  private InputStream stream;
  boolean waitForClick;
  private XMLReader xr;
  
  static
  {
    Lines = new ArrayList();
    images = new Hashtable();
  }
  
  public Novel(Game paramGame, Context paramContext)
  {
    this.assetManager = paramContext.getAssets();
    this.context = paramContext;
    this.game = paramGame;
    prepareNovel("test.xml");
    setFrame("menu");
  }
  
  private void enableButton(boolean paramBoolean)
  {
    this.alertDialog.getButton(-1).setEnabled(paramBoolean);
  }
  
  public static Hashtable getActors()
  {
    return actors;
  }
  
  public static Hashtable getImages()
  {
    return images;
  }
  
  public static ArrayList<String> getLabels()
  {
    return labels;
  }
  
  public static ArrayList<ArrayList<ScriptLine>> getLines()
  {
    return Lines;
  }
  
  public static Hashtable getUserVar()
  {
    return userVar;
  }
  
  private void novel_dissolveIn(String paramString)
  {
    if (this.game.getBgAlpha() > 0.0F)
    {
      this.game.setBgAlpha(this.game.getBgAlpha() - 0.1F);
      return;
    }
    this.game.changeBG(this.backgroundImage, paramString);
    this.game.setBgAlpha(1.0F);
    this.onLine = (1 + this.onLine);
  }
  
  private void novel_fadeBgIn(float paramFloat)
  {
    if (this.game.getBgAlpha() < 1.0F)
    {
      this.game.setBgAlpha(0.1F + this.game.getBgAlpha());
      return;
    }
    this.fadingIn = false;
    this.onLine = (1 + this.onLine);
  }
  
  private void novel_fadeBgOut(String paramString)
  {
    if (this.game.getBgAlpha() > 0.0F)
    {
      this.game.setBgAlpha(this.game.getBgAlpha() - 0.1F);
      return;
    }
    this.game.changeBG(this.backgroundImage, paramString);
    this.fadingIn = true;
    novel_fadeBgIn(1.0F);
  }
  
  public static void setLines(ArrayList<ArrayList<ScriptLine>> paramArrayList)
  {
    Lines = paramArrayList;
  }
  
  public static void setUserVar(Hashtable paramHashtable)
  {
    userVar = paramHashtable;
  }
  
  protected void alert(String paramString1, String paramString2)
  {
    new AlertDialog.Builder(this.context).setMessage(paramString2).setTitle(paramString1).setCancelable(true).setNeutralButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).show();
  }
  
  void changeBG(String paramString1, String paramString2)
  {
    String str1;
    Iterator localIterator2;
    if (!paramString1.equals("")) {
      if (images.containsKey(paramString1))
      {
        str1 = this.game.getBackgroundString();
        localIterator2 = actors.keySet().iterator();
      }
    }
    for (;;)
    {
      String str2;
      if (!localIterator2.hasNext())
      {
        this.game.dialogVisible = false;
        if ((paramString2 != null) && (!paramString2.equals(""))) {
          break label177;
        }
        str2 = ((Image)images.get(paramString1)).getImage();
        if (str2.equals(str1)) {}
      }
      try
      {
        this.stream = this.assetManager.open(str2);
        this.backgroundImage = BitmapFactory.decodeStream(this.stream);
        this.game.changeBG(this.backgroundImage, str2);
        for (;;)
        {
          return;
          Object localObject3 = localIterator2.next();
          Object localObject4 = actors.get((String)localObject3);
          if (!(localObject4 instanceof Character)) {
            break;
          }
          ((Character)localObject4).setImage(null);
          break;
          label177:
          String str4;
          if (paramString2.equals("fade"))
          {
            str4 = ((Image)images.get(paramString1)).getImage();
            if (str4.equals(str1)) {}
          }
          try
          {
            this.stream = this.assetManager.open(str4);
            this.backgroundImage = BitmapFactory.decodeStream(this.stream);
            if (!this.fadingIn)
            {
              novel_fadeBgOut(str4);
              return;
            }
            novel_fadeBgIn(this.game.getBgAlpha());
            return;
            if (!paramString2.equals("dissolve")) {
              continue;
            }
            String str3 = ((Image)images.get(paramString1)).getImage();
            if (!str3.equals(str1)) {}
            try
            {
              this.stream = this.assetManager.open(str3);
              this.backgroundImage = BitmapFactory.decodeStream(this.stream);
              this.game.setBackground2(this.backgroundImage);
              this.game.bgString = str3;
              novel_dissolveIn(str3);
              return;
              Iterator localIterator1 = actors.keySet().iterator();
              for (;;)
              {
                if (!localIterator1.hasNext())
                {
                  this.game.changeBG(null, null);
                  this.onLine = (1 + this.onLine);
                  return;
                }
                Object localObject1 = localIterator1.next();
                Object localObject2 = actors.get((String)localObject1);
                if ((localObject2 instanceof Character)) {
                  ((Character)localObject2).setImage(null);
                }
              }
            }
            catch (IOException localIOException2)
            {
              for (;;) {}
            }
          }
          catch (IOException localIOException3)
          {
            for (;;) {}
          }
        }
      }
      catch (IOException localIOException1)
      {
        for (;;) {}
      }
    }
  }
  
  public void click(float paramFloat1, float paramFloat2)
  {
    if (this.waitForClick)
    {
      this.onLine = (1 + this.onLine);
      this.waitForClick = false;
    }
  }
  
  public Bitmap getBackgroundImage()
  {
    return this.backgroundImage;
  }
  
  public String getFrame()
  {
    return this.frame;
  }
  
  public Game getGame()
  {
    return this.game;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void load()
  {
    this.stream = null;
    final File localFile = new File("/sdcard/VN/" + getName());
    localFile.mkdirs();
    this.filename = null;
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.game.getContext());
    localBuilder.setSingleChoiceItems(localFile.list(), -1, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Novel.this.filename = localFile.list()[paramAnonymousInt];
        Toast.makeText(Novel.this.game.getContext(), "Selected " + localFile.list()[paramAnonymousInt], 0).show();
        Novel.this.enableButton(true);
      }
    }).setTitle("Select Game").setPositiveButton("Okay", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (Novel.this.filename != null) {}
        try
        {
          File localFile = new File(localFile, "/" + Novel.this.filename);
          Novel.this.stream = new FileInputStream(localFile);
          try
          {
            SAXParser localSAXParser = SAXParserFactory.newInstance().newSAXParser();
            Novel.this.xr = localSAXParser.getXMLReader();
            LoadHandler localLoadHandler = new LoadHandler(Novel.this);
            Novel.this.xr.setContentHandler(localLoadHandler);
            Novel.this.xr.parse(new InputSource(Novel.this.stream));
            return;
          }
          catch (ParserConfigurationException localParserConfigurationException)
          {
            Log.e("SAX XML", "sax parse error", localParserConfigurationException);
            return;
          }
          catch (SAXException localSAXException)
          {
            Log.e("SAX XML", "sax error", localSAXException);
            return;
          }
          catch (IOException localIOException2)
          {
            Log.e("SAX XML", "sax parse io error", localIOException2);
            return;
          }
        }
        catch (IOException localIOException1)
        {
          for (;;) {}
        }
      }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    this.alertDialog = localBuilder.create();
    this.alertDialog.show();
    this.alertDialog.getButton(-1).setEnabled(false);
  }
  
  public void loadBG(String paramString)
  {
    if (!paramString.equals(this.game.getBackgroundString())) {}
    try
    {
      this.stream = this.assetManager.open(paramString);
      this.backgroundImage = BitmapFactory.decodeStream(this.stream);
      this.game.changeBG(this.backgroundImage, paramString);
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public void playNovel()
  {
    ScriptLine localScriptLine;
    if ((!this.ignoreClicks) && (this.onFrame < Lines.size()) && (!this.paused) && (!this.waitForClick))
    {
      if (this.onLine >= ((ArrayList)Lines.get(labels.indexOf(this.frame))).size()) {
        break label516;
      }
      localScriptLine = (ScriptLine)((ArrayList)Lines.get(labels.indexOf(this.frame))).get(this.onLine);
      if (!localScriptLine.getType().equals("actor")) {
        break label246;
      }
      if (localScriptLine.getName() == null) {
        break label201;
      }
      if (localScriptLine.getName().equals("")) {
        break label156;
      }
      Object localObject3 = actors.get(localScriptLine.getName());
      if ((localObject3 instanceof Character)) {
        ((Character)localObject3).doAction(this, localScriptLine);
      }
    }
    label155:
    label156:
    label201:
    label246:
    Object localObject1;
    do
    {
      Object localObject2;
      do
      {
        do
        {
          break label155;
          do
          {
            return;
          } while (!localScriptLine.getAction().contains("say"));
          this.game.dialogVisible = true;
          this.game.text(localScriptLine.getValue(), Color.parseColor("#FF000000"), null);
          this.waitForClick = true;
          return;
        } while (!localScriptLine.getAction().contains("say"));
        this.game.dialogVisible = true;
        this.game.text(localScriptLine.getValue(), Color.parseColor("#FF000000"), null);
        this.waitForClick = true;
        return;
        if (localScriptLine.getType().equals("function"))
        {
          String str = localScriptLine.getAction();
          if (str.equals("changeBG"))
          {
            changeBG(localScriptLine.getSrc(), localScriptLine.getValue());
            return;
          }
          if (str.equals("playMusic"))
          {
            PlaySound.play(this.context, localScriptLine.getSrc());
            this.onLine = (1 + this.onLine);
            return;
          }
          if (str.equals("changeFlag"))
          {
            getUserVar().put(localScriptLine.getName(), localScriptLine.getValue());
            this.onLine = (1 + this.onLine);
            return;
          }
          if (str.equals("jump"))
          {
            setFrame(localScriptLine.getValue());
            this.onLine = 0;
            return;
          }
          this.onLine = (1 + this.onLine);
          return;
        }
        if (!localScriptLine.getType().equals("menu")) {
          break;
        }
        localObject2 = actors.get(localScriptLine.getAction());
      } while (!(localObject2 instanceof Menu));
      ((Menu)localObject2).doAction(this);
      return;
      if (!localScriptLine.getType().equals("if")) {
        break;
      }
      localObject1 = actors.get(localScriptLine.getAction());
    } while (!(localObject1 instanceof ifStatment));
    if (getUserVar().get(localScriptLine.getSrc()).equals(localScriptLine.getValue()))
    {
      ((ifStatment)localObject1).doAction(this);
      return;
    }
    this.onLine = (1 + this.onLine);
    return;
    this.onLine = (1 + this.onLine);
    return;
    label516:
    this.paused = true;
    this.onFrame = (1 + this.onFrame);
    this.onLine = 0;
  }
  
  public void prepareNovel(String paramString)
  {
    this.stream = null;
    try
    {
      this.stream = this.assetManager.open(paramString);
      try
      {
        this.xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        DataHandler localDataHandler = new DataHandler(this);
        this.xr.setContentHandler(localDataHandler);
        this.xr.parse(new InputSource(this.stream));
        return;
      }
      catch (ParserConfigurationException localParserConfigurationException)
      {
        Log.e("SAX XML", "sax parse error", localParserConfigurationException);
        return;
      }
      catch (SAXException localSAXException)
      {
        Log.e("SAX XML", "sax error", localSAXException);
        return;
      }
      catch (IOException localIOException2)
      {
        Log.e("SAX XML", "sax parse io error", localIOException2);
        return;
      }
    }
    catch (IOException localIOException1)
    {
      for (;;) {}
    }
  }
  
  public String save(String paramString)
  {
    XmlSerializer localXmlSerializer = Xml.newSerializer();
    File localFile1 = new File("/sdcard/VN/" + getName());
    localFile1.mkdirs();
    File localFile2 = new File(localFile1, "/" + paramString);
    try
    {
      localFile2.createNewFile();
    }
    catch (IOException localFileNotFoundException)
    {
      try
      {
        FileOutputStream localFileOutputStream1 = new FileOutputStream(localFile2);
        localFileOutputStream2 = localFileOutputStream1;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        try
        {
          FileOutputStream localFileOutputStream2;
          label85:
          Log.v("File Write", "Beginning");
          localXmlSerializer.setOutput(localFileOutputStream2, "UTF-8");
          localXmlSerializer.startDocument(null, Boolean.valueOf(true));
          localXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
          Log.v("File Write", "start tags");
          localXmlSerializer.startTag(null, "saveGame");
          Log.v("File Write", "onFrame tags");
          localXmlSerializer.startTag(null, "onFrame");
          localXmlSerializer.attribute(null, "frame", this.frame);
          localXmlSerializer.endTag(null, "onFrame");
          Log.v("File Write", "onLine tags");
          localXmlSerializer.startTag(null, "onLine");
          localXmlSerializer.attribute(null, "line", String.valueOf(this.onLine));
          localXmlSerializer.endTag(null, "onLine");
          Log.v("File Write", "bgimage tags");
          localXmlSerializer.startTag(null, "BGimage");
          localXmlSerializer.attribute(null, "image", this.game.getBackgroundString());
          localXmlSerializer.endTag(null, "BGimage");
          Log.v("File Write", "actors tags");
          localXmlSerializer.startTag(null, "characters");
          int i = 0;
          label319:
          Iterator localIterator;
          if (i >= this.game.getActors().size())
          {
            localXmlSerializer.endTag(null, "characters");
            Log.v("File Write", "userVars tags");
            localXmlSerializer.startTag(null, "userVars");
            localIterator = getUserVar().keySet().iterator();
          }
          for (;;)
          {
            if (!localIterator.hasNext())
            {
              localXmlSerializer.endTag(null, "userVars");
              Log.v("File Write", "end tags");
              localXmlSerializer.endTag(null, "saveGame");
              localXmlSerializer.endDocument();
              Log.v("File Write", "flushing");
              localXmlSerializer.flush();
              Log.v("File Write", "flushed");
              localFileOutputStream2.close();
              return null;
              localIOException = localIOException;
              Log.e("IOException", "exception in createNewFile() method");
              break;
              localFileNotFoundException = localFileNotFoundException;
              Log.e("FileNotFoundException", "can't create FileOutputStream");
              localFileOutputStream2 = null;
              break label85;
              localXmlSerializer.startTag(null, "actor");
              localXmlSerializer.attribute(null, "name", ((Character)this.game.getActors().get(i)).getName());
              localXmlSerializer.attribute(null, "image", ((Character)this.game.getActors().get(i)).getBimapString());
              localXmlSerializer.endTag(null, "actor");
              i++;
              break label319;
            }
            Object localObject = localIterator.next();
            localXmlSerializer.startTag(null, "var");
            localXmlSerializer.attribute(null, "name", (String)localObject);
            localXmlSerializer.attribute(null, "value", (String)getUserVar().get((String)localObject));
            localXmlSerializer.endTag(null, "var");
          }
          return null;
        }
        catch (Exception localException)
        {
          Log.e("Exception", "error occurred while creating xml file");
        }
      }
    }
  }
  
  public void setFrame(String paramString)
  {
    this.frame = paramString;
  }
  
  public void setGame(Game paramGame)
  {
    this.game = paramGame;
  }
  
  public void setLabels(ArrayList<String> paramArrayList)
  {
    labels = paramArrayList;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\Novel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */