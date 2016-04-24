package com.csci5210.se.visualnovelengine.Types;

import com.csci5210.se.visualnovelengine.DialogueType;

public class IfStmt implements DialogueType {
	public enum Op { EQUALS, NOT_EQUALS };

	public String variable; // feel
	public String string; // good
	public Op op; // ==
	public boolean chained;

	public IfStmt(String variable, String string, String op, boolean chained) {
		this.variable = variable;
		this.string = string;
		if(op.equals("=="))
			this.op = Op.EQUALS;
		else if(op.equals("!="))
			this.op = Op.NOT_EQUALS;
		this.chained = chained;
	}

	public IfStmt(String variable, String string, Op op, boolean chained) {
		this.variable = variable;
		this.string = string;
		this.op = op;
		this.chained = chained;
	}

	public boolean evaluate(String dbString) {
		switch(op) {
			case EQUALS:
				return string.equals(dbString);
			case NOT_EQUALS:
				return !string.equals(dbString);
			default:
				return false;
		}
	}

	@Override
	public String toString() {
		return "Variable: " + variable + "\nString: " + string + "\nOperator: " + op + "\nChained: " + chained;
	}
}
