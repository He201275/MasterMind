package MasterM;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.LabelUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class InterGraph extends JFrame implements ActionListener{
	  private JPanel pan = new JPanel();
	  private JButton bouton = new JButton("PLAY");
	  private JButton bouton2 = new JButton("RULES");	
	  private JButton bouton3 = new JButton("HOME");
	  private JPanel container = new JPanel();
	  private JLabel label = new JLabel("MASTERMIND");
	  private int compteur = 0;

	  public InterGraph(){
	    this.setTitle("MasterMind");
	    this.setSize(300, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(new ImagePanel(new ImageIcon("mastermind.png").getImage()));

	    container.setBackground(Color.lightGray);
	    container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);

	    bouton.addActionListener(this);
	    bouton2.addActionListener(this);
	    bouton3.addActionListener(this);
	    
	    //Image img = ImageIO.read(new File("mastermind.png"));
        //label.drawImage(img, 0, 0, this);

	    JPanel south = new JPanel();
	    south.add(bouton);
	    south.add(bouton2);
	    south.add(bouton3);
	    container.add(south, BorderLayout.SOUTH);

	    Font police = new Font("Tahoma", Font.BOLD, 12);
	    label.setFont(police);
	    label.setForeground(Color.BLACK);
	    label.setHorizontalAlignment(JLabel.CENTER);
	    container.add(label, BorderLayout.NORTH);
	    this.setContentPane(container);
	    this.setVisible(true);
	  }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		  if(arg0.getSource() == bouton)
		    label.setText("Vous avez cliqué sur PLAY");

		  if(arg0.getSource() == bouton2)
		    label.setText("vous avez cliqué sur RULES");
		  
		  if(arg0.getSource() == bouton3)
			label.setText("MASTERMIND");
		}

	  //…
	}
//public void paintComponent(Graphics g){                
		//    Font font = new Font("Arial", Font.BOLD, 20);
		  //  g.setFont(font);
		    //g.setColor(Color.BLACK);          
		    //g.drawString("Welcome to Mastermind", 10, 20); 
		    //try {
		      //  Image img = ImageIO.read(new File("mastermind.png"));
		        //g.drawImage(img, 0, 0, this);
		        //Pour une image de fond
		        //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		      //} catch (IOException e) {
		        //e.printStackTrace();
		      //} 
 


