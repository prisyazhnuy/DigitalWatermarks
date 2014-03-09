package digitalwatermarks;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class ImagePanel extends JPanel{
    private BufferedImage image;

    public ImagePanel(){}
    
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
    
}
