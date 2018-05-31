package tts_deckbuilder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 *
 * @author Jackson
 */
public class Card {
    private Image cardImage;
    private String cardDescription;
    
    public Card(Image image, String details){
        cardImage = image;
        cardDescription = details;
    }
    
    public Card(Image image){
        cardImage = image;
    }
    
    public void setImage(Image image){
        cardImage = image;
    }
    public void setDetails(String details){
        cardDescription = details;
    }
    
    public Image getImage(){
        return cardImage;
    }
    
    public String getDescription(){
        return cardDescription;
    }
    
    /**
     * Scale passed image to desired width and height.
     * @param width
     * @param height
     */
    public void scaleImage(int width, int height){
        BufferedImage tempImage = null;
        BufferedImage imageToScale = SwingFXUtils.fromFXImage(cardImage, null);
        
        if(imageToScale != null){
            tempImage = new BufferedImage(width, height, imageToScale.getType());
            Graphics2D g2D = tempImage.createGraphics();
            g2D.drawImage(imageToScale, 0, 0, width, height, null);
            g2D.dispose();
        }
        
        cardImage = SwingFXUtils.toFXImage(tempImage, null);
    }
    
    public void resizeImageWidth(int width, boolean conserveAspectRatio){
        int height = 0;
        if(conserveAspectRatio){
            // calculate height per existing aspect ratio
        }
        else{
            // set to existing height
        }
        scaleImage(width, height);
    }
    
    public void resizeImageHeight(int height, boolean conserveAspectRatio){
        int width = 0;
        if(conserveAspectRatio){
            // calculate width per existing aspect ratio
        }
        else{
            // set to existing width
        }
        scaleImage(width, height);
    }
}
