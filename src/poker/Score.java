package poker;

import java.util.*;

/*
 * per la valutazione mi serve: pip, segno(colore), segno(carta piu alta)
 * 						output: pip, carta piu alta, seconda carta piu alta, segno 
 * posso esprimere tutto con numeri e assegnare ad un arrayList la size della list dipende dal punto es. punto(2), pip1(x) pip2(y)....
 * comunque nel valutare i punti hanno importanza decrescente
 */

public class Score extends Card {
	
	String myScore [] = {"None","Pair","Doulbe Pair","Tris","Straight","Flush","Poker","Stright Flush","Royal Flush"};
	
	public ArrayList<Card> valuableCards(Card[] c ) {
		
		ArrayList<Card> cardToEvaluate = new ArrayList<>();

			for (int i = 0; i < c.length-1; i++) {
				String pip1 = c[i].getPip();
				String pip2 = c[i+1].getPip();
						if(pip1.equals(pip2)) {
							if(!cardToEvaluate.contains(c[i]))
								cardToEvaluate.add(c[i]);
						
							cardToEvaluate.add(c[i+1]);
						}		
			}
			
			
					 
			if(cardToEvaluate.size()==0) {// potrebbe essere una scala!
					boolean scala = true;
						for (int i = 0; i < c.length-1; i++) {
							int pip1 =   convertPipsToNumbers(c[i]);
							int pip2 = convertPipsToNumbers(c[i+1]) ;
								if(pip1 != pip2 -1) {
									scala= false;			//se pip1 e diveso da pip2 -1 non e scala
						         }
								
								if(scala) {					//se e scala aggiungi tutte le carte in newC
									for(Card t : c) {
										cardToEvaluate.add(t);
									} 
								}
						} 
			}
			
			
		return cardToEvaluate;
	}
	
	
	
	public ArrayList<Integer> score1(ArrayList<Card> cardToEvaluate) {
		int size = cardToEvaluate.size();
		ArrayList<Integer> finalScore = new ArrayList<>();   // 0= punto 1= pip o segno(colore) 2= secondo segno
			
			if(size==2) {						//Coppia
				finalScore.add(1);
				int pip = convertPipsToNumbers(cardToEvaluate.get(0));
				finalScore.add(pip);
			}
			
			if(size==3) {					//Tris
				finalScore.add(3);
				int pip = convertPipsToNumbers(cardToEvaluate.get(0));
				finalScore.add(pip);
			}
			
			if(size==4) {
				String pip1 = cardToEvaluate.get(0).getPip();
				String pip2 = cardToEvaluate.get(3).getPip();
					if(pip1.equals(pip2)) {						//Poker
						finalScore.add(7);
						int mySuit= convertSuitToNumbers(cardToEvaluate.get(0));
						finalScore.add(mySuit);
					} else {							//Doppia Coppia
						finalScore.add(2);
						int pipFirst = convertPipsToNumbers(cardToEvaluate.get(0));
						int pipSecond= convertPipsToNumbers(cardToEvaluate.get(3));
						finalScore.add(pipFirst);
						finalScore.add(pipSecond);
						
					}
			}//End of size 4
			
			if(size==5) {
				boolean flush = true;
				boolean straight = true;
				int greaterPip = convertPipsToNumbers(cardToEvaluate.get(4));
				
				String suit1 = cardToEvaluate.get(0).getSuit();
					
					for(int i= 1; i<cardToEvaluate.size(); i++ ) {		//colore o scala reale
						String suit2 = cardToEvaluate.get(i).getSuit();
								if(!suit1.equals(suit2))
									flush = false;
					}
				
					for (int i = 0; i < cardToEvaluate.size()-1; i++) {
						int pip1 =   convertPipsToNumbers(cardToEvaluate.get(i));
						int pip2 = convertPipsToNumbers(cardToEvaluate.get(i+1)) ;
							
							if(pip1 != pip2 -1) {
								straight= false;			//se pip1 e diveso da pip2 -1 non e scala
					         }
					}
					
					if(straight && flush) { //scala reale
						
						finalScore.add(8);
						finalScore.add(greaterPip);
						
					}
					else if(!straight && flush) {   //colore
						int suitNumber = convertSuitToNumbers(cardToEvaluate.get(0));
						finalScore.add(5);
						finalScore.add(suitNumber);
					}
					else {					//scaletta
						finalScore.add(4);
						finalScore.add(greaterPip);
						
					}
					
			}//End of size==5
			
		return finalScore;
		
	}
	
	
	
	
	
	public static void main(String[] args) {


		
		Card c = new Card();
		Player p = new Player();
		Score score = new Score();
		int nPlayers = new PokerTest().getNumberOfPlayers();
		
		Player  [] players = p.createPlayer(nPlayers);		//5 e il numero di giocatori!!!!!!!!!!!!!
		ArrayList<Card> deck = c.createDeck(true); 		//creo Mazzo mischiato
		
		
			for (int i = 0; i < nPlayers; i++) {
				c.giveCArds(players[i], deck);
				Card [] mazzetto = players[i].getCards();
				c.sortDeck(mazzetto);
				
				ArrayList<Card> newC =score.valuableCards(mazzetto);
				ArrayList<Integer> scoreFInale=   score.score1(newC);
				
				for(Integer t : scoreFInale) {
					System.out.print("   AA " + t);
				}
				
				System.out.println();
				 
				for(Card t : newC) {
					System.out.println( t.getPip() + " of " + t.getSuit());
					
				}
				 
				
				
				c.printFive(players[i]);
				System.out.println();
				}
				
		
		
	
	}
}