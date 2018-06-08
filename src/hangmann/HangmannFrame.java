package hangmann;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

@SuppressWarnings("serial")
public class HangmannFrame extends JFrame {
   Hangmann hh = new Hangmann();															 
   JTextField field, field2;
   JTextArea area;
    int counter = 0;
   
   public HangmannFrame() throws FileNotFoundException   {
	   						 
	   Thread startGame = new Thread() {
	        public synchronized void run() {
	                try {
	                    hh.play();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	        }
	    };
	    
	    JButton button = new JButton("New Game");
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				startGame.start();
			}
		});
	    
	   																			
      JPanel panel = new JPanel(new GridLayout(3, 2, 10, 2));
      panel.setBorder(BorderFactory.createTitledBorder("Play: "));
      panel.add(button);
      field = new JTextField(1);
     
      
      
										      
										      field.setFont(new Font("Serif", Font.BOLD, 18));
										      panel.add(field);
										     field.addActionListener(new ActionListener() {
										         @Override
										         public void actionPerformed(ActionEvent e) {
										        	 char x = field.getText().charAt(0);
										        	 System.out.println(x + "  xxxxxxx");
										        	 hh.setLetter(x);
										        	 
										        	
										        	System.out.println(hh.getLetter()+" letteraaa");
										           area.setText(hh.getSenteceToShow());		
										           
										         }
										      });
										      
										      
										      
										     /* field.addKeyListener(new KeyAdapter() {
										    	  public void keyTyped(KeyEvent e) {
										  			char c = e.getKeyChar();
										  			++counter;
										  			if(counter >1) {
										  				field.setText("");
										  				//field.setText(c+"");
										  			}
										  			
										  			
										  		}
											});*/
      
      
      
     
      
      
 
     
      area = new JTextArea();
      area.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 33));
     												
     												area.setEditable(false);
      area.setLineWrap(true);        
      area.setWrapStyleWord(true);  
      area.setBackground(new Color(204, 238, 241)); 
      area.setForeground(Color.BLUE);
      
      													//startGame.start(); // asocialo 
      													 
       
      JScrollPane scroll = new JScrollPane(area);
      scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 
       
      Container cp = this.getContentPane();
      cp.setLayout(new BorderLayout(5, 5));
      cp.add(panel, BorderLayout.SOUTH);
      cp.add(scroll, BorderLayout.CENTER);
      																							
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("HANGMANN");
      setSize(850, 350);
      setVisible(true);
      			
      area.setText(hh.getSenteceToShow());
   }
   
   
   public static void main(String[] args) throws FileNotFoundException {
            new HangmannFrame();        
   }
}