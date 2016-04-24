package com.csci5210.se.visualnovelengine.Types;

import com.csci5210.se.visualnovelengine.AnimationHandler.AnimationType;
import com.csci5210.se.visualnovelengine.DialogueType;

public class Character implements DialogueType {
	public String name; // Name of the character
	public String character; // Name of the drawable resource
	public String text; // Dialogue
	public AnimationType animation; // Type of animation

	public Character(String name, String character, String text, AnimationType animation) {
		this.name = name;
		this.character = character;
		this.text = text;
		this.animation = (animation == null) ? AnimationType.NOTHING : animation;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nCharacter: " + character + "\nText: " + text + "\nAnimation: " + animation;
	}
}
