package tts_deckbuilder;

//import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 *
 * @author Jackson
 */

public class Deck {
    private static final int CARD_WIDTH_DEFAULT = 312;
    private static final int CARD_HEIGHT_DEFAULT = 445;
    private static final int CARD_OUTLINE_THICKNESS_DEFAULT = 10;
    private static final int DECK_WIDTH_DEFAULT =
            10 * ((2 * CARD_OUTLINE_THICKNESS_DEFAULT) + CARD_WIDTH_DEFAULT);
    private static final int DECK_HEIGHT_DEFAULT =
            10 * ((2 * CARD_OUTLINE_THICKNESS_DEFAULT) + CARD_HEIGHT_DEFAULT);
    
    private static int desiredDeckImageWidth;
    private static int desiredDeckImageHeight;
    private static int desiredCardOutlineThickness;
    private static int desiredCardWidth;
    private static int desiredCardHeight;
        private static String deckName;
    
    ArrayList<Card> deckList;
    
    
    public Deck(){
        deckList = new ArrayList();
    }
    
    public Deck(String name){
        deckName = name; // Need to run this through regex
        deckList = new ArrayList();
    }
    
    public String getDeckName(){
        return deckName;
    }
    
    public void addCard(Card c){
        deckList.add(c);
    }
    
    public void removeCard(Card c){
        deckList.remove(c);
    }
    
    public void removeCardAtIndex(int index){
        deckList.remove(index);
    }
    
    public Card getCardAtIndex(int index){
        return deckList.get(index);
    }
    
    public int size(){
        return deckList.size();
    }
    
    public void resizeCards(int width, int height){
        deckList.forEach((c) -> {
            c.scaleImage(width, height);
        });
    }
}
