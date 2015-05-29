package com.acordier.sequoia.view;

import controlP5.Bang;
import controlP5.ControlP5;
import controlP5.Group;
import controlP5.Numberbox;

import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_MONITOR_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_DOWN_BTN_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_UP_BTN_ID;

public class SequoiaTransposer extends Group {
	
	private int h, w;
	private float value;
	private Bang transposeUpBtn, transposedwnBtn;
	private SequoiaNumberBox monitor;
	
	private SequoiaTransposer(Builder builder){
		super(builder.cP5, builder.name);
		this.h = builder.h;
		this.w = builder.w;
		this.hideBar();
		this.setWidth(w);
		this.setHeight(h);
		this.setPosition(builder.x, builder.y);
		int btnH = (h-1)/2;
		transposeUpBtn = builder.cP5.addBang(TRANSPOSER_UP_BTN_ID);
		transposeUpBtn.setWidth(btnH).setHeight(btnH);
		transposeUpBtn.setPosition(0, 0);
		transposeUpBtn.setTriggerEvent(Bang.RELEASE);
		transposeUpBtn.setGroup(this);
		transposedwnBtn = builder.cP5.addBang(TRANSPOSER_DOWN_BTN_ID);
		transposedwnBtn.setWidth(btnH).setHeight(btnH);
		transposedwnBtn.setPosition(0, btnH+1);
		transposedwnBtn.setTriggerEvent(Bang.RELEASE);
		transposedwnBtn.setGroup(this);
		monitor = new SequoiaNumberBox.Builder(TRANSPOSER_MONITOR_ID).setPosition(btnH+1, 0).setDimensions(w-btnH, btnH*2+1 ).build(builder.cP5);
		monitor.setGroup(this);
		this.setHeight(transposeUpBtn.getHeight() + transposedwnBtn.getHeight() + 1);
	}
	
	public float getValue() {
		return value;
	}

	public static class Builder {

		private ControlP5 cP5;
		private String name;
		private float x, y;
		private int w, h;

		public Builder(String name) {
			this.name = name;

		}

		public Builder setPosition(float x, float y) {
			this.x = x;
			this.y = y;
			return this;
		}

		public Builder setDimensions(int w, int h) {
			this.w = w;
			this.h = h;
			return this;
		}

		public SequoiaTransposer build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new SequoiaTransposer(this);
		}
	}
}
