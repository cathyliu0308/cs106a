// a Melody class that uses an array to represent a song comprised of a series of notes.

import java.util.*;
import java.io.*;

import stanford.cs106.audio.*;

public class Melody implements MelodyInterface {
	Note[] notes;
	int numNotes;
	String title;
	String artist;
	double sumDuration;
	public Melody(Scanner input) {
		title = input.nextLine();
		artist = input.nextLine();
		numNotes = Integer.parseInt(input.nextLine());
		//System.out.println(title + "*" + artist + "*" + numNotes);
		notes = new Note[numNotes];
/*
		while (input.hasNextLine()) {
			String line = input.nextLine();
			System.out.println(line);
			Note note = new Note(line);
			notes[i] = note;
		}
*/		
		for (int i = 0; i < numNotes; i++) {
			String line = input.nextLine();
			//System.out.println(line);
			Note note = new Note(line);
			notes[i] = note;			
		}	
	}
	
	public void changeDuration(double ratio) {
		double duration;
		if (ratio != 1.0) {
			for (int i = 0; i < numNotes; i++) {
				duration = notes[i].getDuration();
				notes[i].setDuration(duration * ratio);
			}
		}

	}
	
	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}
	
	public double getTotalDuration() {
		int flag = 0;
		int i = 0;
		int j = 0;
		double tmp = 0.0;
		while (i < numNotes){			
			if (notes[i].isRepeat() == true && flag == 0) {
				flag = 1;
				j = i;
			} else if (notes[i].isRepeat() == true && flag == 1) {
				for (int k = j; k<=i; k++) {
					tmp +=notes[k].getDuration();
				}
				flag = 0;			
			}
			i++;
		}
		for (int index = 0; index < numNotes; index ++) {
			sumDuration += notes[index].getDuration();
		}
		sumDuration += tmp;
		return sumDuration;
	}
	
	public boolean octaveDown() {
		int flag = 1;
		int octave;
		for (int i = 0; i< numNotes; i++) {
			if ((notes[i].getPitch() != Pitch.R) && (notes[i].getOctave() == 1)) {
				flag = 0;
				break;
			}
		}
		if (flag == 0)
			return false;
		else {
			for (int i = 0; i< numNotes; i++) {
				if (notes[i].getPitch() != Pitch.R) {
					octave = notes[i].getOctave();
					notes[i].setOctave(octave - 1);
				}
			}
			return true;
		}
	}
	
	public boolean octaveUp() {
		int flag = 1;
		int octave;
		for (int i = 0; i< numNotes; i++) {
			if ((notes[i].getPitch() != Pitch.R) && (notes[i].getOctave() == 10)) {
				flag = 0;
				break;
			}
		}
		if (flag == 0)
			return false;
		else {
			for (int i = 0; i< numNotes; i++) {
				if (notes[i].getPitch() != Pitch.R) {
					octave = notes[i].getOctave();
					notes[i].setOctave(octave + 1);
				}
			}
			return true;
		}
	}
	
	public void play() {
		int flag = 0;
		int i = 0;
		int j = 0;
		while (i < numNotes){			
			if (notes[i].isRepeat() == true && flag == 0) {
				flag = 1;
				j = i;
				notes[i].play();
			} else if (notes[i].isRepeat() == true && flag == 1) {
				notes[i].play();
				for (int k = j; k<=i; k++) {
					notes[k].play();
				}
				flag = 0;			
			} else {
				notes[i].play();
			}
			i++;
		}
		
	}
	
	public void reverse() {
		Note tmp;
		for (int i = 0; i < numNotes/2; i++) {
			tmp = notes[i];
			notes[i] = notes[numNotes-1-i];
			notes[numNotes-1-i] = tmp;
		}
		
	}
	

}
