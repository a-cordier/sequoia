package com.acordier.sequoia.model;

import java.util.Random;

/**
 * StepSequence is implemented as a singleton in order
 * to be safelly shared between SequoiaMatrix and SequoiaCoreController
 * @author anton
 *
 */
public class SequoiaStepSequence {

	private SequoiaStep[] steps;
	private Random random;
	private static SequoiaStepSequence instance;
	
	/** default constructor of 16 steps */
	private SequoiaStepSequence() {
		steps = new SequoiaStep[16];
		feed();
	}
	
	public static SequoiaStepSequence getInstance(){
		if(instance==null){
			synchronized (SequoiaStepSequence.class) {
				instance = new SequoiaStepSequence();
			}
		}
		return instance;
	}
	
	/** Feed with empty beats */
	private void feed(){
		for (byte i = 0; i < steps.length; i++) {
			SequoiaStep step = new SequoiaStep();
			steps[i]=step;
			if(i!=0){
				steps[i-1].setPrevious(step);	
			}
		}
		steps[0].setPrevious(steps[steps.length-1]);
	}

	/**
	 * Feed with random beats
	 * @param octave: an integer between 0 and 9 
	 */
	public void randomize(int octave) {
		random = new Random();
		for (byte i = 0; i < steps.length; i++)  {
			steps[i] = new SequoiaStep(127 - random.nextInt(127 - 50),
					Math.random() > 0.5 ? true : false);
			/* Set step to trigger a random note in the given octave */
			steps[i].setNote(random.nextInt(12) + 12 * octave);
		}
	}
	
	public SequoiaStep get(int i){
		return steps[i];
	}
	

	
	
	
	

}
