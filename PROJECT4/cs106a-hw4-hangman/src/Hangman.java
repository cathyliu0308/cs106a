/*
 * File: Hangman.java
 * Name:
 * Section Leader:
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4. 
 
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Hangman extends ConsoleProgram { 
	HangmanLexicon lexicon = new HangmanLexicon();
	private HangmanCanvas canvas;
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		
	}
	
    public void run() {
    	canvas.reset();
    	int index = lexicon.getIndex();
    	String word = lexicon.getWord(index);
 //   	println(index);
    	//println("word: " + word);
    	int guess = 8;  	
    	int dash = word.length();
    	println("Welcome to Hangman!");
    	String guessWord = new String(new char[dash]).replace('\0', '-');
    	while (guess > 0 && dash > 0) {  		
    		println("The word now looks like this: " + guessWord);
    		canvas.displayWord(guessWord);
    		if (guess == 1) {
    			println("You have only one guess left.");
    		} else {
    			println("You have " + guess + " guesses left.");
    		}
    		String letter = readLine("Your guess: ");
    		letter = letter.toUpperCase();
    		char[] ch = letter.toCharArray();
    		while (ch.length > 1 || (ch.length == 1 && (ch[0] > 'Z' || ch[0] < 'A'))){
    			throw new ErrorException("The guess is illegal! Please give a new guess.");
    		}
    		int flag = 0;
    		for (int i = 0; i < word.length(); i++) {
    			if (letter.equals(word.substring(i, i + 1)) && guessWord.charAt(i) == '-') {
    				dash --;
    				flag = 1;
    				guessWord = guessWord.substring(0, i) + letter + guessWord.substring(i + 1);
    				println("That guess is correct.");
    				break;
    			} 
    			//else if (letter.equals(word.substring(i, i + 1)) && letter.equals(repeatedDash.substring(i, i + 1))) {
    			//	flag = 1;
    			//	break;
    			//}
    			
    		}
    		if (flag == 0) {
    			println("There are no " + letter + "\'s in the word");
    			guess --;
    			canvas.noteIncorrectGuess(ch[0], guess);
    		} else {
    			println("The word now looks like this: " + guessWord);
        		canvas.displayWord(guessWord);
    		}
    		
    	}
    	if (guess == 0) {
    		println("You\'re completely hung.");
    		println("The word was: " + word);
    		println("You lose.");
    	} else {
    		println("You guessed the word: " + word);
    		println("You win");
    	}
    	
		/* You fill this in */
	}

}
