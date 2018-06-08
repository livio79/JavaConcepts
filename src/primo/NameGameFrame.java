package primo;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class NameGameFrame extends JFrame
{

    static JTextField textfield = new JTextField(20);
    static JTextArea  textarea = new JTextArea(30,30);

    public static void main( String[] args)
    {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Name Game");
        frame.setLocation(500,400);
        frame.setSize(800,800);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel label = new JLabel("Enter the Name or Partial Name to search:");
        
        ImageIcon im = new ImageIcon("C:\\Users\\belli\\Desktop\\Immagini\\ball.png");
        ImageIcon im2 = new ImageIcon("C:\\Users\\belli\\Desktop\\Immagini\\nought.gif");
        label.setIcon(im2);
        label.setIcon(im);
       
        label.setText("dddddddd");
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(2,2,2,2);

        panel.add(label,c);

        c.gridx = 0;
        c.gridy = 1;
        textfield.setAlignmentY(CENTER_ALIGNMENT);
        textfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textfield.setFont(new Font("Serif", 1, 55));
        textfield.setEditable(false);
        textfield.setForeground(Color.LIGHT_GRAY);
        textfield.setBackground(Color.cyan);
        textfield.setText("ddeeeeeeeeeeeeedededed dededee");
        textarea.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(textarea,c);

        JButton button = new JButton("Search");
        c.gridx = 1;
        c.gridy = 1;
        panel.add(button,c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(textfield,c);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

    }
        static class Action implements ActionListener
        {
        public void actionPerformed(ActionEvent e)
        {

           //This is the code that should  perform the task
            String name  = textfield.getText();
            textarea.append(name);
        }
    }
}