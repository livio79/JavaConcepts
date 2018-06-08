package primo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Demo2 {
	public  void fizzBuzz(int x,int num1, int num2, String a, String b) {
				
		for(int i=0; i<=x; i++) {
			boolean isNum1= num12(i, num1);
			boolean isNum2= num12(i, num2);
			String fb = "";
		
			if(i!=0) {
				if ( i%num1==0 || isNum1)
					fb+= a;
				if(i% num2==0 || isNum2)
					fb+= b;
				}
			if( i==0 || i% num1!=0 && i% num2!=0 && !isNum1 && !isNum2 )
				fb +=i;			
			
			System.out.println(fb);
		}	
		
	}
	
	public  boolean num12(int i, int numero) {
		boolean num=false;
			while(i>0) {
				int div = i % 10;
				i /= 10;
				if(div==numero) num=true;
			}
			return num;
	}

	
	
	public static void main(String[] args) {
				Demo2 d = new Demo2();
				System.out.println("kofgjg0rg0rekgre");
				d.fizzBuzz(100, 3, 5, "Fizz", "Buzz");
				
				
	}
	
}