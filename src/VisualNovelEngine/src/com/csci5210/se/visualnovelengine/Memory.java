package com.csci5210.se.visualnovelengine;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import com.csci5210.se.visualnovelengine.R;

import org.json.JSONObject;

import android.content.Context;

public class Memory {

	public static final String KEYVALUES = "visual_novel_engine.bin";

	public static boolean putKeyValues(Context context, Hashtable<String, String> keyvalues) {
		try {
			JSONObject json = new JSONObject();

			for(Entry<String, String> entry : keyvalues.entrySet())
			    json.put(entry.getKey(), entry.getValue());

			FileOutputStream fos = context.openFileOutput(KEYVALUES, Context.MODE_PRIVATE);
			fos.write(json.toString().getBytes());
			fos.flush();
			fos.close();

	        return true;
		}
		catch(Exception e) {
			Utils.error(e);
			Utils.error(context, context.getString(R.string.error_saving_game));
			return false;
		}
	}

	public static Hashtable<String, String> getKeyValues(Context context) {
		Hashtable<String, String> keyvalues = new Hashtable<String, String>();

		try {
			StringBuffer buffer = new StringBuffer();

			InputStreamReader input = new InputStreamReader(context.openFileInput(KEYVALUES));
			BufferedReader reader = new BufferedReader(input);

			String line;
			while((line = reader.readLine()) != null)
				buffer.append(line);

			JSONObject json = new JSONObject(buffer.toString());
			Iterator<?> keys = json.keys();

			while(keys.hasNext()) {
				String key = (String)keys.next();
				keyvalues.put(key, json.getString(key));
			}
		}
		catch(Exception e) {
			Utils.error(e);
		}

		return keyvalues;
	}

}