package com.acordier.sequoia.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StepSequence implements Iterable<Step>{

	private List<Step> steps;
	private Random random;

	public StepSequence(int size) {
		steps = new ArrayList<Step>(size);
		feed();
	}

	/** default constructor of 16 steps */
	public StepSequence() {
		steps = new ArrayList<Step>(16);
		feed();
	}
	
	/** Feed with empty beats */
	private void feed(){
		for (byte i = 0; i < steps.size(); i++) {
			steps.add(new Step());
		}
	}

	/**
	 * Reset all beats
	 */
	public void reset() {
		for (byte i = 0; i < steps.size(); i++)  {
			steps.get(i).reset();
		}
	}

	/**
	 * Feed with random beats
	 * @param octave: an integer between 0 and 9 
	 */
	public void randomize(int octave) {
		random = new Random();
		for (byte i = 0; i < steps.size(); i++)  {
			steps.set(i, new Step(127 - random.nextInt(127 - 50),
					Math.random() > 0.5 ? true : false));
			/* Set step to trigger a random note in the given octave */
			steps.get(i).setNote(random.nextInt(12) + 12 * octave);
		}
	}
	
	/**
	 * Feed with four regular beats
	 */
	public void fourBeat(int note) {
		for (byte i = 0; i < steps.size(); i++)  {
			steps.set(i, new Step(105,
					i%4==0 ? true : false));
			steps.get(i).setNote(note);
			System.out.println(steps.get(i));
		}
	}
	
	/**
	 * Add one beat
	 */
	public void grow(){ 
		this.steps.add(new Step());
	}
	
	/**take care of the end of track event
	 * Remove one beat
	 */
	public void shrink(){
		this.steps.remove(this.steps.size()-1);
	}

	@Override
	public Iterator<Step> iterator() {
		return steps.iterator();
	}
	
	/** returns the current number of steps of the step sequence */
	public int size() {
		return steps.size();
	}
	
	
	
	

}
