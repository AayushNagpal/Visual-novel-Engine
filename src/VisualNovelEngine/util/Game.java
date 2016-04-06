package enders.visual.novel;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;

public class Game
  extends SurfaceView
  implements SurfaceHolder.Callback
{
  private ArrayList<Character> actors = new ArrayList();
  protected AlertDialog alert;
  private AlertDialog alertDialog;
  private Bitmap background;
  private Bitmap background2;
  private float bgAlpha = 1.0F;
  String bgString;
  private CanvasThread canvasthread;
  private Context context;
  private String dialogCharacter;
  private int dialogColor;
  private String dialogText;
  boolean dialogVisible;
  protected String filename;
  private float gameHeight = 600.0F;
  private float gameWidth = 800.0F;
  private ArrayList<Menu> menus = new ArrayList();
  private float midX;
  private float normal;
  private Novel novel;
  private float scale;
  private int textsize;
  private int windowHeight;
  private float windowWidth = 0.0F;
  
  public Game(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    getHolder().addCallback(this);
    this.canvasthread = new CanvasThread(getHolder(), this);
    setFocusable(true);
    setFocusableInTouchMode(true);
    this.context = paramContext;
    this.novel = new Novel(this, paramContext);
  }
  
  public void changeBG(Bitmap paramBitmap, String paramString)
  {
    this.background = paramBitmap;
    this.background2 = null;
    this.bgString = paramString;
  }
  
  public ArrayList<Character> getActors()
  {
    return this.actors;
  }
  
  public Bitmap getBackground2()
  {
    return this.background2;
  }
  
  public String getBackgroundString()
  {
    return this.bgString;
  }
  
  public float getBgAlpha()
  {
    return this.bgAlpha;
  }
  
  public ArrayList<Menu> getMenus()
  {
    return this.menus;
  }
  
  public void load()
  {
    this.novel.load();
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    Paint localPaint;
    int j;
    if (paramCanvas != null)
    {
      if (this.novel != null) {
        this.novel.playNovel();
      }
      localPaint = new Paint();
      localPaint.setFilterBitmap(true);
      paramCanvas.scale(this.scale, this.scale);
      paramCanvas.drawColor(-16777216);
      if (this.background2 != null)
      {
        localPaint.setAlpha((int)(255.0F * (1.0F - this.bgAlpha)));
        paramCanvas.drawBitmap(this.background2, this.midX - this.background2.getWidth() / 2, 0.0F, localPaint);
      }
      if (this.background != null)
      {
        localPaint.setAlpha((int)(255.0F * this.bgAlpha));
        paramCanvas.drawBitmap(this.background, this.midX - this.background.getWidth() / 2, 0.0F, localPaint);
      }
      localPaint.setAlpha(255);
      if (this.actors.size() > 0)
      {
        j = 0;
        if (j < this.actors.size()) {
          break label468;
        }
      }
      paramCanvas.scale(this.normal, this.normal);
      if (this.menus.size() <= 0) {}
    }
    TextPaint localTextPaint;
    for (int i = 0;; i++)
    {
      if (i >= this.menus.size())
      {
        if (this.dialogVisible)
        {
          localPaint.setColor(Color.parseColor("#ff3d372c"));
          localPaint.setAlpha(128);
          paramCanvas.drawRect(new Rect(0, 3 * (getHeight() / 5), getWidth(), getHeight()), localPaint);
          localPaint.setColor(this.dialogColor);
          localTextPaint = new TextPaint();
          localTextPaint.setColor(this.dialogColor);
          localTextPaint.setTextSize(this.textsize);
          localTextPaint.setAntiAlias(true);
          if ((this.dialogCharacter == null) && (this.dialogCharacter == null)) {
            break label606;
          }
          localTextPaint.setColor(this.dialogColor);
          StaticLayout localStaticLayout1 = new StaticLayout(this.dialogCharacter + ":", localTextPaint, (int)(this.gameWidth * this.scale) - 6, Layout.Alignment.ALIGN_NORMAL, 1.25F, 0.0F, false);
          paramCanvas.translate(3.0F + (this.windowWidth - this.gameWidth * this.scale) / 2.0F, 3 * (getHeight() / 5));
          localStaticLayout1.draw(paramCanvas);
          localTextPaint.setColor(-1);
          StaticLayout localStaticLayout2 = new StaticLayout(this.dialogText, localTextPaint, (int)(this.gameWidth * this.scale) - 6, Layout.Alignment.ALIGN_NORMAL, 1.25F, 0.0F, false);
          paramCanvas.translate(0.0F, 5 + this.textsize);
          localStaticLayout2.draw(paramCanvas);
        }
        return;
        label468:
        if ((((Character)this.actors.get(j)).getImage() != null) && (((Character)this.actors.get(j)).visible)) {
          paramCanvas.drawBitmap(((Character)this.actors.get(j)).getImage(), this.midX - ((Character)this.actors.get(j)).getImage().getWidth() / 2, this.gameHeight - ((Character)this.actors.get(j)).getImage().getHeight(), localPaint);
        }
        j++;
        break;
      }
      ((Menu)this.menus.get(i)).draw(paramCanvas, this.textsize);
    }
    label606:
    localTextPaint.setColor(-1);
    StaticLayout localStaticLayout3 = new StaticLayout(this.dialogText, localTextPaint, (int)(this.gameWidth * this.scale) - 6, Layout.Alignment.ALIGN_NORMAL, 1.25F, 0.0F, false);
    paramCanvas.translate(3.0F + (this.windowWidth - this.gameWidth * this.scale) / 2.0F, 3 * (getHeight() / 5));
    localStaticLayout3.draw(paramCanvas);
  }
  
  public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.windowWidth = paramInt1;
    this.windowHeight = paramInt2;
    this.scale = (paramInt2 / this.gameHeight);
    this.normal = (this.gameHeight / paramInt2);
    this.midX = (paramInt1 / 2 * this.normal);
    this.textsize = 12;
    if (this.windowHeight >= 640)
    {
      this.textsize = 38;
      return;
    }
    if (this.windowHeight >= 480)
    {
      this.textsize = 28;
      return;
    }
    if (this.windowHeight >= 320)
    {
      this.textsize = 18;
      return;
    }
    this.textsize = 14;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.menus.size() > 0) {
      if (((Menu)this.menus.get(0)).click(paramMotionEvent.getX(), paramMotionEvent.getY(), paramMotionEvent.getAction())) {
        this.menus.remove(0);
      }
    }
    while (paramMotionEvent.getAction() != 1) {
      return true;
    }
    this.novel.click(paramMotionEvent.getX(), paramMotionEvent.getY());
    return true;
  }
  
  public void restart()
  {
    this.background = null;
    this.background2 = null;
    this.bgString = null;
    this.dialogVisible = false;
    this.bgAlpha = 1.0F;
    this.actors.clear();
    this.menus.clear();
    this.novel.setFrame("menu");
    this.novel.onLine = 0;
    this.novel.waitForClick = false;
  }
  
  public void save()
  {
    File localFile = new File("/sdcard/VN/" + this.novel.getName());
    localFile.mkdirs();
    final String[] arrayOfString = new String[1 + localFile.list().length];
    arrayOfString[0] = "New Game";
    for (int i = 0;; i++)
    {
      if (i >= localFile.list().length)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(getContext());
        localBuilder.setSingleChoiceItems(arrayOfString, -1, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            Game.this.filename = arrayOfString[paramAnonymousInt];
            Toast.makeText(Game.this.getContext(), "Selected " + arrayOfString[paramAnonymousInt], 0).show();
            Game.this.alertDialog.getButton(-1).setEnabled(true);
          }
        }).setTitle("Select Game").setPositiveButton("Okay", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            if (Game.this.filename != null)
            {
              if (Game.this.filename.equals("New Game"))
              {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(Game.this.getContext());
                localBuilder.setTitle("Create new save file.");
                final EditText localEditText = new EditText(Game.this.getContext());
                localEditText.addTextChangedListener(new TextWatcher()
                {
                  public void afterTextChanged(Editable paramAnonymous2Editable) {}
                  
                  public void beforeTextChanged(CharSequence paramAnonymous2CharSequence, int paramAnonymous2Int1, int paramAnonymous2Int2, int paramAnonymous2Int3) {}
                  
                  public void onTextChanged(CharSequence paramAnonymous2CharSequence, int paramAnonymous2Int1, int paramAnonymous2Int2, int paramAnonymous2Int3)
                  {
                    if (!localEditText.getText().toString().equals("")) {
                      Game.this.alert.getButton(-1).setEnabled(true);
                    }
                    for (;;)
                    {
                      if (localEditText.getText().toString().equals("")) {
                        Game.this.alert.getButton(-1).setEnabled(false);
                      }
                      return;
                      Game.this.alert.getButton(-1).setEnabled(false);
                    }
                  }
                });
                localBuilder.setView(localEditText);
                localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                  {
                    String str = localEditText.getText().toString();
                    Game.this.novel.save(str);
                  }
                });
                localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
                });
                Game.this.alert = localBuilder.create();
                Game.this.alert.show();
                Game.this.alert.getButton(-1).setEnabled(false);
              }
            }
            else {
              return;
            }
            Game.this.novel.save(Game.this.filename);
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
        return;
      }
      arrayOfString[(i + 1)] = localFile.list()[i];
    }
  }
  
  public void setActors(ArrayList<Character> paramArrayList)
  {
    this.actors = paramArrayList;
  }
  
  public void setBackground(Bitmap paramBitmap)
  {
    this.background = paramBitmap;
  }
  
  public void setBackground2(Bitmap paramBitmap)
  {
    this.background2 = paramBitmap;
  }
  
  public void setBgAlpha(float paramFloat)
  {
    this.bgAlpha = paramFloat;
  }
  
  public void setMenus(ArrayList<Menu> paramArrayList)
  {
    this.menus = paramArrayList;
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    if (!this.canvasthread.isAlive())
    {
      this.canvasthread = new CanvasThread(getHolder(), this);
      this.canvasthread.start();
    }
    this.canvasthread.setRunning(true);
    this.novel.paused = false;
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    int i = 1;
    this.canvasthread.setRunning(false);
    for (;;)
    {
      if (i == 0) {
        return;
      }
      try
      {
        PlaySound.stop();
        this.canvasthread.join();
        i = 0;
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
  
  public void text(String paramString1, int paramInt, String paramString2)
  {
    this.dialogText = paramString1;
    this.dialogColor = paramInt;
    this.dialogCharacter = paramString2;
    this.dialogVisible = true;
  }
}


/* Location:              C:\Users\Harry\Desktop\CS-Details\Introduction to Software Engineering\new-project\dex2jar-0.0.9.15\dex2jar-0.0.9.15\classes-dex2jar.jar!\enders\visual\novel\Game.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */