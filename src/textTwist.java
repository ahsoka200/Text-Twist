
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import acm.graphics.GLabel;
import acm.program.ConsoleProgram;
import acm.util.*;
import acm.util.*;

import java.awt.*;

public class textTwist extends ConsoleProgram {
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private String sixLetterWord;
	public textTwistCanvas canvas; 
	private ArrayList<String> allPossibleWords;
	private int score=0;
	public void run(){
		
		
		textTwistLexicon lex = new textTwistLexicon();

		
		while(true){
		
			
			sixLetterWord = lex.getSixLetterWord(rgen.nextInt(0, lex.getSixLetterWordCount()-1));
			System.out.println(sixLetterWord);
			
			allPossibleWords = lex.getAllValidWords(sixLetterWord);
				
			scrambleWord();
			
			println(" Welcome to text twist");
			println(" Here are your scrambled letters: " + sixLetterWord);
			
			
			
			canvas.reset(sixLetterWord, allPossibleWords, score);
			
			//start timer in new thread
			Thread thread = new Thread(){
				public void run(){
					
					try {
						canvas.startTimer();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			};
		
			thread.start();
			
			
			String input = null;
			
			while (canvas.isGameOver() == false){
				
				input= readLine(" Your guess: ");
				
				if(canvas.isGameOver() == false){
					input = input.toLowerCase();
			    	
			    	if(input.length() >=7){
			    		
			    		println(" You can only type in six letters!!!");
			    	
				
			    	}else if(validateLetters(input) == false){
			    		
			    		println(" You can only use the letters given to you!!!");	
			    	
			    	
			    	}else if(input.length() <=2){
			    		
			    		println(" The word you make must be three leters or more");
			    	
			    	
			    	}else{ 
			    		inputISWord(input);
			    	}
				}
				
		    	
		    	
	    	}//while brace
			thread.interrupt();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//remove old game
			
			boolean win = canvas.clear();
			
			if(win == false){
				
				score = 0;
			}
		
		
		}//while true for all games
		
	}//run brace
	
	
	public void inputISWord(String input){
		
		if(allPossibleWords.contains(input)){
    		
			println(" YES!!!");
			canvas.updateWordList(input);
			
			score = score + 1;
			
			canvas.updateScoreLabel(score);
			
			
			
			if(input.length() == 6){
	    		
	    		score = score + 4;
	    		
	    		canvas.updateScoreLabel(score);
	    		
	    		println(" YOU WIN!!!");
	    		
	    		canvas.win();
	    		
	    	}
			
		}else{
				
				println(" NO!!!");
	
		}
		
	}
	
	
	public boolean validateLetters(String input){
		
		for(int i=0; i<input.length(); i ++){
			
			int position = sixLetterWord.indexOf(input.charAt(i));
			
			if(position == -1){
				
				return(false);
			}
			
		}
		
		
		return (true);
		
	}
	
	
	
	
	public void scrambleWord(){
		
		String curentWord = sixLetterWord;
		
		
		String ScrambleLetters = "";
		
		
		
		for(int i= 0; i<6; i ++){
			
			int letterP = rgen.nextInt(0, curentWord.length() - 1);
			
			char letter = curentWord.charAt(letterP);
			//System.out.println("Our letter: " + letter);
			
			if(letterP == 0){
	    		curentWord = curentWord.substring(letterP + 1);
	    	}else if(letterP == curentWord.length()-1){
	    		
	    		curentWord = curentWord.substring(0, letterP);
	    		
	    	}else{
	    		curentWord = curentWord.substring(0, letterP) + curentWord.substring(letterP + 1);
	    	}
			
			
			ScrambleLetters = ScrambleLetters + letter;
			
		}//for loop brace
		
		
		System.out.println("The scrambled word: " + ScrambleLetters);
		
		sixLetterWord = ScrambleLetters;

		
	}//scrambleWord brace
	
	
	 public void init(){

	    	canvas = new textTwistCanvas();
	    	add(canvas);
	 }
	
}//end brace




/*USED TO COPY A WORD LIST OF 3 TO 6 LETTER WORDS

BufferedReader rd = null;

try {
	rd = new BufferedReader(new FileReader("ScrabbleWords.txt"));
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


BufferedWriter bufferedWriter = null;

//Construct the BufferedWriter object
try {
	bufferedWriter = new BufferedWriter(new FileWriter("textTwist.txt"));
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}


String word ="";
while(word != null){
	 
	
	try {
		word = rd.readLine();
		if(word != null){
			if(word.length() >=3 && word.length() <= 6){
				
				System.out.println(word);
				bufferedWriter.write(word);
	            bufferedWriter.newLine();
			}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}


try {
	bufferedWriter.flush();
    bufferedWriter.close();

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


*/