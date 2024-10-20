/*
 * Parker Zhang
 * Turner pd 3
 * Counts total number of letters in a given word
 */
public class StringFrequency {

	private String word;
	private int[] alphaCounter;
	
	public StringFrequency(String w) {
		
		word = w;
		String tempWord = word.toLowerCase();
		alphaCounter = new int[26];
		
		for(int i =0; i < word.length(); i++) {
			
			alphaCounter[(int)tempWord.charAt(i)-97]++;
		}
	}
	
	
	public StringFrequency(StringFrequency other) {
		
		alphaCounter = new int [other.alphaCounter.length];
		
		for(int i =0; i < alphaCounter.length; i ++) 
			alphaCounter[i]=other.alphaCounter[i];
		
	}
	
	public String getWord() {
		return word;
	}
	
	//checks if the same amount of each letter
	public boolean hasSameFreq(StringFrequency other) {
		
		for(int i =0; i < alphaCounter.length; i ++) {
			
			if(alphaCounter[i]!=other.alphaCounter[i]) 
				return false;
		}
		return true;
	}
	
	
}
