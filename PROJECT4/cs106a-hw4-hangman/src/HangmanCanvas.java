/*
 * File: HangmanCanvas.java
 * Name:
 * Section Leader:
 * ------------------------
 * This file keeps track of the Hangman display.
 
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {
	GLabel secretWord;
	String guessLetter = "";
	GLabel guessWord;
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		drawScafold();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		
		if (secretWord == null) {
			secretWord = new GLabel (word, getWidth()/2 - BEAM_LENGTH - 20, getHeight()/2 + SCAFFOLD_HEIGHT/2 + SCAFFOLD_OFFSET/2);
			secretWord.setFont("Arial-30");
			add(secretWord);
		}
		else
			secretWord.setLabel(word);
		
	}
	
	private void drawScafold() {
		//System.out.println(getWidth());
		//System.out.println(getHeight());
		GLine scafoldHeight = new GLine(getWidth()/2 - BEAM_LENGTH, getHeight()/2 + SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET, getWidth()/2 - BEAM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET);
		GLine scafoldBeam = new GLine(getWidth()/2 - BEAM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET);
		GLine scafoldRope = new GLine(getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH - SCAFFOLD_OFFSET);
		add(scafoldHeight);
		add(scafoldBeam);
		add(scafoldRope);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int i) {
		//System.out.println(guess);
		GOval head = new GOval(getWidth()/2 - HEAD_RADIUS, getHeight()/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH - SCAFFOLD_OFFSET, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		GLine body = new GLine(getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH - SCAFFOLD_OFFSET + 2 * HEAD_RADIUS + BODY_LENGTH);
		GLine leftUpperArm = new GLine(getWidth()/2 - UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine leftLowerArm = new GLine(getWidth()/2 - UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH, getWidth()/2 - UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine rightUpperArm = new GLine(getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine rightLowerArm = new GLine(getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH, getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine leftHip = new GLine(getWidth()/2 - HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		GLine leftLeg = new GLine(getWidth()/2 - HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH, getWidth()/2 - HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		GLine rightHip = new GLine(getWidth()/2 + HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		GLine rightLeg = new GLine(getWidth()/2 + HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH, getWidth()/2 + HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		GLine leftFoot = new GLine(getWidth()/2 - HIP_WIDTH - FOOT_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, getWidth()/2 - HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		GLine rightFoot = new GLine(getWidth()/2 + HIP_WIDTH + FOOT_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, getWidth()/2 + HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT/2 - SCAFFOLD_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		
		guessLetter += letter;
		if (guessWord == null) {
			guessWord = new GLabel (guessLetter, getWidth()/2 - BEAM_LENGTH - 20, getHeight()/2 + SCAFFOLD_HEIGHT/2 + 1.5 *SCAFFOLD_OFFSET);
			guessWord.setFont("Arial-15");
			add(guessWord);
		}else
			guessWord.setLabel(guessLetter);
		switch (i) {
		case 0: add(rightFoot);
		case 1: add(leftFoot);
		case 2: add(rightHip); add(rightLeg);
		case 3: add(leftHip); add(leftLeg);
		case 4: add(rightUpperArm); add(rightLowerArm);
		case 5: add(leftUpperArm); add(leftLowerArm);
		case 6: add(body);
		case 7: add(head);
		//default: throw new ErrorException("getWord: Illegal index");
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int SCAFFOLD_OFFSET = 30;

}
