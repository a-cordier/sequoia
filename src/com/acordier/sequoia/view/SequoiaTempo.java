package com.acordier.sequoia.view;

import static com.acordier.sequoia.model.SequoiaConstants.TEMPO_DOWN_BTN_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TEMPO_MONITOR_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TEMPO_UP_BTN_ID;
import static com.acordier.sequoia.model.SequoiaConstants.IMG_DOWN_ARROW_DEFAULT;
import static com.acordier.sequoia.model.SequoiaConstants.IMG_DOWN_ARROW_ACTIVE;
import static com.acordier.sequoia.model.SequoiaConstants.IMG_UP_ARROW_DEFAULT;
import static com.acordier.sequoia.model.SequoiaConstants.IMG_UP_ARROW_ACTIVE;

import controlP5.Bang;
import controlP5.ControlP5;
import controlP5.Group;
import controlP5.Numberbox;

public class SequoiaTempo extends Group {
	
	private int h, w;
	private float value;
	private Bang tempoUpBtn, tempodwnBtn;
	private Numberbox monitor;
	
	private SequoiaTempo(Builder builder){
		super(builder.cP5, builder.name);
		this.h = builder.h;
		this.w = builder.w;
		this.hideBar();
		this.setWidth(w);
		this.setHeight(h);
		this.setPosition(builder.x, builder.y);
		System.out.println(h);
		int btnH = (h)/2;
		System.out.println(btnH);

		//tempoUpBtn = builder.cP5.addBang(TEMPO_UP_BTN_ID);
		tempoUpBtn = new SequoiaBang.Builder(TEMPO_UP_BTN_ID).setPosition(0, 0).setDimensions(btnH, btnH).setImages(IMG_UP_ARROW_DEFAULT,IMG_UP_ARROW_ACTIVE).build(builder.cP5);
		//tempoUpBtn.setWidth(btnH).setHeight(btnH);
		//tempoUpBtn.setPosition(0, 0);
		tempoUpBtn.setTriggerEvent(Bang.RELEASE);
		tempoUpBtn.setGroup(this);
		tempodwnBtn = new SequoiaBang.Builder(TEMPO_DOWN_BTN_ID).setPosition(0, btnH).setDimensions(btnH, btnH).setImages(IMG_DOWN_ARROW_DEFAULT,IMG_DOWN_ARROW_ACTIVE).build(builder.cP5);
		tempodwnBtn.setTriggerEvent(Bang.RELEASE);
		tempodwnBtn.setGroup(this);
		monitor = new SequoiaNumberBox.Builder(TEMPO_MONITOR_ID).setPosition(btnH+4, 0).setDimensions(w, h).build(cp5);
		monitor.setMin(60).setMax(240);
		monitor.setGroup(this);
		this.setHeight(tempodwnBtn.getHeight() + tempodwnBtn.getHeight() + 1);
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

		public SequoiaTempo build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new SequoiaTempo(this);
		}
	}
}
