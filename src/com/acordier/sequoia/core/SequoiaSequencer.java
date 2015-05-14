package com.acordier.sequoia.core;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

import com.acordier.sequoia.model.SequoiaStep;
import com.acordier.sequoia.model.SequoiaStepSequence;

public class SequoiaSequencer {
	
	private MidiDevice midiOutputDevice;
	private int tempo;
	private int duration;
	private SequoiaStepSequence steps;
	
	public SequoiaSequencer(){
		this.steps = SequoiaStepSequence.getInstance();
	}
	
	public MidiDevice getMidiOutputDevice() {
		return midiOutputDevice;
	}

	public void setMidiOutputDevice(MidiDevice midiOutputDevice) {
		this.midiOutputDevice = midiOutputDevice;
	}
	
	public void trigger(int x) {
		try {
			System.out.println("playing note");
			SequoiaStep current = steps.get(x);
			SequoiaStep previous = current.getPrevious();
			midiOutputDevice.getReceiver().send(new ShortMessage(ShortMessage.NOTE_ON, current.getNote(), current.getVelocity()), -1);
			if(previous.isEnabled()){
				//midiOutputDevice.getReceiver().send(new ShortMessage(ShortMessage.NOTE_OFF, previous.getNote(), previous.getVelocity()), -1);
			}
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("no receiver");
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
	
}
