package com.acordier.sequoia.view;

import com.acordier.sequoia.view.SequoiaNumberBox.Builder;

import controlP5.Bang;
import controlP5.ControlP5;

public class SequoiaBang extends Bang {

	
	private SequoiaBang(Builder builder){
		super(builder.cP5, builder.name);
		this.setPosition(builder.x, builder.y);
		this.setWidth(builder.w);
		this.setHeight(builder.h);
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
		
		public Builder setDimensions(int w, int h){
			this.w = w;
			this.h = h;
			return this;		
		}
		
		
		public SequoiaBang build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new SequoiaBang(this);
		}
	}
}
