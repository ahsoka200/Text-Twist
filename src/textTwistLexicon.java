import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class textTwistLexicon {
	
	private ArrayList<String> wordList;
	private ArrayList<String> sixLetterWordList;
	
	public textTwistLexicon(){

			
			BufferedReader rd = null;
			try {
				rd = new BufferedReader(new FileReader("textTwist.txt"));
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			wordList = new ArrayList<String>();
			
			sixLetterWordList= new ArrayList<String>();
			
			String line = "";
			while(true){
				 
				try {
					line = rd.readLine();
			
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				wordList.add(line);
				
				if (line == null) break; 
			
				
				if(line.length() == 6){
					
					sixLetterWordList.add(line);
					
				}
			
			
			}
			
			
	}
	
	public int getSixLetterWordCount() {
		return sixLetterWordList.size();
	}
		
	public String getSixLetterWord(int index){
		

		return sixLetterWordList.get (index);
	}
	
	

	public ArrayList<String> getAllValidWords(String sixLetterWord){
		
		ArrayList<String> AllValidWords =  new ArrayList<String>();
			
		
		for(int i = 0; i < wordList.size() -1; i ++){
			
			String currentWord = wordList.get(i);
			String remainingSixLetterWord = sixLetterWord;
			
			for(int w = 0; w <currentWord.length(); w ++){
				
				char currentLetter = currentWord.charAt(w);
				if(remainingSixLetterWord.indexOf(currentLetter) != -1){
					remainingSixLetterWord = removeLetter(currentLetter, remainingSixLetterWord);
				}else{
					break;
				}
				
				if(w == currentWord.length() - 1){
					System.out.println(currentWord);
					AllValidWords.add(currentWord);
				}
				
			}//for W brace
			
		}//for I brace
		
	
		return AllValidWords;
	}
	
	
	private String removeLetter(char letter, String remainingSixLetterWord){
		
		int index = remainingSixLetterWord.indexOf(letter);
		
		if(index == -1){
			return null;
		}else{
			//remove letter from remainingSixLetterWord
			if(index == 0){
				remainingSixLetterWord = remainingSixLetterWord.substring(index + 1);
	    	}else if(index == remainingSixLetterWord.length()-1){
	    		
	    		remainingSixLetterWord = remainingSixLetterWord.substring(0, index);
	    		
	    	}else{
	    		remainingSixLetterWord = remainingSixLetterWord.substring(0, index) + remainingSixLetterWord.substring(index + 1);
	    	}
			return remainingSixLetterWord;
		}
		
		
		
	}
		
	
}
	
