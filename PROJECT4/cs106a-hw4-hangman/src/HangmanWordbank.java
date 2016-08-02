/*
 * CS 106A Hangman - HangmanWordbank
 * 
 *  DO NOT MODIFY THIS FILE
 *  DO NOT MODIFY THIS FILE
 *  DO NOT MODIFY THIS FILE
 *  
 *  Author: Rohit Talreja
 *  Modified: 7/11/16
 *  
 *  This instructor-provided class implements the HangmanWordbank.
 *  You will use the public methods implemented here to complete the
 *  HangmanLexicon class.
 *  
 *  Your program should work properly with an UNMODIFIED version of this
 *  file.
 *  
 *  If you want to modify this file as part of an extension, that
 *  is your choice and you do so at your own risk. If you are modifying
 *  this file for an extension we recommend creating a duplicate of the 
 *  entire assignment first, as we recommend for any other assignment.
 *  
 *  
 *  HOW TO USE THIS CLASS:
 *  
 *  For all practical purposes you can think of the HangmanWordbank class
 *  as storing an indexed list of words. For example, after adding 7 words
 *  to the word bank, our internal list would look like:
 *  0	Apple
 *  1	Banana
 *  2	Cherry
 *  3	Date
 *  4	Eggplant
 *  5	Fig
 *  6	Grape
 *  where the indices have been added for clarity.
 *  
 *  In the above example, getWordbankSize would return 7 (the number of words
 *  in the list), getWordFromWordbank(4) would return "Eggplant" and
 *  addWordToWordbank("Huckleberry") would create a new entry at index 7
 *  for Huckleberry.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanWordbank {

	private ArrayList<String> wordList;
	
	/*
	 * Initializes the HangmanWordbank class by creating a new ArrayList
	 * instance to store words from the HangmanLexicon class
	 */

	
	public HangmanWordbank() {
		wordList = new ArrayList<String>();
	}
	
	
	/*
	 * Returns the number of words in the word bank.
	 */
	public int getWordbankSize() {
		return wordList.size();
	}
	
	
	/*
	 * Adds a new word to the word bank. Does not perform any checks for
	 * duplicates, however assuming you only read the lexicon file once
	 * to make the lexicon this should not be an issue.
	 */
	public void addWordToWordbank(String word) {
		wordList.add(word);
	}
	
	
	/*
	 * Returns the word at the given index. See top of file comments for
	 * a specific example.
	 */
	public String getWordFromWordbank(int index) {
		if (index < wordList.size()) {
			return wordList.get(index);
		} else {
			return null;
		}
	}
}
