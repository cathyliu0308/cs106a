/*
 * File: HangmanLexicon.java
 * Name:
 * Section Leader:
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.

 */


import acm.util.*;

import java.io.*;


public class HangmanLexicon {
	private HangmanWordbank wordbank;

	// This is the HangmanLexicon constructor
	public HangmanLexicon() {
	// your initialization code goes here
		wordbank = new HangmanWordbank();
		try {
    		BufferedReader br = new BufferedReader(new FileReader("res/HangmanLexicon.txt"));
    		while (true) {
				// Read the next line
				String line = br.readLine();
				// Break out of the loop when we reach the end of the file
				if (line == null) break;
				
				// Line is not null, so we can do something with it here
				//System.out.println(line);
				wordbank.addWordToWordbank(line);
			}
		
		} 
    	catch (IOException e) {
    		// Handle errors here	
    		System.out.println("Error" + e);
    	}
	}

	public int getIndex() {
		RandomGenerator rg = RandomGenerator.getInstance();
		int wordcount = getWordCount();
		int res = rg.nextInt(0,wordcount);
		return res;
	}	
	
/** 
 * Returns the number of words in the lexicon.
 * TODO: update this in part 3 of the assignment to use the wordbank
 * and it's associated methods.
 */
	public int getWordCount() {
		//int res = rg.nextInt(0,10);
		//return res;
		return wordbank.getWordbankSize();
	}

/** 
 * Returns the word at the specified index. 
 * TODO: update this in part 3 of the assignment to use the 
 * wordbank and it's associated methods
 */
	public String getWord(int index) {
/*		switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
*/
		return wordbank.getWordFromWordbank(index);
		
	}
}
