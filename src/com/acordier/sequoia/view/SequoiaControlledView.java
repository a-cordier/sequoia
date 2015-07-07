package com.acordier.sequoia.view;

import static com.acordier.sequoia.model.SequoiaConstants.DEFAULT_TEMPO;
import static com.acordier.sequoia.model.SequoiaConstants.PLAY_ACTIVE;
import static com.acordier.sequoia.model.SequoiaConstants.PLAY_DEFAULT;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import processing.core.PApplet;
import processing.core.PFont;

import com.acordier.sequoia.common.Colors;
import com.acordier.sequoia.common.Fonts;
import com.acordier.sequoia.controller.SequoiaViewController;

import controlP5.ControlP5;
import controlP5.Matrix;
import controlP5.Textfield;

public class SequoiaControlledView extends PApplet {

	private static final long serialVersionUID = 1L;
	private ControlP5 cP5;
	private Matrix matrix;
	private SequoiaToggle playBtn;
	private SequoiaTransposer transposer;
	private SequoiaTempo tempo;
	private SequoiaToggle settingsBtn;
	private SequoiaBang backBtn;
	private PFont font;
	boolean stop;

	SequoiaViewController viewController;
	
	@Override
	public void setup() {
		stop = true;
		size(400, 300);
		frame.setResizable(false);
		cP5 = new ControlP5(this);
		viewController = new SequoiaViewController(this);
		
		cP5.setColorBackground(Colors.color(147,177,198));
		cP5.setColorForeground(Colors.color(199,208,213));
		cP5.setColorActive(Colors.color(134, 209, 64)); // variante
		

		font = Fonts.loadFont("coolvetica.ttf", 40);
		textFont(font);
		int tempoWidth = (int) textWidth("2222");
		int transposerWidth = (int) textWidth("44");

		int xPos = 0;
		
		/* This is the core controller of the sequencer,
		 * it basically trigger a midi event when cursor
		 * overlapp an active cell
		 */
		matrix = new SequoiaMatrix.Builder("matrix")
				.setPosition(xPos, height - (height * 2 / 3))
				.setDimensions(width, (height * 2 / 3)).setMargin(10)
				.setInterval(DEFAULT_TEMPO).build(cP5).stop();
		noStroke();
		viewController.bindMatrix(matrix);
		
		/* Tempo selector */
		tempo = new SequoiaTempo.Builder("tempo")
				.setPosition(xPos, 2 * (height - matrix.getHeight()) / 3)
				.setDimensions(tempoWidth, (height - matrix.getHeight()) / 3)
				.build(cP5);
		viewController.bindTempo(tempo);

		xPos += tempo.getWidth() + 8;
		
		/* Transpose (up|down) the whole sequence playing in matrix*/
		transposer = new SequoiaTransposer.Builder("transpose")
				.setPosition(xPos, 2 * (height - matrix.getHeight()) / 3)
				.setDimensions(transposerWidth,
						(height - matrix.getHeight()) / 3).build(cP5);
		viewController.bindTransposer(transposer);

		xPos += transposer.getWidth() + 8;

		/* play pause button. Bound as well to space key press */
		playBtn = new SequoiaToggle.Builder("play btn")
				.setPosition(xPos, 2* (height - matrix.getHeight()) / 3)
				.setDimensions((height - matrix.getHeight()) / 2,
						(height - matrix.getHeight()) / 3)
				.setImages(PLAY_DEFAULT, PLAY_ACTIVE).build(cP5);
		viewController.bindPlayBtn(playBtn);
		
		xPos += playBtn.getWidth() + 4;
		
		backBtn = new SequoiaBang.Builder("stop btn")
			.setPosition(xPos, 2* (height - matrix.getHeight()) / 3)
			.setDimensions((height - matrix.getHeight()) / 3, (height - matrix.getHeight()) / 3)
			.setImages("back_default.png", "back_active.png").build(cP5);
		viewController.bindBackBtn(backBtn);
		
		/* settings window */
		settingsBtn = new SequoiaToggle.Builder("settings btn")
				.setPosition(width-110, 10)
				.setImages("setting_default.png", "setting_active.png")
				.build(cP5);
		viewController.bindSetting(settingsBtn);
		
		/* additional layer of pics and|or graphics */
		new SequoiaSkin.Builder("").build(cP5);
	}

	@Override
	public void draw() {
		background(245);
		if (stop) {
			matrix.pause();
		} else
			matrix.play();
		fill(255);
	}

	@Override
	public void keyPressed() {
		if (key == ' ') {
			playBtn.toggle();
		}
	}

	public void toggleTransport() {
		stop = !stop;
	}

	public Matrix getMatrixController() {
		return matrix;
	}

	public void stepSeq(int i, int j) {
	}

	/** just to get rid of the no such method exception error */
	public void matrix(int a, int b) {
	}

	public ControlFrame addControlFrame(String theName, int theWidth,
			int theHeight) {
		Frame frame = new Frame(theName);
		final ControlFrame controlFrame = new ControlFrame(this, theWidth, theHeight);
		frame.add(controlFrame);
		controlFrame.init();
		controlFrame.setFrame(frame);
		frame.setTitle(theName);
		frame.setSize(controlFrame.w, controlFrame.h);
		frame.setLocation(this.getMousePosition().x, this.getMousePosition().y);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {
	        	we.getWindow().dispose();
	        	settingsBtn.setOff();
	         }
	     }
	);
		return controlFrame;
	}


	
	public static void main(String args[]) {
		PApplet.main(new String[] { "com.acordier.sequoia.view.SequoiaControlledView" });
	}
	
	/* Additional Applet used to open|close a setting dialog */
	public class ControlFrame extends PApplet {

		
		private static final long serialVersionUID = 1L;
		private ControlP5 _cP5;
		private SequoiaControlledView parent;
		private int w, h;
		private SequoiaListBox midiOutDevices;
		private Frame parentFrame;
		
		public ControlFrame(SequoiaControlledView parent, int theWidth, int theHeight) {
			this.parent = parent;
			w = theWidth;
			h = theHeight;
			
		
		}

		public void setup() {
			size(w, h);
			frameRate(25);
			_cP5 = new ControlP5(this);
			Textfield midiOutDevicesLabel = _cP5.addTextfield("Midi out setting label").setPosition(20, 20);
			midiOutDevicesLabel.setText("Available midi out devices");
			midiOutDevicesLabel.setColor(255);
			midiOutDevicesLabel.setColorBackground(ControlP5.getColor().getBackground());
			
			midiOutDevices = new SequoiaListBox.Builder("Midi out").setDimensions(200, 200).setPosition(20, 40)
					.build(_cP5);
			midiOutDevices.setColorBackground(Colors.color(199,208,213));
			//midiOutDevices.setColorForeground(Colors.color(236,88,59));
			parent.viewController.bindMidiOutputDeviceSelector(midiOutDevices);
			
		}

		public void draw() {
			background(250);
		}
		
		public ControlP5 control() {
			return _cP5;
		}
		
		public Object getPArent(){
			return parent;
		}
		
		public Frame getFrame(){
			return parentFrame;
		}
		
		public void setFrame(Frame parentFrame){
			this.parentFrame = parentFrame;
		}
	}

}
