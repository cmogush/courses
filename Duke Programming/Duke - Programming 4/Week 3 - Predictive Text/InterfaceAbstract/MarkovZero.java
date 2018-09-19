//2017 Christopher Mogush
//MarkovZero

import java.util.Random;

public class MarkovZero implements IMarkovModel {
    private String myText;
	private Random myRandom;
	
	public MarkovZero() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){ //Using this method will allow you to generate the same random text each time
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){ //The String s is used to initialize the training text
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){ //generates and returns random text that is numChars long
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length()); //
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}
}
