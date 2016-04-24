package com.csci5210.se.visualnovelengine.Types;

import android.annotation.SuppressLint;
import com.csci5210.se.visualnovelengine.DialogueType;

public class Event implements DialogueType {
	public enum EventType { SETBACKGROUNDIMAGE, PLAYSOUND, STOPSOUND, SETCHARACTER };

	public EventType name; // Type of the event
	public String resource; // Resource to load
	public boolean loop; // Just for playSound

	@SuppressLint("DefaultLocale")
	public Event(String name, String resource) {
		for(EventType e : EventType.values()) {
			if(name.toUpperCase().equals(e.toString())) {
				this.name = e;
				break;
			}
		}

		this.resource = resource;
	}

	@SuppressLint("DefaultLocale")
	public Event(String name, String resource, boolean loop) {
		for(EventType e : EventType.values()) {
			if(name.toUpperCase().equals(e.toString())) {
				this.name = e;
				break;
			}
		}

		this.resource = resource;
		this.loop = loop;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nResource: " + resource + (loop ? "\nLoop: " + loop : "");
	}
}