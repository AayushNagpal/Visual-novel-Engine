package gamecontroller;
import java.util.*;
import java.io.*;

public class GameController {
    
    public static void main(String[] args) {
        String scriptFileName = "E:/script.txt"; // game script file
        String scriptLine = null;
        String scriptHead = null;
        String scriptContent = null;
        
        int beginIdx;
        int endIdx;
        
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
                    System.out.println(scriptContent);
                }
                if (scriptHead.compareTo("image") == 0) {
                    System.out.println("IMAGE");
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
                    System.out.println(scriptContent);
                }
                if (scriptHead.compareTo("skip") == 0) {
                    System.out.println("SKIP");
                    System.out.println(scriptContent);
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
            System.out.println(
                "Unable to open file '" + scriptFileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + scriptFileName + "'");                  
        }     
    }
}
