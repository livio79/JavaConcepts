package primo;

import java.util.*; 

public class MasterMind {
	
	char [] letters = {'Q',	'W',  'E',  'R'};
	
	public boolean isLengthValid(String lettersToGuess) {
		boolean valid = false;
				if(lettersToGuess.length()==4) 
					valid = true;
				else 
					System.out.println("*** 4 lettere");
		return valid;
	}
	
	
	public boolean isLetterValid(String lettersToGuess) {
		int rightLetter = 0;
		boolean letterValid = true;
		
			for (int i = 0; i < lettersToGuess.length(); i++) {
				if( lettersToGuess.substring(i,i+1).equals("Q")  ||
					lettersToGuess.substring(i,i+1).equals("W")  ||
					lettersToGuess.substring(i,i+1).equals("E")  ||
					lettersToGuess.substring(i,i+1).equals("R")  ) 
						rightLetter += 0;	
				else 
						rightLetter += 1;
			}
			
			if(rightLetter!= 0) {
				letterValid =false;
				System.out.println("*** nur Q W E oder R");
			}
		return letterValid;
	}
	
	
	public  void play(int attempt) {
		char [] four = createFour();
		
		System.out.println("Gebe 4 Buchstäbe ein : Q, W, E oder R");
		System.out.println("Du hast " + attempt + " Versuche zu Verfugung");
		
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.printf("%26s || Richtig + Richtige Stelle ||\n","");
		System.out.println("-------------------------------------------------------------");
		
		for (int i = 1; i <= attempt ; i++) {
			System.out.println("\nVersuch " + i);
			char [] guess = createGuess();

			printLetter(guess);
		
			 int wellPlaced = isThere(guess, four);
			 int totalMatch = howMany(four, guess);
			 
			 printAnswer(totalMatch, wellPlaced);
			 
			 	if(wellPlaced ==4) {
			 		System.out.println();
			 		System.out.println("\nGewonnen nach " + i +  " Versuche!.");
			 		printLetter(four);
			 		i = attempt +1;
			 	}
			 
			 if(i == attempt && wellPlaced != 4) {
				 System.out.println();
				 System.out.println("\nLeider zu viele Versuche.");
				 System.out.println("Die richtige Kombination war: ");
				 printLetter(four);
				 }
		}
	}
	
	
	public char[] createFour() {						
		char [] four = new char[4];
		Random r = new Random();
		
		for (int i = 0; i < letters.length; i++) {
			int randomNumber = r.nextInt(4);
			four[i]= letters[randomNumber];
		}
	return four;
}
	 
	public char[] createGuess() {
		Scanner s = new Scanner(System.in);
		char[] guess = new char[4];
		
		boolean lengthInput = false;
		boolean letterValid = false;
		String kombinationToGuess =  "";
		
		   while(!lengthInput || !letterValid) {
				kombinationToGuess = s.next().toUpperCase();
				
				lengthInput = isLengthValid(kombinationToGuess);    
				letterValid = isLetterValid(kombinationToGuess);
		   }
			 
			for (int i = 0; i < 4; i++) {
				guess[i]=  kombinationToGuess.charAt(i);
			}
		return guess;
	}
	
	
	public int howMany(char[]four, char[]guess) {
		int totalMatch = 0;
		int [] counterGuess = new int [4];
		int [] counterFour = new int [4];

				for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					
					if(letters[i] == four[j])
						++counterFour[i];
					if(letters[i] == guess[j])
						++counterGuess[i];
				}
			
			if(counterFour[i] > 0) {
				int difference = counterFour[i] - counterGuess[i];
				if(difference >= 0)
					totalMatch += counterGuess[i];
				if(difference < 0)
					totalMatch += counterFour[i];
			}
		}	
		return totalMatch;
	}
	
	
	
	public  int isThere (char [] guess, char[]four) { 
		int wellPlaced = 0;
		
		for (int i = 0; i < four.length; i++) {
			if(guess[i] == four[i])
				++wellPlaced;
		}
		return wellPlaced;
	}
	
	
	public  void printAnswer(int totalMatch, int wellPlaced) {
		totalMatch = totalMatch - wellPlaced;
			System.out.printf("%10d   %10d  ", totalMatch , wellPlaced);
	}
	
	public void printLetter(char[] guess) {
		System.out.printf("%15s", "");
		for (int i = 0; i < guess.length; i++) {
			System.out.print(guess[i] + " ");
		}
	}
	
	public static void main(String[] args) {
			
			int versuch = 10;
			
			new MasterMind().play(versuch);
		}
	}
