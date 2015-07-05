package com.acordier.sequoia.common;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PConstants;
import processing.core.PImage;

public class Images {
	
	public static PImage loadImage(String fileName) {
		PImage pImage = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(Images.class
					.getClassLoader().getResourceAsStream(fileName));
			pImage = new PImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), PConstants.ARGB);
			bufferedImage.getRGB(0, 0, pImage.width, pImage.height,
					pImage.pixels, 0, pImage.width);
			pImage.updatePixels();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pImage;
	}
	
	public static PImage loadImage(String fileName, int w, int h) {
		PImage pImage = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(Images.class
					.getClassLoader().getResourceAsStream(fileName));
			pImage = new PImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), PConstants.ARGB);
			bufferedImage.getRGB(0, 0, pImage.width, pImage.height,
					pImage.pixels, 0, pImage.width);
			pImage.updatePixels();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pImage.resize(w, h);
		return pImage;
	}
	
	
	public static int getImgWidth(String fileName) {
		PImage pImage = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(Images.class
					.getClassLoader().getResourceAsStream(fileName));
			pImage = new PImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), PConstants.ARGB);
			bufferedImage.getRGB(0, 0, pImage.width, pImage.height,
					pImage.pixels, 0, pImage.width);
			pImage.updatePixels();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return pImage.width;
	}
	
	public static int getImgHeight(String fileName) {
		PImage pImage = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(Images.class
					.getClassLoader().getResourceAsStream(fileName));
			pImage = new PImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), PConstants.ARGB);
			bufferedImage.getRGB(0, 0, pImage.width, pImage.height,
					pImage.pixels, 0, pImage.width);
			pImage.updatePixels();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return pImage.height;
	}
}
