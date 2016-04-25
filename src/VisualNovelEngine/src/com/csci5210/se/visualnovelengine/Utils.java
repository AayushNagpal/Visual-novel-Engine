package com.csci5210.se.visualnovelengine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

public class Utils {
	public static void error(String err) {
		System.out.println("Exception: " + err);
	}

	public static void error(Context context, String err) {
		Toast.makeText(context, "Exception: " + err, Toast.LENGTH_LONG).show();
		System.out.println("Exception: " + err);
	}

	public static void error(Exception e) {
		e.printStackTrace();
	}

	public static void error(Context context, Exception e) {
		Toast.makeText(context, "Exception: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
		e.printStackTrace();
	}

	public static void debug(Context context, String str) {
		Toast.makeText(context, ">>> " + str, Toast.LENGTH_LONG).show();
		System.out.println(">>> " + str);
	}

	
	@SuppressWarnings("rawtypes")
	public static void print(Hashtable map) {
	    Iterator it = map.entrySet().iterator();
	    while(it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println("<"+pairs.getKey() + "> = <" + pairs.getValue() + ">");
	    }
	}

	
	public static String join(String[] ary, String del) {
		int length = ary.length;
        if(length == 0)
        	return "";

        StringBuilder sb = new StringBuilder();
        int i;
        for(i = 0; i < length-1; ++i)
            sb.append(ary[i] + del);

        return sb.toString() + ary[i];
	}

	/
	public static String parseString(String text, String pattern, Hashtable<String, String> keyvalues) {
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		StringBuffer buffer = new StringBuffer();

		while(matcher.find())
			matcher.appendReplacement(buffer, keyvalues.get(matcher.group(1)));
		matcher.appendTail(buffer);

		return buffer.toString();
	}

	
	public static String serialize(Object o) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(o);
	        oos.close();
	        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
		}
		catch(Exception e) {}

		return "";
	  }

	
	public static Object deserialize(String s) {
		try {
	        byte[] data = Base64.decode(s, Base64.DEFAULT);
	        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
	        Object o = ois.readObject();
	        ois.close();
	        return o;
		}
		catch(Exception e) {}

		return null;
	}

	
	public static Stack<DialogueType> prepend(Stack<DialogueType> source, Stack<DialogueType> prepend) {
		Stack<DialogueType> stack = source;

		for(DialogueType d : prepend)
			stack.push(d);

		return stack;
	}

	public static void saveAll(Context context) {
		Memory.putKeyValues(context, Game.keyvalues);
		MediaPlayerHandler.stop(Game.mediaPlayers);
	}
}
