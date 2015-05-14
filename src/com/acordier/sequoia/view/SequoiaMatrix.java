package com.acordier.sequoia.view;

import java.awt.Color;

import processing.core.PApplet;

import com.acordier.sequoia.model.SequoiaStepSequence;

import controlP5.ControlP5;
import controlP5.Matrix;

public class SequoiaMatrix extends Matrix {
	
	private SequoiaStepSequence steps;
	
	private SequoiaMatrix(Builder builder) {
		super(builder.cP5, builder.name);
		this.setPosition(builder.x, builder.y);
		this.setSize(builder.w, builder.h);
		this.setInterval(builder.i);
		this.setGrid(16, 12);
		this.setMode(ControlP5.MULTIPLES);
		this.setColorBackground(new Color(120, 120, 120).getRGB());
		this.setBackground(new Color(40, 40, 40).getRGB());
		steps = SequoiaStepSequence.getInstance();
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
	public Matrix updateInternalEvents(PApplet theApplet) {
		setIsInside(inside());

		if (getIsInside()) {
			if (isPressed) {
				int tX = (int) ((theApplet.mouseX - position.x) / stepX);
				int tY = (int) ((theApplet.mouseY - position.y) / stepY);

				if (tX != currentX || tY != currentY) {
					tX = PApplet.min(PApplet.max(0, tX), _myCellX);
					tY = PApplet.min(PApplet.max(0, tY), _myCellY);
					boolean isMarkerActive = (_myCells[tX][tY] == 1) ? true
							: false;
					_myCells[tX][tY] = isMarkerActive ? 0 : 1;
					if(!isMarkerActive) {
						steps.get(tX).enable();
						steps.get(tX).setNote((11 - tY) + 12 * 5);
					} else {
						steps.get(tX).disable();
					}
					currentX = tX;
					currentY = tY;
				}
			}
		}
		return this;
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

		public Builder setDimensions(int w, int h) {
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
