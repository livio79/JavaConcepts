package poker2;

import java.util.*;

public class Score extends Card {
	
	
	
	
/*	
public ArrayList<Card> valuableCards(ArrayList<Card> c ) {
		
		ArrayList<Card> cardToEvaluate = new ArrayList<>();

			for (int i = 0; i < c.size()-1; i++) {
				int pip1 = c.get(i).getPip();
				int pip2 = c.get(i+1).getPip();
						if(pip1 ==  pip2) {
							if(!cardToEvaluate.contains(c.get(i)))
								cardToEvaluate.add(c.get(i));
						
							cardToEvaluate.add(c.get(i+1));
						}		
			}
					 
			if(cardToEvaluate.size()==0) {// potrebbe essere una scala o un colore
					boolean scala = isStraight(c);
					boolean flush =  isFlush(c);
					System.out.println("scala e flush " + scala + " " + flush);
								if(scala || flush) {					 
									for(Card t : c) {
										cardToEvaluate.add(t);
									} 
								}
						} 
			 
		return cardToEvaluate;
	}


	public boolean isStraight(ArrayList<Card> cards) {
		boolean scala = true;
			for (int i = 0; i < cards.size()-1; i++) {
				int pip1  =	cards.get(i).getPip() ;
				int pip2  = cards.get(i+1).getPip() ;
					if(pip1 != pip2 -1) {
						scala= false;			
					}
			}
			return scala;
	}
	
	public boolean isFlush(ArrayList<Card> cards) {
		boolean flush = true;
		
			for (int i = 0; i < cards.size()-1; i++) {
				int suit1 = cards.get(i).getSuit() ;
				int suit2 = cards.get(i+1).getSuit() ;
					if(suit1 != suit2) {
						flush = false;
					}
			}
			return flush;
	}
	
	String [] score = {"Nothing", "One Pair", "Two Pair", "Tris", "Straight", "Flush", "Full", "Poker", "Royal Flush"};
	
	public String convertPipSuitToWords(ArrayList<Integer> finalScore) { //metti suit = getSuit+20
		int myScore = finalScore.get(0);
		String scoreToWords = score[myScore];
			for (int i = 0; i < finalScore.size(); i++) {
				if(finalScore.get(i) < 15) {
					scoreToWords += " " + pips[finalScore.get(i)];
				} else {
					scoreToWords += " of " + suits[finalScore.get(i)-20];
				}
				
			}
		 return scoreToWords;
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
					if(pip1==pip2) {						//Poker
						finalScore.add(7);
						int suit=  cardToEvaluate.get(0).getSuit();
						finalScore.add(suit);
					} else {							//Doppia Coppia
						finalScore.add(2);
						finalScore.add(pip1);
						finalScore.add(pip2);
					}
			}//End of size 4
			
			
			if(size==5) {
				boolean flush = isFlush(cardToEvaluate);
				boolean straight = isStraight(cardToEvaluate);
				System.out.println("22scala e flush " + straight + " " + flush);
				int greaterPip = cardToEvaluate.get(4).getPip();
				
					if(straight && flush) { 							//scala reale
						finalScore.add(8);
						finalScore.add(greaterPip);
						
					} else if(!straight && flush) {						//Colore
						finalScore.add(5);
						finalScore.add(cardToEvaluate.get(0).getSuit());
					} else if(straight && !flush) {						//Scaletta
						finalScore.add(4);
						finalScore.add(greaterPip);
					} else if(!straight && !flush){											//Full
						finalScore.add(6);
						finalScore.add(cardToEvaluate.get(2).getPip());
					}
			}
				
		return finalScore;
		
	}*/
	
	
}
	
	



/*	
	
	
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
				
		
		
	
	}*/
