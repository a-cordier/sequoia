package com.acordier.sequoia.view;

import processing.core.PApplet;
import processing.core.PFont;

import com.acordier.sequoia.common.Fonts;

import controlP5.Canvas;
import controlP5.ControlP5;

public class SequoiaSkin extends Canvas {
	
	//private Builder builder;
	
	private SequoiaSkin(Builder builder) {
		super();
		//this.builder = builder;
	}
	
	@Override
	public void draw(PApplet applet) {
		PFont font = Fonts.loadFont("coolvetica.ttf", 40);
		applet.textFont(font);
		applet.fill(134, 209, 64);
		applet.text("Sequoia", 5, 35);
		applet.textSize(20);
		applet.text("16 step sequencer", 80, 50);

	}
	public static class Builder {

		//private float x, y;

		public Builder(String name) {}

//		public Builder setPosition(float x, float y) {
//			this.x = x;
//			this.y = y;
//			return this;
//		}

//		public Builder setDimensions(int w, int h) {
//			return this;
//		}
		
		public SequoiaSkin build(ControlP5 cP5) {
			SequoiaSkin skin = new SequoiaSkin(this);
			cP5.addCanvas(skin);
			return skin;
		}
	}
}
