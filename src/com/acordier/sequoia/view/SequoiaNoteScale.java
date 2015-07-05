package com.acordier.sequoia.view;

import processing.core.PApplet;

import com.acordier.sequoia.common.Images;

import controlP5.Canvas;
import controlP5.ControlP5;
import controlP5.Matrix;

public class SequoiaNoteScale extends Canvas {
	
	private final String[] notes = {
			"A ", "A#", "B ", "C ", "C#", "D ", "D#", "E ", "F ", "F#", "G ", "G#"
	};
	
	private Builder builder;
	private int keySize;
	
	private SequoiaNoteScale(Builder builder) {
		super();
		this.builder = builder;
		int matrixHeight = builder.matrix.getHeight();
		int nCells = builder.matrix.getCells()[0].length;
		keySize = matrixHeight / nCells;
		System.out.println(keySize * nCells);
	}
	

	@Override
	public void draw(PApplet applet) {
		float vPos = builder.y;
		for(int i = notes.length-1; i>-1; i--) {
			if(!notes[i].contains("#")){
				System.out.println(applet.getClass().getName());
				applet.image(Images.loadImage("white.png", keySize, keySize), builder.x, vPos);
			}else {
							applet.image(Images.loadImage("black.png", keySize, keySize), builder.x, vPos);
			}
		 	vPos += keySize;
		}
	}
	
	public static class Builder {

		private float x, y;

		
		// test
		private Matrix matrix;
		
		public Builder(String name) {}

		public Builder setPosition(float x, float y) {
			this.x = x;
			this.y = y;
			return this;
		}

		public Builder setDimensions(int w, int h) {
			return this;
		}
		
		public Builder setMatrix(Matrix matrix){
			this.matrix = matrix;
			return this;
		}
		
		public SequoiaNoteScale build(ControlP5 cP5) {
			SequoiaNoteScale noteScale = new SequoiaNoteScale(this);
			cP5.addCanvas(noteScale);
			return noteScale;
		}
	}



}



