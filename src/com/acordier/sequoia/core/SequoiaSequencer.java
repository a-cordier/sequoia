package com.acordier.sequoia.core;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

import com.acordier.sequoia.model.SequoiaStep;
import com.acordier.sequoia.model.SequoiaStepSequence;

public class SequoiaSequencer {
	
	private MidiDevice midiOutputDevice;
	private SequoiaStepSequence steps;
	private int octave;
	
	public SequoiaSequencer(){
		this.steps = SequoiaStepSequence.getInstance();
		this.octave = 3;
	}
	
	
	public void trigger(int x) {
		try {
			SequoiaStep current = steps.get(x);
			midiOutputDevice.getReceiver().send(new ShortMessage(ShortMessage.NOTE_ON, 4, current.getNote() + 12 * octave , current.getVelocity()), -1 );
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("no receiver");
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
	
	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}
	
	public MidiDevice getMidiOutputDevice() {
		return midiOutputDevice;
	}

	public void setMidiOutputDevice(MidiDevice midiOutputDevice) {
		this.midiOutputDevice = midiOutputDevice;
	}
}
