package primo;

import java.util.Random;

public class Demo {
	public String[] fizzBuzz(int x) {
		String [] fb = new String[x];
		
		for(int i=0; i<x; i++) {
			String f = "";
			
			if(i%3==0)
				f+= "Fizz";
			if(i%5==0)
				f+="Buzz";
			if(f.equals(""))
				f+= i;

			f = modified(f);
			fb[i] = f;
		}
		return fb;
	}
	
	public String[] add(String [] fb) {
		String [] fb2 = new String [fb.length];
		for(int i =0; i<fb.length; i++) {
			if(fb[i].equals(i+""))
			 fb2[i] = fb[i] + "--         blop";
			else
				fb2[i]= fb[i];
		}
		
		return fb2;
	}
	
	
	public String modified(String f) {
		if(f.contains("Buzz"))	
			f += "hallo";
		return f;
	}
	
	
	public void print(String [] fb) {
		for(String t: fb)
			System.out.println(t);
	}
	
	public static void main(String[] args) {
		Demo d = new Demo();
		
		String [] fb = d.fizzBuzz(100);
		String [] fb2 = d.add(fb);
		d.print(fb2);
}
}
