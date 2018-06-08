package poker2;

import java.util.*;
/*metodo whowhin
bet / win / rischio( coefficente rischio, soldi, punto) / -di 100 euro...out. se player < 2 Nuovo giocatore
file text per il buchaltung
Grafica
riordina la sequenza di metodi in Card
JUnit
JDoc
*/



public class Demo { 
	 
	 
	public static void main(String[] args) {
		Card carte = new Card();
		Player player= new Player();
		int numplay = 5;
		
		ArrayList<Card> deck = new ArrayList<>();
		deck = carte.createDeck(true);
		//carte.printDeck(deck);
	
		ArrayList<ArrayList<Card>> p = new ArrayList<>();
		
		for(int i=0; i<numplay; i++) {
			p.add(carte.sortDeck(carte.giveCards(deck)));
		}
		 
		System.out.println("GIVE 5 CARDS TO THE " + numplay  +" PLAYERS");
		for (int i = 0; i < numplay; i++) {
			System.out.println("***"+player.names[i]+"***");
				carte.printDeck(p.get(i));
			System.out.println();
			
		}
		
			
	
		ArrayList<ArrayList<Card>> newP = new ArrayList<>();
		System.out.println("CHANGE AND GIVE NEW CARDS TO THE " + numplay  +" PLAYERS");
		for (int i = 0; i < numplay; i++) {
			System.out.println("***"+player.names[i]+"***");
			newP.add(  carte.sortDeck(carte.changeCards(    carte.valuableCards(p.get(i)))));   //RIDATO LE CARTE- RIORDINATE
			System.out.println();
		}
		
		
		
		
		
		ArrayList<ArrayList<Integer>> scor = new ArrayList<>();
		for (int i = 0; i < numplay; i++) {
			 scor.add(carte.calculateScore(newP.get(i)));  //Arraylist di arraylist di integer: lo score
		}
		
	 
		
		for (int i = 0; i < scor.size(); i++) {
			System.out.println("N° " + i +" sequenza score : " + scor.get(i).size());
			for(Integer tmp : scor.get(i)) {
				System.out.print(tmp + " ");
			}
			System.out.println();
		}
		
		/*
		
		
		int [] winners = new int [newP.size()];
		
		*/
		
		 
		/*for (int i = 0; i < numplay; i++) {
			System.out.println("***"+player.names[i]+"***");
			winners[i] = carte.convertFinalScore(carte.calculateScore(p.get(i)));
			System.out.println();
		}
		
		 
			
			for (int i = 0; i < winners.length; i++) {
				System.out.println("player " + i + " ha " + winners[i]);
			}
			
			ArrayList<Integer> win = carte.whoWin(winners);
			player.printWinner(win);*/
			
			
	
	}
}		
		





 
		
	
	
 
 