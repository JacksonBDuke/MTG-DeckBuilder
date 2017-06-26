/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tts_deckbuilder;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;

/**
 *
 * @author Jackson
 */
public class TTS_DeckBuilder extends Application implements EventHandler<ActionEvent> {
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double DPI = Screen.getPrimary().getDpi();
    
    Image deckImage;
    Image cardImage;
    Image previousImage;
    Image previousImage2;
    ImageView ivCurrent;
    ImageView ivPrevious;
    ImageView ivPrevious2;
    TextField tvDeckName;
    ArrayList<Image> imageList = new ArrayList();
    Button button;
    Stage window;
    Scene scene1;
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
        
        Text cardCounter = new Text("No cards added");
        Text statusMessage = new Text();
        
        cardCounter.setFill(Color.web("#FEFEFE"));
        statusMessage.setFill(Color.web("#FEFEFE"));
        
        //ivCurrent.setFitWidth(312);
        //ivCurrent.setFitHeight(445);
        ivCurrent.setFitWidth(cardWidth);
        ivCurrent.setFitHeight(cardHeight);
        ivCurrent.setPreserveRatio(false);
        ivCurrent.setSmooth(true);
        ivCurrent.setCache(true);
        
        //ivPrevious.setFitWidth(312);
        //ivPrevious.setFitHeight(445);
        ivPrevious.setFitWidth(cardWidth);
        ivPrevious.setFitHeight(cardHeight);

        ivPrevious.setPreserveRatio(false);
        ivPrevious.setSmooth(true);
        ivPrevious.setCache(true);
        
        //ivPrevious2.setFitWidth(312);
        //ivPrevious2.setFitHeight(445);
        ivPrevious2.setFitWidth(cardWidth);
        ivPrevious2.setFitHeight(cardHeight);
        
        //ivPrevious2.setFitWidth(156);
        //ivPrevious2.setFitHeight(289);
        ivPrevious2.setPreserveRatio(false);
        ivPrevious2.setSmooth(true);
        ivPrevious2.setCache(true);
        
        Button button_GrabClipboard = new Button("Grab from Clipboard");
        
        Button button_AddToDeck = new Button("Add Card to Deck");
        button_AddToDeck.setDisable(true);
        
        Button button_Export = new Button("Export");
        button_Export.setDisable(true);
        
        Button button_PreviousCard = new Button("Previous Card");
        button_PreviousCard.setDisable(true);
        
        Button button_NextCard = new Button("Next Card");
        button_NextCard.setDisable(true);
        
        Button button_Settings = new Button("Settings");
        
