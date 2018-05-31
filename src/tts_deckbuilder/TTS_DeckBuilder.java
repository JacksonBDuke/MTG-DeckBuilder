package tts_deckbuilder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.File;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.BorderPane;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Date;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
//import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 *
 * @author Jackson
 */
public class TTS_DeckBuilder extends Application implements EventHandler<ActionEvent> {
    
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double DPI = Screen.getPrimary().getDpi();
    private final int MAXIMUM_NUMBER_OF_CARDS = 70;
    private final KeyCombination kcombo_ControlV = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
    private final KeyCombination kcombo_Enter = new KeyCodeCombination(KeyCode.ENTER);
    
    Image deckImage;
    Image cardImage;
    Image previousImage;
    Image previousImage2;
    
    ImageView ivCurrent;
    ImageView ivPrevious;
    ImageView ivPrevious2;
    
    TextField tvDeckName;
    
    Deck cardDeck = new Deck();
    
    Button button;
    Stage window;
    Scene scene1;
    
    Text cardCounter;
    Text statusMessage;
    Text currentText;
    Text lastText;
    Text previousText;
    
    Button button_GrabClipboard;
    Button button_AddToDeck;
    Button button_Export;
    Button button_PreviousCard;
    Button button_NextCard;
    Button button_Settings;
    
    int currentIndex = 0;
    int canvasWidth, canvasHeight, cardWidth, cardHeight, cardPadding;
    
    Controller controller = new Controller();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        System.out.println(DPI);
        
        canvasWidth = controller.getCanvasWidth();
        canvasHeight = controller.getCanvasHeight();
        cardWidth = controller.getCardWidth();
        cardHeight = controller.getCardHeight();
        cardPadding = controller.getCardPadding();
        
        //window.setOnCloseRequest(e -> closeProgram());
        window = primaryStage;
        window.getIcons().add(new Image(TTS_DeckBuilder.class.getResourceAsStream("icon.png")));
        
        cardImage = previousImage = previousImage2 = null;
        
        ivCurrent = new ImageView();
        ivPrevious = new ImageView();
        ivPrevious2 = new ImageView();
        tvDeckName = new TextField();
        
        cardCounter = new Text("No cards added");
        statusMessage = new Text();
        
        cardCounter.setFill(Color.web("#FEFEFE"));
        statusMessage.setFill(Color.web("#FEFEFE"));
        
        ivCurrent.setFitWidth(cardWidth);
        ivCurrent.setFitHeight(cardHeight);
        ivCurrent.setPreserveRatio(false);
        ivCurrent.setSmooth(true);
        ivCurrent.setCache(true);
        
        ivPrevious.setFitWidth(cardWidth);
        ivPrevious.setFitHeight(cardHeight);
        ivPrevious.setPreserveRatio(false);
        ivPrevious.setSmooth(true);
        ivPrevious.setCache(true);
        
        ivPrevious2.setFitWidth(cardWidth);
        ivPrevious2.setFitHeight(cardHeight);
        ivPrevious2.setPreserveRatio(false);
        ivPrevious2.setSmooth(true);
        ivPrevious2.setCache(true);
        
        button_GrabClipboard = new Button("Grab from Clipboard");
        
        button_AddToDeck = new Button("Add Card to Deck");
        button_AddToDeck.setDisable(true);
        
        button_Export = new Button("Export");
        button_Export.setDisable(true);
        
        button_PreviousCard = new Button("Previous Card");
        button_PreviousCard.setDisable(true);
        
        button_NextCard = new Button("Next Card");
        button_NextCard.setDisable(true);
        
        button_Settings = new Button("Settings");
        
        button_GrabClipboard.setOnAction(e -> {
            System.out.println("Grab from Clipboard clicked.");
            setCurrentImageToClipboardContents();
        });
        
        button_AddToDeck.setOnAction(e ->{
            System.out.println("Add to Deck clicked.");
            //public void addToDeck(Text counter, javafx.scene.Node buttonAdd, javafx.scene.Node buttonExport, javafx.scene.Node buttonGrab, Text status){
            addToDeck();
        });
        
        button_Export.setOnAction(e ->{
           System.out.println("Export clicked.");
           System.out.println("Exporting to image...");
           exportDeck(statusMessage);
           System.out.println("Done.");
        });
        
        button_PreviousCard.setOnAction(e ->{
            System.out.println("Previous Card clicked.");
        });
        
