package recursion;

public class Triangle{
	private int width;

	 public Triangle(int aWidth){
		 width = aWidth;
	 }
	 
	 public int getArea()	 {
		 if (width <= 0) { return 0; }
		 if (width == 1) { return 1; }
		 Triangle smallerTriangle = new Triangle(width - 1);
		 System.out.println(width);
		 int smallerArea = smallerTriangle.getArea();
		 System.out.println(width + "   " +smallerArea);
		 return smallerArea + width;
	 }
	 
	 
		 public static void main(String[] args){
		   Triangle t = new Triangle(10);
		   int area = t.getArea();
		   System.out.println("Area: " + area);
		   System.out.println("Expected: 55");
		   
	}
}