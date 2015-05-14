package com.acordier.sequoia.model;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;


public class SequoiaStep {

	private int velocity;
	private int note;
	private boolean enabled;
	private SequoiaStep previous;
	private ShortMessage noteOnMessage;
	private ShortMessage noteOffMessage;

	public SequoiaStep(int velocity, boolean set) {
		this.enabled = set;
		this.velocity = velocity;
	}

	public SequoiaStep() {
		this.enabled = false;
		this.velocity = 100;
	}

	public int getVelocity() {
		return velocity;
	}

	/**
	 * Set velocity value for this beat
	 * 
	 * @param velocity
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	/**
	 * Return beat state (disable|enabled)
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Set beat to enabled state, which make it candidate for playing
	 */
	public void enable() {
		this.enabled = true;
	}

	/**
	 * Set beat to disabled
	 */
	public void disable() {
		this.enabled = false;
	}

	/**
	 * Set velocity to a default value of 100 and disable beat
	 */
	public void reset() {
		this.disable();
		this.velocity = 100;
	}

	/**
	 * Toggle beat state (set|unset)
	 */
	public void toggle() {
		this.enabled = !this.enabled;
	}
	
	@Override
	public String toString() {
		return String.format("{enabled:%s,velocity:%s, note:%s}",enabled, velocity, note);
	}

	public int getNote() {
		return this.note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public SequoiaStep getPrevious() {
		return previous;
	}

	public void setPrevious(SequoiaStep previous) {
		this.previous = previous;
	}
	
}
