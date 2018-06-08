package impiccato9; 

//REFACTOR - lowerCase/uppercase - Info -- lettera gia messa
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; 

public class Imp {
	private String text= "";
	private boolean [] show;
	ArrayList<String> enterd = new ArrayList<>();
	private int TENTATIVI = 5;
	private int turno= 1;
	private String file  ;	
	
	
	/** get random a line (the sentence to guess) from a file .txt */
	public  String createProverb() throws FileNotFoundException { 
		File f = new File( getFile() + ".txt");
		Scanner s = new Scanner(f,"UTF-8");
		int counter = 0;
		 
		Random r = new Random(); 
		int random = r.nextInt(countLine());  
		
		counter = 0;  
		while(text.isEmpty()) {			
			if(counter==random) {
										text = s.nextLine().toUpperCase();
			}else {
				s.nextLine();
				counter++; 
			} 
		} 
		return text;
	}
	
	/**count the number of lines in a .txt file*/
	public int countLine( ) throws FileNotFoundException {
		File f = new File( getFile() + ".txt");
		Scanner s = new Scanner(f,"UTF-8");
		int counter = 0;
		
		while(s.hasNextLine()) {
			s.nextLine();
			counter++;
		}  
		return counter; 
	}
	
	
	
	/**initialize the boolean array with true if the character is not a letter*/
	public void initializeShowArray(String text) { 
		show = new boolean[text.length()];  
		for(int i=0; i<show.length; i++) {
			if(!Character.isLetter(text.charAt(i)))
				show[i] = true; 
		} 
	}
	
	/**the input is valid if its lenght > 0 and it is a letter*/
	public boolean validInput(String input) {
		if(input.length()==0) { 										//message = "Immetti una lettera";
			return false;
		} else if(!Character.isLetter(input.charAt(0))) { 													//message = "Input non valido";
			return false;
		} 
		return true;
	}
	
	
	/**If the ArrayList entered contains the input return true
	 * else add the input into the ArrayList and return false*/
	public boolean enteredLetter(String input){
		if(!enterd.contains(input)) {
			enterd.add(input);
			return false;
			}
		else {
																
			return true;
		}
	}
	
	
	/**return true if the Sentence to guess contains the input*/
	public boolean isThere(String input) { 
		if(text.contains(input))
			return true;
		return false;
	}
	
	
	public void increaseTurn() {
		++turno;
	}
	
	

	/**update the boolean array*/
	public void updateShow(String input) {
		for(int i=0; i<text.length(); i++) { 
			if(text.substring(i, i+1).equals(input))
				show[i] = true;
		}
	}
	
	
	
	public String showText() {
		String showText = "";
		for (int i = 0; i < text.length(); i++) {
			if(show[i])
				showText += text.charAt(i);
			else
			showText += "-";
		}
		return showText;
	}
	
	
	
	public boolean win() {  
		for(int i=0; i<show.length; i++) {
			if(!show[i])
				return false;
		}
		return true;
	}
	
	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	public boolean lose() {
		if(turno<TENTATIVI)
			return false;
		return true;
	}
	
	 
	public boolean [] getShow() {
		return show;
	}
	
	public String getText() {
		return text;
	}
	
	public int getTurno() {
		return turno;
	}
	
	public int getTentativi() {
		return TENTATIVI;
	}
	
	public ArrayList<String> getEnterd(){
		return enterd;
	}
	
	public void resetEntered() {
		this.enterd.clear();
	}
	
	public void setFile(String f) {
		this.file = f;
	}
	
	public String getFile() {
		return file;
	}
	
 
	
}
