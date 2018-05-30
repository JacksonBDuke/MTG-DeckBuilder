package tts_deckbuilder;

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
    
    public void resizeImage(int width, int height){
        
    }
    
    public void resizeImageWidth(int width, boolean conserveAspectRatio){
        int height = 0;
        if(conserveAspectRatio){
            // calculate height per existing aspect ratio
        }
        else{
            // set to existing height
        }
        resizeImage(width, height);
    }
    
    public void resizeImageHeight(int height, boolean conserveAspectRatio){
        int width = 0;
        if(conserveAspectRatio){
            // calculate width per existing aspect ratio
        }
        else{
            // set to existing width
        }
        resizeImage(width, height);
    }
}