        button_NextCard.setOnAction(e ->{
            System.out.println("Next Card clicked.");
        });
        
        button_Settings.setOnAction(e ->{
            System.out.println("Settings clicked.");
            changeSettings(); 
        });
        
        VBox topMenu = new VBox();
        VBox leftMenu = new VBox();
        VBox rightMenu = new VBox();
        HBox bottomMenu = new HBox();
        HBox centerContent = new HBox();
        
        centerContent.setSpacing(3.0);
        
        VBox currentCardBox = new VBox();
        currentCardBox.autosize();
        currentCardBox.setAlignment(Pos.CENTER);
        
        VBox previousCardBox = new VBox();
        previousCardBox.autosize();
        previousCardBox.setAlignment(Pos.CENTER);
        
        VBox previousCardBox2 = new VBox();
        previousCardBox2.autosize();
        previousCardBox2.setAlignment(Pos.CENTER);
        
        currentText = new Text("Current Card");
        currentText.setFill(Color.web("#FEFEFE"));
        lastText = new Text("Last Added");
        lastText.setFill(Color.web("#FEFEFE"));
        previousText = new Text("Previous Added");
        previousText.setFill(Color.web("#FEFEFE"));
        
        tvDeckName.setAlignment(Pos.CENTER);
        tvDeckName.autosize();
        tvDeckName.setDisable(true);
        tvDeckName.setPromptText("Enter Custom Deck Name");
        
        currentCardBox.getChildren().addAll(ivCurrent, currentText);
        previousCardBox.getChildren().addAll(ivPrevious, lastText);
        previousCardBox2.getChildren().addAll(ivPrevious2, previousText);
        
        //*** Set menu contents ***
        topMenu.getChildren().addAll(cardCounter, statusMessage);
        bottomMenu.getChildren().addAll(button_Settings, button_GrabClipboard, button_AddToDeck, button_Export);
        //leftMenu.getChildren().addAll(button_PreviousCard);
        //rightMenu.getChildren().addAll(button_NextCard);
        
        centerContent.getChildren().addAll(previousCardBox2, previousCardBox, currentCardBox);
        centerContent.setAlignment(Pos.CENTER);
        
        topMenu.setAlignment(Pos.TOP_CENTER);
        bottomMenu.setAlignment(Pos.BOTTOM_CENTER);
        
        BorderPane borderPane = new BorderPane( centerContent, topMenu, null, bottomMenu, null);
        BorderPane.setAlignment(borderPane.getBottom(), Pos.BOTTOM_CENTER);
        borderPane.autosize();
        
