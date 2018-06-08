package primo;

import java.util.Iterator;
import java.util.Random;

public class NumberToLCD{

	public String [][] createThise( ) {
		
		String [][] letters =  new String[3][10];
		letters [0][1] 	= "   ";
		letters [1][1] 	= "  |";
		letters [2][1] 	= "  |";
		
		letters [0][2] 	= " __ ";
		letters [1][2] 	= " __|";
		letters [2][2] 	= "|__ ";
		
		letters [0][3] 	= "__ ";
		letters [1][3] 	= "__|";
		letters [2][3] 	= "__|";
		
		letters [0][4] 	= "    ";
		letters [1][4] 	= "|__|";
		letters [2][4] 	= "   |";
		
		letters [0][5] 	= " __ ";
		letters [1][5] 	= "|__ ";
		letters [2][5] 	= " __|";
		
		letters [0][6] 	= " __ ";
		letters [1][6] 	= "|__ ";
		letters [2][6] 	= "|__|";
		 
		letters [0][7] 	= "__ ";
		letters [1][7] 	= "  |";
		letters [2][7] 	= "  |";
		
		letters [0][8] 	= " __ ";
		letters [1][8] 	= "|__|";
		letters [2][8] 	= "|__|";
		
		letters [0][9] 	= " __ ";
		letters [1][9] 	= "|__|";
		letters [2][9] 	= " __|";
		
		letters [0][0] 	= " __ ";
		letters [1][0] 	= "|  |";
		letters [2][0] 	= "|__|";
		
		return letters;
	}
		
		
		public int[] createNumber() {
		
				Random r = new Random();
				int number = r.nextInt(1000000000);
				
				System.out.println("Number is  " + number);
				
				int length = String.valueOf(number).length();
				
				int sequenza [] = new int[length];
				
					for (int i = sequenza.length-1; i >= 0; i--) {
						int digit = number % 10;
						number /= 10;
						sequenza[i] = digit;
					}
		
		return sequenza;
		
		}
		
		public void print() {
			int[] numbers = createNumber();
			String [][] letters = createThise();
		System.out.print("Sequence is " );
	
			 for (int i = 0; i < numbers.length; i++) {
				System.out.print(numbers[i] + " ");
			}
		 System.out.println();
			
			for (int j = 0; j < letters.length; j++) {
	 		     for (int i = 0; i < numbers.length; i++) {
			    	   System.out.print(letters[j][numbers[i]] + "  ");
				}
			 System.out.println("");
			}
		}
		
		public static void main (String[]args) {
			NumberToLCD lcd = new NumberToLCD();
			 
			lcd.print();
			 
	}
}


