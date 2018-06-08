package poker;

import java.util.*;

public class Card {
		private String [] suits = {"Heart", "Diamond", "Club", "Spade"};
		private String [] pips = {	"2", "3",	"4",	"5", 	"6",	"7",	"8", "9",	"10",	"J",	"Q",	"K", "A" };  
		private String suit;
		private String pip;
		
		
		int lowerPipValue = pips.length - (new PokerTest().getNumberOfPlayers() +3);  //riguarda il nome - minore e il numero di giocatori minore deve essere il numero di carte
		private  ArrayList<Card> deck = new ArrayList<>();							 // ho modificato createDeck e converToPips
		
			
		public ArrayList<Card> createDeck (boolean shuffle){
			for(int i=0; i<suits.length; i++) {
				for(int j=lowerPipValue; j<pips.length; j++) {						
					Card c = new Card();
					c.suit = suits[i];
					c.pip  = pips[j];
					deck.add(c); 
				}
			}
			if(shuffle)
				Collections.shuffle(deck);
			return deck;
		}
		
		
		/** @param card
		 * @return position = correspondent position of the this pip int the above array. It help to sort the five cards
		 */
		public int convertPipsToNumbers(Card card) {
			String onePip = card.getPip();
			int position = 0;
			for (int i = lowerPipValue; i < pips.length; i++) {
				if(onePip.equals(pips[i]))
					position=i;
			}
			return position;
		}
		
		public int convertSuitToNumbers(Card card) {
			String oneSuit = card.getSuit();
			int position = 0;
			for (int i =0; i < suits.length; i++) {
				if(oneSuit.equals(suits[i]))
					position=i;
			}
			return position;
		}
		
	
		/** @param cards
		 * @return sort the five cards
		 */
		public Card [] sortDeck(Card [] cards) {
			int length = cards.length;
			
			int [] positions = new int [length];
			
			for (int i = 0; i <length; i++) {
				positions[i] = convertPipsToNumbers(cards[i]);
			}
			
		//insertion Sort in order to sort the five cards
				        for (int i=1; i<length;  ++i) {		
					            int key = positions[i];
					            Card keyCard = cards[i];
					            int j = i-1;
				 
				            while (j>=0 && positions[j] > key) {
					                positions[j+1] = positions[j];
					                cards[j +1] = cards [j];
					                j = j-1;
					            }
				            positions[j+1] = key;
				            cards[j+1] = keyCard;
				        }
				    
				 return cards;
			}
	
		
		
		public void giveCArds(Player player, ArrayList<Card> deck) {   //UN Giocatore  riceve 5 carte dal mazzo
			Card [] cards = new Card[5];
					for(int i = 0; i<5; i++) {
						cards[i] = deck.get(0);
						deck.remove(0);
					}
			player.setCards(cards);
			
		}
		
		
		public void printFive(Player player) {
			
				Card [] cardOnHand = player.getCards();
				System.out.println("Player " + player.getName() + " has got:");
				
					for (int j = 0; j < cardOnHand.length; j++) {
						System.out.println(cardOnHand[j].getPip() + " of " + cardOnHand[j].getSuit());
					}
		}
		
		
		//get/-set and print Methods
		
		public String getSuit() {
			return suit;
		}
		
		public String getPip() {
			return pip;
		}
		
		public void printCard() {
			System.out.println("Card is " + getPip() + " of " + getSuit());
		}
		
		public void printDeck(ArrayList<Card> deck) {
			for(Card d : deck)
				d.printCard();
		}
			
}
