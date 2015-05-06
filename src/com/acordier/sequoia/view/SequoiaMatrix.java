package com.acordier.sequoia.view;

import java.awt.Color;

import processing.core.PApplet;
import controlP5.ControlP5;
import controlP5.Matrix;

public class SequoiaMatrix extends Matrix {
	
	private SequoiaMatrix(Builder builder) {
		super(builder.cP5, builder.name);
		this.setPosition(builder.x, builder.y);
		this.setSize(builder.w, builder.h);
		this.setInterval(builder.i);
		this.setGrid(16, 12);
		this.setMode(ControlP5.MULTIPLES);
		this.setColorBackground(new Color(120, 120, 120).getRGB());
		this.setBackground(new Color(40, 40, 40).getRGB());
	}
	
	@Override
    public void mouseReleased() {
            if (isActive) {
                    isActive = false;
            }
            isPressed = false;
            currentX = -1;
            currentY = -1;
   
    }
	
	@Override
	public void mousePressed( ) {
		System.out.println("mouse pressed");
		isActive = getIsInside( );
		if ( getIsInside( ) ) {
			isPressed = true;
			System.out.println(this.stepX);
		}
	}
	
	public static class Builder {
		
		private ControlP5 cP5;
		private String name;
		private float x, y;
		private int w, h;
		private int i;
		
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
		
		public Builder setInterval(int i) {
			this.i = i;
			return this;
		}
		
		public SequoiaMatrix build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new SequoiaMatrix(this);
		}
		
	}
}
