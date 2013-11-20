import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;

public class textTwistCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	
	//private static int BP = 2000;
	private GLabel wordLabbel;
	private int time;
	private GLabel timer;
	private int pause = 100;
	private ArrayList<String> AllValidWords; 
	private ArrayList<String> WordDashes;; 
	private ArrayList<GLabel> hidenWords; 
	private GLabel scoreLabel;
	private GLabel winLabel;
	private boolean youWon;
	private GLabel enter;
	
	public void reset(String word, ArrayList<String> AllValidWords, int score) {
		
		this.AllValidWords = AllValidWords;
		youWon = false;
		time=60;
		
		WordDashes = new ArrayList<String>(); 
		hidenWords = new ArrayList<GLabel>(); 
		
		//makes the GLabel exactly senterd/ work width Brian.
		//applicatin width/ 2 - wordLabbel.get length
		
		wordLabbel = new GLabel(word, 120, 440);
		wordLabbel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		wordLabbel.setColor(Color.black);
		add(wordLabbel);
		
		//score_right_label.setLabel("" + score_right);

		
		timer = new GLabel(""+time, 145, 50);
		timer.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		timer.setColor(Color.black);
		add(timer);
		
		convertToDashes();
		
		for(int i = 0; i < WordDashes.size(); i ++){
			
			GLabel newLabel = new GLabel(WordDashes.get(i), 15 + 50*Math.floor(i/22), 75 + 15*(i%22));
			add(newLabel);
			hidenWords.add(newLabel);
			
		
		}
		
		
		
		scoreLabel = new GLabel("score: " + score, 15, 37); 
		add(scoreLabel);
	
		
		
		
	}//reset brace
	
	
	public void enterLabel(){
		
		enter = new GLabel("hit enter to continue", 200, 300);
		add(enter);
	}
	
	
	public boolean clear(){
		
		
		remove(wordLabbel);
		remove(timer);
		remove(scoreLabel);
		
		for(int i = 0; i < hidenWords.size(); i ++){
			
			remove(hidenWords.get(i));
		}

		if(time > 0){
			
			remove(winLabel);
			return true;
		}else{
			
			remove(enter);
			return false;
		}
	}
	
	
	public void updateWordList(String word){
			
		int spot = AllValidWords.indexOf(word);
		WordDashes.set(spot, word);
		hidenWords.get(spot).setLabel(word);
	}
	
	
	public void showAllWords(){
		
		for(int i = 0; i< hidenWords.size(); i++){
			
			GLabel label = hidenWords.get(i);
			String word = AllValidWords.get(i);
			label.setLabel(word);
			
		}
		
		
		
	}
	
	public void updateScoreLabel(int score){
		
	
		
		scoreLabel.setLabel("score: " + score);
		
	}//score brace
	
	public void win(){
		
		winLabel = new GLabel(" You win!!!", 200, 35);
		winLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		winLabel.setColor(Color.black);
		add(winLabel);
		
		youWon = true; 
		
		showAllWords();
	
	}
	
	private void convertToDashes(){
		
		for(int i = 0; i < AllValidWords.size(); i++){
			

			String convertToDashes = "";
			
		
			for(int l = 0; l < AllValidWords.get(i).length(); l++){
				
				convertToDashes = convertToDashes + "-";
				
				
			}
				
			WordDashes.add(convertToDashes);
			
		}
		
		
		
	}
	
	public void startTimer() throws InterruptedException{
		
		for(int i = 0; i <60; i ++){
			
			Thread.sleep(1000);
			time = time - 1;
			timer.setLabel("" + time);
			
		}//for brace
		
		showAllWords();
		
		enterLabel();

		
	}//startTimer brace
	
	public boolean isGameOver(){
		
		if(time == 0 || youWon == true){
			return true;
		}else{
			
			return false;
		}
		
	}

	
	
}//end brace