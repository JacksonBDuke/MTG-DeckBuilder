package tts_deckbuilder;

/**
 *
 * @author Jackson
 */
public class Controller {
    private final int CANVAS_WIDTH_DEFAULT = 3320;
    private final int CANVAS_HEIGHT_DEFAULT = 3255;
    private final int CARD_WIDTH_DEFAULT = 312;
    private final int CARD_HEIGHT_DEFAULT = 445;
    private final int CARD_PADDING_DEFAULT = 10;
    
    private int canvasWidth, canvasHeight, cardWidth, cardHeight, cardPadding;
    
    public Controller(){
        canvasWidth = CANVAS_WIDTH_DEFAULT;
        canvasHeight =CANVAS_HEIGHT_DEFAULT;
        cardWidth = CARD_WIDTH_DEFAULT;
        cardHeight = CARD_HEIGHT_DEFAULT;
        cardPadding = CARD_PADDING_DEFAULT;
    }
    
    public void restoreDefaults(){
        canvasWidth = CANVAS_WIDTH_DEFAULT;
        canvasHeight =CANVAS_HEIGHT_DEFAULT;
        cardWidth = CARD_WIDTH_DEFAULT;
        cardHeight = CARD_HEIGHT_DEFAULT;
        cardPadding = CARD_PADDING_DEFAULT;
    }
    
    public void setCanvasWidth(int i){canvasWidth = i;}
    public void setCanvasHeight(int i){canvasHeight = i;}
    public void setCardWidth(int i){cardWidth = i;}
    public void setCardHeight(int i){cardHeight = i;}
    public void setCardPadding(int i){cardPadding = i;}
    
    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}
    public int getCardWidth(){return cardWidth;}
    public int getCardHeight(){return cardHeight;}
    public int getCardPadding(){return cardPadding;}
}
