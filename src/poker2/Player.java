package poker2;

import java.util.*;

import javax.xml.stream.events.Namespace;

public class Player {
	Card c  = new Card();
	private String name ;
	int numberOfPlayers = 0;
	  String [] names = {"Livio","Simon","Alex","Andrew","Hermann"}; //private rimettilo
	private int money;
	private ArrayList<Card> cards;
	
		
		Player(){
			money = 1000;
		}
		
		public Map<Player, ArrayList<Card>> createPlayer( ArrayList<Card> mazzo) {
			numberOfPlayers = c.getNumGiocatori();
			Map<Player, ArrayList<Card>> players = new HashMap<>();					//Differenza tra hashmap e treemap
			ArrayList<ArrayList<Card>>  carteperognigiocatore = new ArrayList<>();
			Player [] p = new Player[numberOfPlayers];
			 
			for(int i=0; i<numberOfPlayers; i++) {
				carteperognigiocatore.add(c.giveCards(mazzo));
				//System.out.println(names[i]);
				//c.printDeck(carteperognigiocatore.get(i));
				p[i] = new Player();
				p[i].setName(names[i]);
				players.put(p[i], carteperognigiocatore.get(i));
			
				//System.out.println();
			}
			
			
			return players;
		}
		
		public void printWinner (ArrayList<Integer> winners) {
			for(Integer tmp : winners) {
				System.out.println(names[tmp] + " Win!");
			}
		}
		
		
		public void setMoney(int m) {
			money = m;
		}
		
		public int getMoney() {
			return money;
		}
		
		public void setName(String n) {
			name = n;
		}
		
		public String getName() {
			return name;
		}
		public void setCards(ArrayList<Card> cards) {
			this.cards = cards;
		}
		
		public ArrayList<Card> getCards( ) {
			return cards;
		}
		
		
		
		public void printPlayer() {
			System.out.println("Player " + getName() + "   Money " + getMoney());
		}
}
