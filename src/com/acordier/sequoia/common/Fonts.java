package com.acordier.sequoia.common;

import java.awt.Font;

import processing.core.PFont;

public class Fonts {
	
	public static PFont loadFont(String fileName, float size){
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,  Fonts.class.getClassLoader().getResourceAsStream("coolvetica.ttf"));
			font = font.deriveFont(size);
		} catch (Exception ex) {
			ex.printStackTrace();;
			font = new Font("serif", Font.PLAIN, 24);
		}
		return new PFont(font, false);
	}
}
