package poker;

public class Player {
	private String name ;
	private String [] names = {"Livio","Simon","Alex","Andrew","Hermann"};
	private int money;
	private Card [] cards;
	
		
		Player(){
			money = 1000;
			cards = new Card [5];
		}
		
		public Player[] createPlayer(int numberOfPlayers) {
			Player [] player = new Player[numberOfPlayers];
			
			for(int i=0; i<numberOfPlayers; i++) {
				player[i] = new Player();
				player[i].setName(names[i]);
			}
			return player;
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
		
		public void setCards(Card [] newCard) {
			cards = newCard;
		}
		
		public Card [] getCards( ) {
			return cards;
		}
		
		
		
		public void printDeck() {
			System.out.println("Player " + getName() + "   Money " + getMoney());
		}
}
