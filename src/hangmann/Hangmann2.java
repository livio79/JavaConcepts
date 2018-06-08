package hangmann;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; 

public class Hangmann2 {
	private String text = "";
	private boolean [] show;
	ArrayList<String> enterd = new ArrayList<>();

	/** get random a line (the sentence to guess) from a file .txt */
	public  String getText( ) throws FileNotFoundException {
		File f = new File("sprichworter2.txt");
		Scanner s = new Scanner(f,"UTF-8");
		Random r = new Random();
		int random = r.nextInt(1961); 
		int counter = 0; 
		
		while(text.isEmpty()) {			
			if(counter==random) {
				text = s.nextLine().toUpperCase();
			}else {
				s.nextLine();
				counter++; 
			} 
		} 
		return text.substring(0, text.lastIndexOf(".")+1);
	}
	
	
	public int countLetters() {
		int count = 0;
		for(int i=0; i<text.length(); i++) {
			if(Character.isLetter(text.charAt(i)))
				count++;
		}
	return count;
	}
	
	
	
	public void updateShow(char x) {
		for(int i=0; i<text.length(); i++) {
			if(text.charAt(i) == x)
				show[i] = true;
		}
	}
	
	public void initializeShowArray(String text) { 
		show = new boolean[text.length()];  
		for(int i=0; i<show.length; i++) {
			if(!Character.isLetter(text.charAt(i)))
				show[i] = true; 
		} 
	}
	
	public String showText(String text) {
		String showText = "";
		for (int i = 0; i < text.length(); i++) {
			if(show[i])
				showText += text.charAt(i);
			else
			showText += "*";
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
	
	
	public boolean enteredLetter(String x){
		if(!enterd.contains(x)) {
			enterd.add(x);
			return false;
			}
		else return true;
	}
	
	public boolean increaseTurn(boolean isEntered, boolean isThere) {
		if(!isEntered && !isThere)
			return true;
		return false;
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Hangmann2 h = new Hangmann2();
		String text = h.getText();
		System.out.println(text);
		
		System.out.println("aa" + h.countLetters(  ));
		h.initializeShowArray(text);
		 h.showText(text);
		 
	}
	
	
}
