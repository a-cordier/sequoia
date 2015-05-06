package com.acordier.sequoia.core;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class SequoiaSequencer {
	
	private MidiDevice midiOutputDevice;
	private int tempo;
	private int duration;
	
	public MidiDevice getMidiOutputDevice() {
		return midiOutputDevice;
	}

	public void setMidiOutputDevice(MidiDevice midiOutputDevice) {
		this.midiOutputDevice = midiOutputDevice;
	}
	
	public void playNote(int note, int velocity) {
		try {
			System.out.println("playing note");
			midiOutputDevice.getTransmitter().getReceiver().send(new ShortMessage(ShortMessage.NOTE_ON, note, velocity), -1);
			System.out.println("receiver class: " + midiOutputDevice.getReceiver().getClass().getName());
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("no receiver");
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
