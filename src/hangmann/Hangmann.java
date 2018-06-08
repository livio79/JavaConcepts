package hangmann;

//NUOVO
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
 
public class Hangmann {//set metodi sono utili? - variabili di classe se possibile
	String sentenceToGuess ;
	String senteceToShow;
	String message;
	boolean [] b ;
	ArrayList<Character> entered = new ArrayList<>();
	char letter =' ';
	
	
	public void setLetter(char l) {
		this.letter = l;
	}
	
	public char getLetter() {
		return letter;
	}
	
	
	public String getSenteceToShow() {
		return this.senteceToShow;
	}
	public void setSentenceToGuess(String  toGuess) {
		this.sentenceToGuess = toGuess;
	}
	
	public String getSentenceToGuess() {
		return sentenceToGuess;
	}
	
	public String getMessagge() {
		return message;
	}
	
	public void setMessage(String m) {
		message = m;
	}
	
	/** get random a line (the sentence to guess) from a file txt. */
	public  String getText( ) throws FileNotFoundException {
		File f = new File("sprichworter2.txt");
		Scanner s = new Scanner(f,"UTF-8");
		Random r = new Random();
		int random = 	r.nextInt(625);
		int counter=0;
		 String line = "";
		 String line2 = "";
		while(s.hasNextLine()) {					//prova : if cout=r line2=s.next.toUpper else s.next ohne line=s.next 
			line = s.nextLine(); 					//if count= random boolean true=>ciclo finisce
			if(counter==random) {
				line2 = line.toUpperCase();
			}
				counter++;
		}
		
		return line2;
	}

	
	/** count the total number of letters in the sentence to guess */
	public int countLetters(String sentenceToGuess) {
		int counter=0;
		for(int i=0; i<sentenceToGuess.length(); i++) {
			if(Character.isLetter(sentenceToGuess.charAt(i))  )
				++counter;
		}
		return counter;
	}
	
	
	
	
	
	/**return the input char only if it was still not entered*/
	public char alreadyEntered(ArrayList<Character> entered) {   
		Scanner s = new Scanner(System.in);
		boolean isThere = true;
		
		while(isThere) {
			letter = s.next().toUpperCase().charAt(0);
			if(entered.contains(letter)) {
				isThere = true;
			}
			else {
				entered.add(letter);
				isThere= false;
			}
		}
		Collections.sort(entered);
	return letter;
	}
	
	
	

	
	
	
	
	
		
	/**count the number of true in a boolean array. True values correspond to the guessed letters*/
	public int countTrue(boolean [] guessedLetterPosition) {
		int countTrue=0;
		for (int i = 0; i < guessedLetterPosition.length; i++) {
			if(guessedLetterPosition[i])
				++countTrue;
		}
		return countTrue;
	}
	
	/**build the string to show with guessed letters oder "_" */
	public String showThisSentence(String sentenceToGusee,   boolean[] guessedLetterPosition) {
		 String show="";
		for (int i = 0; i < sentenceToGusee.length(); i++) {
			if(guessedLetterPosition[i]) {
				show += sentenceToGusee.charAt(i) + " ";
			}
			else if(sentenceToGusee.charAt(i)==' ') {
				show += "   ";}
			else if(!Character.isLetter(sentenceToGusee.charAt(i))){
				show += sentenceToGusee.charAt(i);
			}
			else
				show +="_ ";
		}
		return show;
	}
	
	
	
	
	
	/**start the game*/
	public void play() throws FileNotFoundException {
		
		sentenceToGuess = getText();
		b = new boolean[sentenceToGuess.length()];																 
				System.out.println("Prova ad indovinare la frase...");
		senteceToShow = showThisSentence(sentenceToGuess, b);
			System.out.println(senteceToShow);
		
		int numberOfLetters = countLetters(sentenceToGuess);
		int attempt= 6;
		int errors=0;
		boolean win = false;
		 																 
		while(errors<attempt && !win) {
			letter = alreadyEntered(entered);
			boolean addTurn = updateTrueAndTurn(sentenceToGuess,b, letter); 
			int numberOfTrue = countTrue(b);
		
			if(!addTurn) {
				++errors;
				}
			 
			if(numberOfTrue==numberOfLetters) {
				win=true;
				System.out.println("YOU WIN!!");
			}
			
			senteceToShow = showThisSentence(sentenceToGuess , b);
			 System.out.println("Tentativi rimasti " + (attempt - errors) +"   "+ senteceToShow + "       "+ entered.toString());
			 
 		}
			System.out.println("la frase era: " + sentenceToGuess);
		
	}
	
	/**update the boolean array. Furthermore if counter>0 the entered letter was right*/
	public boolean  updateTrueAndTurn(String senteceToGuess,boolean[] guessedLetterPosition, char letter) {
		int counter = 0;
		for(int i=0; i<senteceToGuess.length(); i++) {
			if(letter == senteceToGuess.charAt(i)) {
				guessedLetterPosition[i]= true;
				++counter;
				}
		}
		return counter>0;
	}
	
	
		 
public static void main(String[] args) throws FileNotFoundException {
	  Hangmann mess = new Hangmann();
	  mess.play();
  }
	

  }