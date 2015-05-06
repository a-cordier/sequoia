package com.acordier.sequoia.view;

import processing.core.PApplet;
import controlP5.ControlP5;
import controlP5.Numberbox;

public class SequoiaNumberbox extends Numberbox {

	private SequoiaNumberbox(Builder builder) {
		super(builder.cP5, builder.name);
		this.setPosition(builder.x, builder.y);
		this.setSize(builder.w, builder.h);
		this.setColorForeground(this.getColor().getBackground());
		this.setMin(0);
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
		
		
		public SequoiaNumberbox build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new SequoiaNumberbox(this);
		}
	}
	
    @Override
    public Numberbox updateInternalEvents(PApplet theApplet) {
            if (isActive) {
                    if (!cp5.isAltDown()) {
                            if (_myNumberCount == VERTICAL) {
                                    setValue(_myValue + (theApplet.pmouseY - theApplet.mouseY) * _myMultiplier);
                            } else {
                                    setValue(_myValue + (theApplet.pmouseX - theApplet.mouseX) * _myMultiplier);
                            }
                    }
            }
            return this;
    }
}
