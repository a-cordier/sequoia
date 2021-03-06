package com.acordier.sequoia.view;

import processing.core.PImage;

import com.acordier.sequoia.common.Images;

import controlP5.Bang;
import controlP5.ControlP5;

public class SequoiaBang extends Bang {

	private PImage[]images;
	
	private SequoiaBang(Builder builder){
		super(builder.cP5, builder.name);
		this.setPosition(builder.x, builder.y);
		this.setWidth(builder.w);
		this.setHeight(builder.h);
		if(builder.images!=null){
			this.images = new PImage[3];
			for(int i = 0; i < builder.images.length; i++){
				images[i]= Images.loadImage(builder.images[i]);
			}
			if(builder.w<0 && builder.h<0){
				this.width=images[0].width;
				this.height=images[0].height;
			}else{
				this.setWidth(builder.w);
				this.setHeight(builder.h);
				for(int i = 0; i < builder.images.length; i++){
					images[i].resize(this.width, this.height);
				}
			}

			this
		     .setImages(images[0],images[0] ,images[1])
		     .updateSize();
		}
		
	}
	
	public static class Builder {
		
		private ControlP5 cP5;
		private String name;
		private float x, y;
		private int w, h;
		private String[]images;
		
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
		
		public Builder setImages(String ... images){
			this.images=images;
			return this;
		}
		
		
		public SequoiaBang build(ControlP5 cP5) {
			this.cP5 = cP5;
			return new SequoiaBang(this);
		}
	
	}
}
