package com.acordier.sequoia.view;

import static com.acordier.sequoia.model.SequoiaConstants.IMG_DOWN_ARROW_ACTIVE;
import static com.acordier.sequoia.model.SequoiaConstants.IMG_DOWN_ARROW_DEFAULT;
import static com.acordier.sequoia.model.SequoiaConstants.IMG_UP_ARROW_ACTIVE;
import static com.acordier.sequoia.model.SequoiaConstants.IMG_UP_ARROW_DEFAULT;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_DOWN_BTN_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_MONITOR_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_UP_BTN_ID;
import controlP5.Bang;
import controlP5.ControlP5;
import controlP5.Group;
import controlP5.Numberbox;

public class SequoiaTransposer extends Group {
	
	private int h, w;
	private float value;
	private Bang transposeUpBtn, transposedwnBtn;
	private Numberbox monitor;
	
	private SequoiaTransposer(Builder builder){
		super(builder.cP5, builder.name);
		this.h = builder.h;
		this.w = builder.w;
		this.hideBar();
		this.setWidth(w);
		this.setHeight(h);
		this.setPosition(builder.x, builder.y);
		int btnH = (h)/2;
		transposeUpBtn = new SequoiaBang.Builder(TRANSPOSER_UP_BTN_ID).setPosition(0, 0).setDimensions(btnH, btnH).setImages(IMG_UP_ARROW_DEFAULT, IMG_UP_ARROW_ACTIVE).build(builder.cP5);
		transposeUpBtn.setWidth(btnH).setHeight(btnH);
		transposeUpBtn.setPosition(0, 0);
		transposeUpBtn.setTriggerEvent(Bang.RELEASE);
		transposeUpBtn.setGroup(this);
		transposedwnBtn = new SequoiaBang.Builder(TRANSPOSER_DOWN_BTN_ID).setPosition(0, btnH).setDimensions(btnH, btnH).setImages(IMG_DOWN_ARROW_DEFAULT, IMG_DOWN_ARROW_ACTIVE).build(builder.cP5);
		transposedwnBtn.setWidth(btnH).setHeight(btnH);
		transposedwnBtn.setPosition(0, btnH);
		transposedwnBtn.setTriggerEvent(Bang.RELEASE);
		transposedwnBtn.setGroup(this);
		monitor = new SequoiaNumberBox.Builder(TRANSPOSER_MONITOR_ID).setPosition(btnH+4, 0).setDimensions(w, h).build(cp5);
		monitor.setMin(2).setMax(4);
		monitor.setGroup(this);
		this.setHeight(transposeUpBtn.getHeight() + transposedwnBtn.getHeight() + 1);
		this.setWidth(btnH+w);
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
