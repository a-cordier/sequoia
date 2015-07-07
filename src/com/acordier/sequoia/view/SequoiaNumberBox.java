package com.acordier.sequoia.view;

import processing.core.PApplet;

import com.acordier.sequoia.common.Fonts;

import controlP5.ControlP5;
import controlP5.Numberbox;

public class SequoiaNumberBox extends Numberbox {

	private SequoiaNumberBox(Builder builder) {
		super(builder.cP5, builder.name);
		this.setPosition(builder.x, builder.y);
		this.setSize(builder.w, builder.h);
		this.setColorForeground(this.getColor().getBackground());
		this.setMin(20);
		this.setMax(200);
		this.setDecimalPrecision(0);
		this.getValueLabel().setFont(Fonts.loadFont("coolvetica.ttf", 30));
		getValueLabel().getFont().getWidth();
//		this.getValueLabel().getStyle().setMarginLeft(10);
//		this.getCaptionLabel()
//		 .setFont(Fonts.loadFont("coolvetica.ttf", 12))
//	     .toUpperCase(false)
//	     .setSize(24).getStyle();
//		this.getCaptionLabel().align(ControlP5.TOP, ControlP5.TOP);
//		this.getCaptionLabel().getStyle().marginTop = -7;
//		this.getCaptionLabel().getStyle().marginLeft = 10;
		this.getCaptionLabel().setVisible(false);
		System.out.println(this.getLabel());
	}
	
	/*
	 * Overriding this method to reverse drag order
	 * (non-Javadoc)
	 * @see controlP5.Numberbox#updateInternalEvents(processing.core.PApplet)
	 */
    @Override
    public Numberbox updateInternalEvents(PApplet theApplet) {
            if (isActive) {
            		float _value = _myValue;
                    if (!cp5.isAltDown()) {
                            if (_myNumberCount == VERTICAL) {
                                    setValue(_myValue + (theApplet.pmouseY - theApplet.mouseY) * _myMultiplier);
                            } else {
                                    setValue(_myValue + (theApplet.pmouseX - theApplet.mouseX) * _myMultiplier);
                            }
                    }
                    if(this.getValue()<100 && _value >= 100){
                    	this.getValueLabel().getStyle().marginLeft +=5;         
                    }else if(this.getValue()>100 && _value <= 100){
                    	this.getValueLabel().getStyle().marginLeft -=5;         
                    }
            }
  
            return this;
    }
	
	public static class Builder {
		
		private ControlP5 cP5;
		private String name;
		private float x, y;
		private int w, h;
		//private float marginLeft, marginTop, marginRight, marginBottom;
		
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
		
//		public Builder setCaptionMargins(float l, float t, float r, float b) {
//			this.marginLeft = l;
//			this.marginTop = t;
//			this.marginRight = r;
//			this.marginBottom = b;
//			return this;
//		}
			
		public SequoiaNumberBox build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new SequoiaNumberBox(this);
		}
		
	}
	

}