        scene1 = new Scene(borderPane);
        scene1.getAccelerators().put(kcombo_ControlV, new Runnable(){
            @Override public void run(){
                setCurrentImageToClipboardContents();
            }
        });
        scene1.getAccelerators().put(kcombo_Enter, new Runnable(){
           @Override public void run(){
               addToDeck();
           } 
        });
        window.setScene(scene1);
        scene1.getStylesheets().add(TTS_DeckBuilder.class.getResource("Dark.css").toExternalForm());
        window.setTitle("TTS Deck Builder");
        window.show();
    }
    
    //***   Decrement currentIndex, adjust displayed cards [NOT IMPLEMENTED] ***
    public void cycleBackwards(){
        
    }
    
    //***   Increment currentIndex, adjust displayed cards [NOT IMPLEMENTED] ***
    public void cycleForwards(){
        
    }
    
    /**
     * Method takes image from the clipboard. If the object is not an image, then no image will be taken and the result will be NULL.
     * If NULL, set the alert text to tell user there is no image in the clipboard.
     * If image is there, copy it into the ArrayList and disable Settings button.
     */
    public void setCurrentImageToClipboardContents(){
        System.out.println("Grabbing image...");
            cardImage = getImageFromClipboard();
            System.out.println("Got image.");
            
            if(cardImage != null){
                ivCurrent.setImage(cardImage);
                makeNodeActive(button_AddToDeck);
                makeNodeInactive(statusMessage);
                statusMessage.setVisible(false);
            }
            
            else{
                makeNodeInactive(button_AddToDeck);
                makeNodeActive(statusMessage);
                statusMessage.setVisible(true);
                statusMessage.setText("No image in clipboard.");
            }
    }
    /**
     * Take the image temporarily stored in the 
     */
    public void addToDeck(){
        if(cardImage != null){
            addImage(cardImage);
            System.out.println("Added image to list");
            ivPrevious2.setImage(ivPrevious.getImage());
            ivPrevious.setImage(ivCurrent.getImage());
            ivCurrent.setImage(null);
            cardCounter.setText("Cards Added: " + cardDeck.size());
            cardImage = null;
            ++currentIndex;
            if(currentIndex == MAXIMUM_NUMBER_OF_CARDS){
                makeNodeInactive(button_AddToDeck);
                makeNodeInactive(button_GrabClipboard);
                statusMessage.setText("Maximum cards added. No further cards may be added.");
                statusMessage.setVisible(true);
            }
            if(!button_Settings.isDisable()){
                makeNodeInactive(button_Settings);
            }
            if(button_Export.isDisable()){
            makeNodeActive(button_Export);
            }
        }
        else{
            statusMessage.setVisible(true);
            statusMessage.setText("No image to add.");
        }
    }
    
    //***   Show the settings window    ***
    public void changeSettings(){
        OptionsMenu.display(controller);
        updateValues();
        System.out.println("Values updated.");
    }
    
    //***   Disable a passed node which is currently enabled    ***
    public void makeNodeInactive(javafx.scene.Node n){
        if(!n.isDisable()){
            n.setDisable(true);
        }
    }
    
    //***   Enable a passed node which is currently disabled   ***
    public void makeNodeActive(javafx.scene.Node n){
        if(n.isDisable()){
            n.setDisable(false);
        }
    }
    
    //***   Add the current image to the list by taking a Snapshot of the imageView   ***
    public void addImage(Image i){
//        Image imageToAdd = scaleImage(i, cardWidth, cardHeight);
//        cardDeck.add(imageToAdd);
        cardDeck.addCard(new Card(i));
    }
    
    //***   Handle unknown ActionEvent events   ***
    @Override
    public void handle(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //***   Get image data from the clipboard.  ***
    public Image getImageFromClipboard(){
        return Clipboard.getSystemClipboard().getImage();
    }
    
    private void closeProgram(){
        System.out.println("Program exited.");
        window.close();
    }
    
    public void exportDeck(Text statusMessage){
        String exportFileName = "Error Exporting";
        
        cardDeck.resizeCards(cardWidth, cardHeight);
        
        Canvas deckCanvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext temporaryCanvas = deckCanvas.getGraphicsContext2D();
        temporaryCanvas.setFill(Color.BLACK);
        temporaryCanvas.fillRect(0, 0, deckCanvas.getWidth(), deckCanvas.getHeight());

        int xPadding = cardPadding;
        int yPadding = cardPadding;
        
        //Maximum of 70 cards per deck-image.
        //Maybe implement custom number.
        for(int i = 1; i < (cardDeck.size() + 1) && i < MAXIMUM_NUMBER_OF_CARDS; ++i){
            
            temporaryCanvas.drawImage((Image)cardDeck.getCardAtIndex(i - 1).getImage(), xPadding, yPadding);

            xPadding+= totalCardWidthWithPadding();
            
            //Set new row every 9 cards.
            if(i%10 == 0 && i > 0){
                xPadding = cardPadding;
                yPadding += totalCardHeightWithPadding();
            }
        }
        
        try{
            WritableImage exportImage = new WritableImage(canvasWidth, canvasHeight);
            WritableImage snapshot = deckCanvas.snapshot(new SnapshotParameters(), exportImage);
            
            String nameSnippet = cardDeck.getDeckName() == null ? "" + new Date().getTime() : cardDeck.getDeckName();
            exportFileName = "TTS_Deck_" + nameSnippet + "_" + cardDeck.size() + "cards.png";
    
            
            File output = new File(exportFileName);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", output);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        statusMessage.setText("Exported to " + exportFileName);
        statusMessage.setVisible(true);
    }
    
    public int totalCardWidthWithPadding(){
        return (cardWidth + (2 * cardPadding));
    }
    
    public int totalCardHeightWithPadding(){
        return (cardHeight + (2 * cardPadding));
    }
    
    /**
     * Update the local values for the card and canvas from the Controller class.
     */
    public void updateValues(){
        canvasWidth = controller.getCanvasWidth();
        canvasHeight = controller.getCanvasHeight();
        cardWidth = controller.getCardWidth();
        cardHeight = controller.getCardHeight();
        cardPadding = controller.getCardPadding();
    }    
}
