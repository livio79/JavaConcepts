package recursion;

public class Reverse {
	 String a = "";
	public   String reverse(String word) {
		 
		 
		//if(word.length()<=1) return ""	;  
		 
		 
			return a +=reverse( word.substring(word.length()-1));
			 
			
		}
		 
 
	
	public static void main(String[] args) {
		 
		 
		Reverse r = new Reverse();
		
		String a = r.reverse("ciao");
		System.out.println(a);
	}

}
