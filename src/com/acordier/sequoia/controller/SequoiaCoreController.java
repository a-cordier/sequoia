package com.acordier.sequoia.controller;

import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;

import com.acordier.mnmd.core.MidiBroker;
import com.acordier.sequoia.core.SequoiaSequencer;

public class SequoiaCoreController {

	private SequoiaSequencer sequencer;

	public SequoiaCoreController() {
		this.sequencer = new SequoiaSequencer();
	}

	/* TODO: add internal bus */
	public List<MidiDevice> getMidiInputDevices() {
		return MidiBroker.getMidiInputDevices();
	}

	/* TODO: add internal bus */
	public List<MidiDevice> getMidiOutputDevices() {
		return MidiBroker.getMidiOutputDevices();
	}

	public void setMidiOutputDevice(String name) {
		MidiDevice device = MidiBroker.getMidiOutputDevice(name);
		try {
			if(!device.isOpen())
				device.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sequencer.setMidiOutputDevice(device);
	}

	public MidiDevice getMidiOutputDevices(String name) {
		return MidiBroker.getMidiOutputDevice(name);
	}

	public void trigger(int x){
		sequencer.trigger(x);
	}
}
