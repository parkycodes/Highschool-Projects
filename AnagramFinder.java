import java.util.*;
import java.io.*;
public class AnagramFinder {
	
	private StringFrequency[] array;
	
	public AnagramFinder(String fname) {
		
		Scanner fileIn = null;
		Scanner fileIn1 = null;
		
		try {
			fileIn = new Scanner(new File(fname));
			fileIn1 = new Scanner(new File(fname));
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		int counter = 0;
		while(fileIn.hasNextLine()) {
			counter++;
			fileIn.nextLine();
		}
		
		array = new StringFrequency[counter];
		
		for(int i =0; i < counter; i ++) {
			array[i] = new StringFrequency(fileIn1.nextLine());
		}
		fileIn.close();
		
	}
	
	public void printAnagrams(StringFrequency word) {
		for(int i =0; i < array.length; i ++) {
			if(word.hasSameFreq(array[i])) {
				System.out.println(array[i]);
			}
		}
	}
	
}
