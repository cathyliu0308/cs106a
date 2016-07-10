/*
 * File: ExamScores.java
 * Name:
 * Section Leader:
 * --------------------
 * TODO: add a program comment
 */

import acm.program.*;

public class ExamScores extends ConsoleProgram { 
	private static final int SENTINEL = -1;
	public void run() {
		println ("CS 106A Exam Master");
		int failing = 0 ;
		int total = 0;
		int high = 0;
		int low = 100;
		double sum = 0;
		double average;
		while (true){
			int score = readInt ("Next exam score (or " + SENTINEL + " to quit)?");
			if (score != SENTINEL){
				total ++;
				sum += score;
				if (score < 60  && score != SENTINEL) {
					failing ++;
				}
				high = Math.max (score, high);
				low = Math.min(score, low);
			}
			else{
				break;
			}
		}
		
		if (total == 0){
			println ("No scores were entered.");
		}
		else {
			average = sum / total;
			println ("Highest score = " + high);
			println ("Lowest score = " + low);
			println ("Average = " + average);
			println (failing + " student(s) failed the exam");
		}
	}
}