        button_GrabClipboard.setOnAction(e -> {
            System.out.println("Grab from Clipboard clicked.");
            grabCard(button_AddToDeck, statusMessage);
        });
        
        
        button_AddToDeck.setOnAction(e ->{
            System.out.println("Add to Deck clicked.");
            //public void addToDeck(Text counter, javafx.scene.Node buttonAdd, javafx.scene.Node buttonExport, javafx.scene.Node buttonGrab, Text status){
            addToDeck(cardCounter, button_Settings, button_AddToDeck, button_Export, button_GrabClipboard, statusMessage);
        });
        
        
        button_Export.setOnAction(e ->{
           System.out.println("Export clicked.");
           System.out.println("Exporting to image...");
           exportImage(statusMessage);
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
        
        
        Text currentText = new Text("Current Card");
        currentText.setFill(Color.web("#FEFEFE"));
        Text lastText = new Text("Last Added");
        lastText.setFill(Color.web("#FEFEFE"));
        Text previousText = new Text("Previous Added");
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

        borderPane.setAlignment(borderPane.getBottom(), Pos.BOTTOM_CENTER);

        
        borderPane.autosize();
        
        scene1 = new Scene(borderPane);
        
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
    
    public void grabCard(javafx.scene.Node n, Text m){
        System.out.println("Grabbing image...");
            cardImage = grabImage();
            System.out.println("Got image.");
            
            if(cardImage != null){
                ivCurrent.setImage(cardImage);
                makeNodeActive(n);
                makeNodeInactive(m);
                m.setVisible(false);
            }
            
            else{
                makeNodeInactive(n);
                makeNodeActive(m);
                m.setVisible(true);
                m.setText("No image in clipboard.");
            }
    }
    
    public void addToDeck(Text counter, javafx.scene.Node buttonSettings, javafx.scene.Node buttonAdd, javafx.scene.Node buttonExport, javafx.scene.Node buttonGrab, Text status){
        if(cardImage != null){
            addImage(ivCurrent);
            System.out.println("Added image to list");
            ivPrevious2.setImage(ivPrevious.getImage());
            ivPrevious.setImage(ivCurrent.getImage());
            ivCurrent.setImage(null);
            counter.setText("Cards Added: " + imageList.size());
            cardImage = null;
            ++currentIndex;
            if(currentIndex == 70){
                makeNodeInactive(buttonAdd);
                makeNodeInactive(buttonGrab);
                status.setText("Maximum cards added. No further cards may be added.");
                status.setVisible(true);
            }
            if(!buttonSettings.isDisable()){
                makeNodeInactive(buttonSettings);
            }
        }
        if(buttonExport.isDisable()){
            makeNodeActive(buttonExport);
        }
    }
    
    //***   Show the settings window    ***
    public void changeSettings(){
        OptionsMenu.display();
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
    
    //***   Add the current image to the list   ***
    public void addImage(javafx.scene.Node n){
        WritableImage snapshotImage = n.snapshot(new SnapshotParameters(), null);
        imageList.add(snapshotImage);
    }
    
    //***   Handle unknown ActionEvent events   ***
    @Override
    public void handle(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //***   Get image data from the clipboard.  ***
    public Image grabImage(){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        
        Image image = null;
        image = clipboard.getImage();

        return image;
    }
    
    private void closeProgram(){
        System.out.println("Program exited.");
        /*
        boolean answer = ConfirmBox.display("Title", "Are you sure you want to close the program?");
        
        if(answer){
            window.close();
        }
        */
        window.close();
    }
    
    //Function to export image.
    public void exportImage(Text t){
        
        String exportFileName = "Error Exporting";
        
        //Canvas deckCanvas = new Canvas(3320, 3255);
        Canvas deckCanvas = new Canvas(canvasWidth, canvasHeight);
        
        GraphicsContext test = deckCanvas.getGraphicsContext2D();
        test.setFill(Color.BLACK);
        test.fillRect(0, 0, deckCanvas.getWidth(), deckCanvas.getHeight());
        
        //int x = 10;
        //int y = 10;
        int x = cardPadding;
        int y = cardPadding;
        
        //Maximum of 70 cards per deck-image.
        for(int i = 1; i < (imageList.size() + 1) && i < 70; ++i){
            
            test.drawImage((Image)imageList.get(i - 1), x, y);
            
            //x+=(cardWidth + (2*10));
            x+=(cardWidth + (2 * cardPadding));
            
            //Set new row every 9 cards.
            if(i%10 == 0 && i > 0){
                //BufferedImage tempImage = (BufferedImage)imageList.get(i);
                //x = 10;
                //y += 465;
                x = cardPadding;
                y += (cardHeight + (2 * cardPadding));
                //deckMaker.drawImage(imageList.get(i), x, y);   
            }
        }
        
        try{
            //WritableImage exportImage = new WritableImage(3320, 3255);
            WritableImage exportImage = new WritableImage(canvasWidth, canvasHeight);
            WritableImage snapshot = deckCanvas.snapshot(new SnapshotParameters(), exportImage);
            
            exportFileName = "TTS_Deck_" + new Date().getTime() + "_" + imageList.size() + "cards";
            String deckName = tvDeckName.getText();
            deckName = deckName.replaceAll(" ", "");
            deckName = deckName.replaceAll("\"", "\\\"");
            deckName = deckName.trim();
            if(!deckName.isEmpty()){
                exportFileName = deckName + "_" + imageList.size() + "cards";
            }

            File output = new File(exportFileName + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", output);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        t.setText("Exported to " + exportFileName + ".png");
        t.setVisible(true);
        
    }
    public void handle(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.V) {
            cardImage = grabImage();
            ivCurrent.setImage(cardImage);
        }
    }
}