package MasterM;

import java.awt.Graphics;
import java.awt.Image;
 
import javax.swing.JButton;
import javax.swing.JPanel;

//ne fonctionne pas mais devrait servir à importer une image

public class ImagePanel extends JPanel {
	
	private Image img;
    
    public ImagePanel(Image img){
        this.img = img;
        add(new JButton("Bouton1"));
        add(new JButton("Bouton2"));
    }
     
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
