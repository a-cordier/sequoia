package com.acordier.sequoia.common;

import java.awt.Color;

public class Colors {

	public static int color(int r, int g, int b) {
		return new Color(r, g, b).getRGB();
	}

	public static int color(int g) {
		return new Color(g,g,g).getRGB();
	}
}
