package poker2;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.html.MinimalHTMLWriter;


public class Card {
 
private String [] suits = {"Spade", "Club",  "Diamond", "Heart"};
private String [] pips = {	"2", "3",	"4",	"5", 	"6",	"7",   "8",   "9",  "10",	"J",   "Q",	"K", "A" };  
private String [] score = {"Nothing", "One Pair", "Two Pair", "Tris", "Straight", "Flush", "Full", "Poker", "Royal Flush"};
private int suit;
private int pip;
private int NUMBER_OF_CARDS = 5;
private int numberOfPlayers = 6;		// min 2 max 5   -- se tutti i giocatori cambiano 5 carte, ce ne sono a sufficienza?
private int lowerPipValue = pips.length - (numberOfPlayers + 7); 
private  ArrayList<Card> deck = new ArrayList<>();		


public void play() {
	
}

	

//metodo temporaneo per trasferire il numero di giocatori nel metodo crea giocatori
public int getNumGiocatori() {
	return numberOfPlayers;
}

public ArrayList<Card> createDeck (boolean shuffle ){
	for(int i=0; i<suits.length; i++) {
		for(int j=lowerPipValue; j<pips.length; j++) {					
			Card card = new Card();
			card.suit = i;
			card.pip  = j;
			deck.add(card); 
		}
	}
	if(shuffle)
		Collections.shuffle(deck);
	return deck;
}

public ArrayList<Card> giveCards( ArrayList<Card> deck) {   //UN Giocatore  riceve 5 carte dal mazzo
	ArrayList<Card> cardsOnHand = new ArrayList<>();

			for(int i = 0; i<NUMBER_OF_CARDS; i++) {
				cardsOnHand.add(deck.get(0));
				deck.remove(0);
			}
	return cardsOnHand;	
}
												

public ArrayList<Card> sortDeck(ArrayList<Card> cards) {
	ArrayList<Card> sorted = new ArrayList<>();
	int size = cards.size();
	int pip [] = new int [size];
	int suit[] = new int [size];
		
		for(int i=0; i<size; i++) {
			pip[i]= cards.get(i).getPip();
			suit[i]= cards.get(i).getSuit();
		}
		
	
		for (int i = 0; i < size-1; i++)  {     
		       for (int j = 0; j < size-i-1; j++) {
			           if (pip[j] > pip[j+1]) {
			        	   	int tmp= pip[j];
			   				pip[j]= pip[j+1];
			   				pip[j+1]= tmp;
			   				
			   				int tmp2= suit[j];
			   				suit[j]= suit[j+1];
			   				suit[j+1]= tmp2;
			           }
		       }
		   }
		Card [] sortedCards = new Card[size];
		for (int i = 0; i < suit.length; i++) {
			sortedCards[i] = new Card();
			sortedCards[i].setPip(pip[i]);
			sortedCards[i].setSuit(suit[i]);
			sorted.add(sortedCards[i]);
		}
		
		return sorted;
}
	

public ArrayList<Card> changeCards( ArrayList<Card> cardToEvaluate) {   
	int cardsTochange = NUMBER_OF_CARDS - cardToEvaluate.size();
		//if(cardsTochange>3)cardsTochange = 3; dalle 5 per non complicare
		
			for(int i = 0; i<cardsTochange; i++) {
				cardToEvaluate.add(deck.get(0));
				deck.remove(0);
			}
	return cardToEvaluate;	
}


public ArrayList<Card> valuableCards(ArrayList<Card> sortedDeck ) {
	
	ArrayList<Card> cardToEvaluate = new ArrayList<>();

		for (int i = 0; i < sortedDeck.size()-1; i++) {
			int pip1 = sortedDeck.get(i).getPip();
			int pip2 = sortedDeck.get(i+1).getPip();
					if(pip1 ==  pip2) {
						if(!cardToEvaluate.contains(sortedDeck.get(i)))
							cardToEvaluate.add(sortedDeck.get(i));
					
						cardToEvaluate.add(sortedDeck.get(i+1));
					}		
		}
				 
		if(cardToEvaluate.size()==0) {// potrebbe essere una scala o un colore
				boolean scala = isStraight(sortedDeck);
				boolean flush =  isFlush(sortedDeck);
				//System.out.println("scala e flush " + scala + " " + flush);
							if(scala || flush) {					 
								for(Card t : sortedDeck) {
									cardToEvaluate.add(t);
								} 
							}
					} 
		 
	return cardToEvaluate;
}


public ArrayList<Integer> calculateScore(ArrayList<Card> cardToEvaluate) {
	int size = cardToEvaluate.size();
	ArrayList<Integer> finalScore = new ArrayList<>();   // 0= punto 1= pip o segno(colore) 2= secondo segno
		if(size<2) {
			finalScore.add(0);
		}
		if(size==2) {						//Coppia
			finalScore.add(1);
			int pip =  cardToEvaluate.get(0).getPip();
			finalScore.add(pip);
		}
		
		if(size==3) {					//Tris
			finalScore.add(3);
			int pip =  cardToEvaluate.get(0).getPip();
			finalScore.add(pip);
		}
		
		if(size==4) {
			int pip1 = cardToEvaluate.get(0).getPip();  
			int pip2 = cardToEvaluate.get(3).getPip(); 
				if(pip1==pip2) {						//Poker  POKER NO SUIT!!!!!!!!!!!!!!!!!!!!!!!!!
					finalScore.add(7);
					int suit=  cardToEvaluate.get(0).getSuit();
					finalScore.add(suit);
				} else {							//Doppia Coppia
					finalScore.add(2);
					finalScore.add(pip2);		//first the bigger one
					finalScore.add(pip1);
				}
		}//End of size 4
		
		
		if(size==5) {
			boolean flush = isFlush(cardToEvaluate);
			boolean straight = isStraight(cardToEvaluate);
			int greaterPip = cardToEvaluate.get(4).getPip();
			
				if(straight && flush) { 							//scala reale
					finalScore.add(8);
					finalScore.add(greaterPip);
					
				} else if(!straight && flush) {						//Colore
					finalScore.add(5);
					finalScore.add(cardToEvaluate.get(0).getSuit() );
				} else if(straight && !flush) {						//Scaletta
					finalScore.add(4);
					finalScore.add(greaterPip);
				} else if(!straight && !flush){											//Full the card in the middle is the tris
					finalScore.add(6);
					finalScore.add(cardToEvaluate.get(2).getPip());
						if(cardToEvaluate.get(2).getPip()==cardToEvaluate.get(0).getPip())  //Determine the pair in the full
							finalScore.add(cardToEvaluate.get(4).getPip());
						else
							finalScore.add(cardToEvaluate.get(0).getPip());
				}
		}
			
	return finalScore;
	
} 


public int convertFinalScore (ArrayList<Integer> finalScore ) {
	int summe=0;
	int multiplicator = 10000;
		for(int i=0; i<finalScore.size(); i++) {
			summe += finalScore.get(i)*multiplicator;
			multiplicator/=100;
	}
	return summe;
}

public ArrayList<Integer>  whoWin(int [] scores) {
	//boolean [] winner = new boolean[scores.length];
	ArrayList<Integer> winners = new ArrayList<>();
	int max = 0;
	for (int i = 0; i < scores.length; i++) {
		if(max < scores[i])
			max = scores[i];
	}
	
	for (int i = 0; i < scores.length; i++) {
		if(scores[i]== max)
			winners.add(i); //numero del vincitore = posizione nell array =>nome
	}
	return winners;
}

/*public String convertPipSuitToWords(ArrayList<Integer> finalScore) { //messo suit = getSuit+20 - rimosso il 20 Non funziona piu
int myScore = finalScore.get(0);
String scoreToWords = score[myScore];
	for (int i = 1; i < finalScore.size(); i++) {
		if(finalScore.get(i) < 20) {
			scoreToWords += " " + pips[finalScore.get(i)];
		} else {
			scoreToWords += " of " + suits[finalScore.get(i)-20];
		}
		
	}
 return scoreToWords;
}
*/


public boolean isStraight(ArrayList<Card> sortedDeck) {
	boolean scala = true;
		for (int i = 0; i < sortedDeck.size()-1; i++) {
			int pip1  =	sortedDeck.get(i).getPip() ;
			int pip2  = sortedDeck.get(i+1).getPip() ;
				if(pip1 != pip2 -1) {
					scala= false;			
				}
		}
		return scala;
}

public boolean isFlush(ArrayList<Card> sortedDeck) {
	boolean flush = true;
	
		for (int i = 0; i < sortedDeck.size()-1; i++) {
			int suit1 = sortedDeck.get(i).getSuit() ;
			int suit2 = sortedDeck.get(i+1).getSuit() ;
				if(suit1 != suit2) {
					flush = false;
				}
		}
		return flush;
}



//get/-set and print Methods

public int getSuit() {
	return suit;
}

public void setSuit(int suit) {
	this.suit=suit;
}

public int getPip() {
	return pip;
}

public void setPip(int pip) {
	this.pip = pip;
}

public void printCard() {
	System.out.println("Card is " + getPip() + " of " + getSuit());
}

public void printDeck(ArrayList<Card> deck) {
	for(Card d : deck)
		d.printCard();
}
	
}
