/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnepcport;

import java.util.*;
import java.io.*;

/**
 *
 * @author Yang
 */

public class GameController {
    private String scriptFileName;
    private String scriptLine = null;
    private String scriptHead = null;
    private String scriptContent = null;
    
    private int beginIdx;
    private int endIdx;
    private int skipBuf;
    private int count = 0;
	
    Text gameText = new Text();
	Image image = new Image();
    
    public void openGame(String fileName) {
        scriptFileName = fileName;
    }
    
    public int runGame() {
        if (scriptFileName == null) {
            return 0;
        } else {
            try {
                FileReader fileReader = new FileReader(scriptFileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while((scriptLine = bufferedReader.readLine()) != null) {
                    beginIdx = scriptLine.indexOf("{");
                    endIdx = scriptLine.indexOf("}");
                    scriptHead = scriptLine.substring(0, beginIdx);
                    scriptContent = scriptLine.substring(beginIdx + 1, endIdx);
                
                    if (scriptHead.compareTo("music") == 0) {
                        System.out.println("MUSIC");
                        System.out.println(scriptContent);
                    }
                    if (scriptHead.compareTo("text") == 0) {
                        System.out.println("TEXT");
                        gameText.displayText(0, scriptContent);
                    }
                    if (scriptHead.compareTo("image") == 0) {
                        System.out.println("IMAGE");
						++count;
						if(count == 1){
							image.displayImage(scriptContent);
						}else{
							image.changeImage(scriptContent);
						}
                        System.out.println(scriptContent);
                    }
                    if (scriptHead.compareTo("background") == 0) {
                        System.out.println("BACKGROUND");
                        System.out.println(scriptContent);
                    }
                    if (scriptHead.compareTo("fx") == 0) {
                        System.out.println("EFFECT");
                        System.out.println(scriptContent);
                    }
                    if (scriptHead.compareTo("option") == 0) {
                        System.out.println("OPTIONS");
                        skipBuf = gameText.displayText(1, scriptContent);
                        for (int i = 0; i < skipBuf; i++) {
                            bufferedReader.readLine();
                        }
                    }
                    if (scriptHead.compareTo("skip") == 0) {
                        System.out.println("SKIP");
                        for (int i = 0; i < Integer.parseInt(scriptContent); i++) {
                            bufferedReader.readLine();
                        }
                    }
                    if (scriptHead.compareTo("pause") == 0) {
                        System.out.println("PAUSE");
                        System.out.println(scriptContent);
                    }
                    if (scriptHead.compareTo("end") == 0) {
                        System.out.println("END");
                        System.out.println(scriptContent);
                    }
                }
                bufferedReader.close(); // close file reader
            }
            catch(FileNotFoundException ex) {
                System.out.println("UNABLE TO OPEN GAME '" + scriptFileName + "'");                
            }
            catch(IOException ex) {
                System.out.println("ERROR READING FILE '" + scriptFileName + "'");                  
            }  
        }
        return 1;
    }
}
