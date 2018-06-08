package primo;

import java.util.*;

public class NUmbersInWords {
	
static String decimal[][] = {  {"","one","two","three","four","five","six","seven","eight","nine"},
	
	                        // {"","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"},
	
	                         {"", "ten" ,"twenty","tirty","fourty","fifty","sixty","seventy","eigthy","ninty"},
                           
	                         {"xhundred"},
	                         
	                         {"xthousand"},
	                         {"xthousand"},
	                         {"xthousand"},
	                                                 
	                         {"xmillion"},
	                         {"xmillion"},
	                         {"xmillion"},
	                         
	                         {"xbillion"},
	                         {"xbillion"},
	                         {"xbillion"},
	                         };

//001 101 
//552 321 
	public static String numberTowords(int [] sequence) {
		String word = "";
		int length = sequence.length;
		
		for (int i = 0; i < length; i++) {
			String insert= "";
				if(i>1)
					insert =  decimal[i][sequence[i]];
				else
					insert = decimal[i][0];
				
				String pre = "";
				
				if(insert.charAt(0)=='x') {
					pre = decimal[0][sequence[i]];
					insert = insert.substring(1,insert.length());
				}
				word =   pre + insert  + word;
		}
		
		return word;
	}
	
	
	 
	public static int[] createInvertedNumber(boolean automatich) {
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		int number;
		
		if(automatich)
			number = r.nextInt(10000000);
		else {
			 System.out.println("Insert number");
			number = s.nextInt();
		}
		
		  
		int length = String.valueOf(number).length();
		int sequence [] = new int[length];
		
		for(int i= 0; i<sequence.length; i++) {
			sequence[i]= number %10;
			number /= 10;
		}
		
		return sequence;
	}
	
	
	public static void print(int [] sequence) {
		for (int i = sequence.length-1; i >=0; i--) {
			System.out.print(sequence[i] + " ");
		}
	}
	
	public static void main(String[] args) {
	
	
	
	int [] seq = createInvertedNumber(false);
		print(seq);
	
		String a = numberTowords(seq);
	System.out.println(a);
	}

}


/*String decimal     [] = {"zero","one","two","three","four","five","six","seven","eight","nine"};

String tenNineteen [] = {"eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};

String twenty      [] = {"twenty","tirty","fourty","fifty","sixty","seventy","eigthy","ninty"};

String more        [] = {"hundred","thousand","million","billion"};*/
