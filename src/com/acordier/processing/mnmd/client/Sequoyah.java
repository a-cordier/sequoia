package com.acordier.processing.mnmd.client;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import processing.core.PApplet;

import com.acordier.processing.mnmd.features.Midinette;
import com.acordier.processing.mnmd.features.Youki;

import controlP5.CheckBox;
import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Knob;
import controlP5.Matrix;

public class Sequoyah extends PApplet {

	private static final long serialVersionUID = 1L;
	ControlP5 cP5;
	Knob filterKnob;
	DropdownList midiDevices;
	List<CheckBox> stepSequencer;
	Matrix m;
	Receiver receiver;
	MidiDevice midiOutDevice;
	
	@Override
	public void setup() {

		size(400, 300);
		cP5 = new ControlP5(this);
		final Youki youki = new Youki(this);
		Midinette midinette = new Midinette(youki);
		stepSequencer = new ArrayList<CheckBox>(midinette.getStepSequence()
				.size());
		midinette.randomize(3);
		midiDevices = cP5.addDropdownList("midi-devices")
				.setPosition(width - 100, 10).setWidth(100);
		MidiDevice device;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0, j = 0; i < infos.length; i++) {
			try {
				device = MidiSystem.getMidiDevice(infos[i]);
				if(device.getMaxReceivers()!=0){
					midiDevices.addItem(infos[i].getName(), j);
					j++;
				};
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}

		}
		midiDevices.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent event) {
				int idx = ((int) event.getValue());
				midiOutDevice = getMidiOutDevice(midiDevices.getItem(idx).getName());	
				try {
					if(!midiOutDevice.isOpen()){
						System.out.println("not opened");
						midiOutDevice.open();
					}
				} catch (MidiUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		int tempo = 120;
		int interval = (int) ((60.F / (tempo * 4)) * 1000);
		m = cP5.addMatrix("stepSeq").setPosition(0, height - 200)
				.setSize(width, 200).setInterval(interval).setGrid(16, 12)
				.setMode(ControlP5.MULTIPLES).setColorBackground(color(120))
				.setBackground(color(40));
		m.addListener(new ControlListener() {
			
			@Override
			public void controlEvent(ControlEvent event) {
				try {
					midiOutDevice.getReceiver().send(new ShortMessage(ShortMessage.NOTE_ON, 1, 60, 100), -1);
				} catch (MidiUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidMidiDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		});
		stop = true;
		m.stop();
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
			m.pause();
		} else
			m.play();
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

}
