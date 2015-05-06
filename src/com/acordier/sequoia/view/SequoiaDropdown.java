package com.acordier.sequoia.view;

import controlP5.ControlP5;
import controlP5.DropdownList;

public class SequoiaDropdown extends DropdownList	{
	
	private SequoiaDropdown(Builder builder) {
		super(builder.cP5, builder.name);
		this.setWidth(builder.w);
		this.setHeight(builder.h);
		this.setPosition(builder.x, builder.y);
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
		public SequoiaDropdown build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new SequoiaDropdown(this);
		}
	}
}
