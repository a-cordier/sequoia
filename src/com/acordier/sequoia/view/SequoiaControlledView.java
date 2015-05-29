package com.acordier.sequoia.view;

import static com.acordier.sequoia.model.SequoiaConstants.DEFAULT_TEMPO;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

import processing.core.PApplet;

import com.acordier.sequoia.controller.SequoiaViewController;

import controlP5.Bang;
import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Matrix;
import controlP5.Numberbox;

public class SequoiaControlledView extends PApplet {

	private static final long serialVersionUID = 1L;
	private ControlP5 cP5;
	private DropdownList midiDevices;
	private Matrix matrix;
	private Numberbox tempoSelector;
	private SequoiaViewController viewController;
	private SequoiaTransposer transposer;
	
	@Override
	public void setup() {
		stop = true;
		size(400, 300);
		
		cP5 = new ControlP5(this);
		viewController = new SequoiaViewController();
		
		midiDevices = new SequoiaDropdown.Builder("MidiOut").setDimensions(100, 100).setPosition(width-100, 10).build(cP5);
		viewController.bindMidiOutputDeviceSelector(midiDevices);
		
		matrix = new SequoiaMatrix.Builder("matrix").setPosition(0, height-200).setDimensions(width,  200).setInterval(DEFAULT_TEMPO).build(cP5).stop();
		viewController.bindMatrix(matrix);
		
		tempoSelector = new SequoiaNumberBox.Builder("tempo").setPosition(0, 0).setDimensions(50, 20).build(cP5);
		viewController.bindTempo(tempoSelector);
		
		transposer = new SequoiaTransposer.Builder("transpose").setPosition(0, 50).setDimensions(50, 20).build(cP5);
		viewController.bindTransposer(transposer);
	}

	public static MidiDevice getMidiOutDevice(String name) {
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		MidiDevice device = null;
		for (Info info : infos) {
			if (info.getName().equals(name)) {
				try {
					device = MidiSystem.getMidiDevice(info);
					if(device.getMaxReceivers()!=0){
						return device;
					}
				} catch (MidiUnavailableException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public void draw() {
		background(255);
		if (stop) {
			matrix.pause();
		} else
			matrix.play();
	}

	boolean stop;

	@Override
	public void keyPressed() {
		if (key == ' ') {
			stop = !stop;
		}
	}
	
	public void stepSeq(int i, int j){
	}
	
	/** just to get rid of the no such method exception error */
	public void matrix(int a, int b){}
}
