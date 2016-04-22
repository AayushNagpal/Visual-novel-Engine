
package com.csci5210.se.visualnovelengine;

import java.io.InputStream;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilderFactory;

import com.csci5210.se.visualnovelengine.AnimationHandler.AnimationType;
import com.csci5210.se.visualnovelengine.Types.Character;
import com.csci5210.se.visualnovelengine.Types.Event;
import com.csci5210.se.visualnovelengine.Types.GoTo;
import com.csci5210.se.visualnovelengine.Types.IfStmt;
import com.csci5210.se.visualnovelengine.Types.Player;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;

public class Parser {

	
	@SuppressLint("DefaultLocale")
	public static Stack<DialogueType> parseXML(InputStream stream) {
		Stack<DialogueType> dialogues = new Stack<DialogueType>();

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream, null);
			NodeList items = doc.getElementsByTagName("dialogue");

			for(int i = items.getLength(); i >= 0; --i) {
				Element e = (Element)items.item(i);
				if(e == null)
					continue;

				if(e.hasAttribute("if")) {
					// example: parsing #feel# == 'good'
					boolean chained = e.getAttribute("chained").equals("true");
					String[] token = e.getAttribute("if").split("#");
					String name = token[1]; // feel

					token = token[2].split("'");
					String op = token[0].replaceAll("\\s", ""); // ==
					String string = token[1].replaceAll("'", ""); // good

					dialogues.push(new IfStmt(name, string, op, chained));
				}
				else if(e.hasAttribute("event")) {
					String event = e.getAttribute("event");
					String resource = e.getAttribute("resource");
					String loop = e.getAttribute("loop");

					Event evnt = loop == "" ? new Event(event, resource) : new Event(event, resource, loop.equals("true"));
					dialogues.push(evnt);
				}
				else if(e.hasAttribute("goTo")) {
					String script = e.getAttribute("goTo");

					dialogues.push(new GoTo(script));
				}
				else {
					String name = e.getAttribute("name");
					String text = e.getFirstChild().getNodeValue();
					String character = e.getAttribute("character");
					String mode = e.getAttribute("mode");
					String animation = e.getAttribute("animation");

					if(character.equals("")) { // player
						if(mode.equals("choice") && e.hasAttribute("choices")) {
							String[] choices = e.getAttribute("choices").split("(\\s+)?\\|(\\s+)?");
							String[] choicesToView = text.split("\\s+\\|\\s+");

							dialogues.push(new Player(name, mode, choices, choicesToView));
						}
						else
							dialogues.push(new Player(name, mode, text));
					}
					else { // character
						AnimationType animationType = animation.equals("") ? AnimationType.NOTHING : AnimationType.valueOf(animation.toUpperCase());

						dialogues.push(new Character(name, character, text, animationType));
					}
				}
			}
		}
		catch(Exception e) {}

		return dialogues;
	}
}
