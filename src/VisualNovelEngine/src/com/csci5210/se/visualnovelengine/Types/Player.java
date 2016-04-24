package com.csci5210.se.visualnovelengine.Types;

import com.csci5210.se.visualnovelengine.DialogueType;
import com.csci5210.se.visualnovelengine.Utils;

public class Player implements DialogueType {	
	public String name; // Name of the variable
	public String mode; // [ input, choice ] if is the player
	public String[] choicesToView; // Choices to see
	public String[] choices; // Choices to store
	public String text; // For text inputs
	
	public Player(String name, String mode, String[] choices, String[] choicesToView) {
		this.name = name;
		this.mode = mode;
		this.choices = choices;
		this.choicesToView = choicesToView;
		this.text = "";
	}
	
	public Player(String name, String mode, String text) {
		this.name = name;
		this.mode = mode;
		this.text = text;
	}

	@Override
	public String toString() {
		if(mode.equals("choice"))
			return "Name: " + name + "\nMode: " + mode + "\nText: " + text + "\nChoices: [ " + Utils.join(choices, ", ") + " ]\nChoices to view: [ " + Utils.join(choicesToView, ", ") + " ]";
		else
			return "Name: " + name + "\nMode: " + mode;
	}
}