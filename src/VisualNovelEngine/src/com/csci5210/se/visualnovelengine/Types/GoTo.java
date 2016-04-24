package com.csci5210.se.visualnovelengine.Types;

import com.csci5210.se.visualnovelengine.DialogueType;

public class GoTo implements DialogueType {
	public String script;
	
	public GoTo(String script) {		
		this.script = script;
	}

	@Override
	public String toString() {
		return "Go to script: " + script;
	}
}