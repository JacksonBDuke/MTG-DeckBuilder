/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tts_deckbuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;
/**
 *
 * @author Jackson
 */
public class OptionsMenu {
    private static Controller theController;
    static int canvasWidth, canvasHeight, cardWidth, cardHeight, cardPadding;
    private static TextField tf_CanvasWidth;
    private static TextField tf_CanvasHeight;
    private static TextField tf_CardWidth;
    private static TextField tf_CardHeight;
    private static TextField tf_CardPadding;
    
    private static final Text TEXT_OPTIONS = new Text("Settings can not be changed after you add cards!");
    private static final Text TEXT_CANVAS_WIDTH = new Text("Canvas Width: ");
    private static final Text TEXT_CANVAS_HEIGHT = new Text("Canvas Height: ");
    private static final Text TEXT_CARD_WIDTH = new Text("Card Width: ");
    private static final Text TEXT_CARD_HEIGHT = new Text("Card Height: ");
    private static final Text TEXT_CARD_PADDING = new Text("Card Padding: ");
    
    public static void display(Controller c){
        theController = c;
        
        canvasWidth = theController.getCanvasWidth();
        canvasHeight = theController.getCanvasHeight();
        cardWidth = theController.getCardWidth();
        cardHeight = theController.getCardHeight();
        cardPadding = theController.getCardPadding();
        
        TEXT_OPTIONS.setFill(Color.web("#FEFEFE"));
        TEXT_CANVAS_WIDTH.setFill(Color.web("#FEFEFE"));
        TEXT_CANVAS_HEIGHT.setFill(Color.web("#FEFEFE"));
        TEXT_CARD_WIDTH.setFill(Color.web("#FEFEFE"));
        TEXT_CARD_HEIGHT.setFill(Color.web("#FEFEFE"));
        TEXT_CARD_PADDING.setFill(Color.web("#FEFEFE"));
        
        tf_CanvasWidth = new TextField("" + canvasWidth);
        tf_CanvasHeight = new TextField("" + canvasHeight);
        tf_CardWidth = new TextField("" + cardWidth);
        tf_CardHeight = new TextField("" + cardHeight);
        tf_CardPadding = new TextField("" + cardPadding);
        
        tf_CanvasWidth.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("\\d*")){
                tf_CanvasWidth.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else{
                int temp = Integer.parseInt(newValue);
                canvasWidth = temp;
                
                refreshDisplayedValues();
            }
        });
        tf_CanvasHeight.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("\\d*")){
                tf_CanvasHeight.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else{
                int temp = Integer.parseInt(newValue);
                canvasHeight = temp;
                
                refreshDisplayedValues();
            }
        });
        tf_CardHeight.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("\\d*")){
                tf_CardHeight.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else{
                int temp = Integer.parseInt(newValue);
                cardHeight = temp;
                canvasHeight = (cardHeight * 7) + (cardPadding * 7);
                
                refreshDisplayedValues();
            }
        });
        tf_CardWidth.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("\\d*")){
                tf_CardWidth.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else{
                int temp = Integer.parseInt(newValue);
                cardWidth = temp;
                canvasWidth = (cardWidth * 10) + (cardPadding * 10);
                
                refreshDisplayedValues();
            }
        });
        tf_CardPadding.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("\\d*")){
                tf_CardPadding.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else{
                int temp = Integer.parseInt(newValue);
                cardPadding = temp;
                canvasWidth = (cardWidth * 10) + (cardPadding * 10);
                canvasHeight = (cardHeight * 7) + (cardPadding * 7);
                
                refreshDisplayedValues();
            }
        });
        
        Stage window = new Stage();
        window.getIcons().add(new Image(OptionsMenu.class.getResourceAsStream("icon.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings");
        window.setMinWidth(250);
        
        /*
        HBox centerContent = new HBox();
        centerContent.autosize();
        centerContent.setAlignment(Pos.CENTER);
        */
        
        VBox topMenu = new VBox();
        topMenu.autosize();
        topMenu.setAlignment(Pos.CENTER);
        VBox rightMenu = new VBox();
        rightMenu.autosize();
        rightMenu.setAlignment(Pos.CENTER);
        VBox centerContent = new VBox();
        centerContent.autosize();
        centerContent.setAlignment(Pos.CENTER);
        
        HBox bottomMenu = new HBox();
        bottomMenu.autosize();
        bottomMenu.setAlignment(Pos.CENTER);
        
        //VBox menuButtons = new VBox();
        //menuButtons.autosize();
        //menuButtons.setAlignment(Pos.CENTER);
        
        HBox box1 = new HBox();
        box1.autosize();
        box1.setAlignment(Pos.CENTER_RIGHT);
        HBox box2 = new HBox();
        box2.autosize();
        box2.setAlignment(Pos.CENTER_RIGHT);
        HBox box3 = new HBox();
        box3.autosize();
        box3.setAlignment(Pos.CENTER_RIGHT);
        HBox box4 = new HBox();
        box4.autosize();
        box4.setAlignment(Pos.CENTER_RIGHT);
        HBox box5 = new HBox();
        box5.autosize();
        box5.setAlignment(Pos.CENTER_RIGHT);
        
        box1.getChildren().addAll(TEXT_CANVAS_WIDTH, tf_CanvasWidth);
        box2.getChildren().addAll(TEXT_CANVAS_HEIGHT, tf_CanvasHeight);
        box3.getChildren().addAll(TEXT_CARD_WIDTH, tf_CardWidth);
        box4.getChildren().addAll(TEXT_CARD_HEIGHT, tf_CardHeight);
        box5.getChildren().addAll(TEXT_CARD_PADDING, tf_CardPadding);
        
        Button button_Apply = new Button("Apply");
        Button button_Cancel = new Button("Cancel");
        Button button_RestoreDefaults = new Button("Restore Defaults");
        
        button_Cancel.setOnAction(e->{
            System.out.println("Settings>Cancel clicked.");
            window.close();
        });
        
        button_Apply.setOnAction(e->{
            applySettings();
        });
        
        button_RestoreDefaults.setOnAction(e->{
            restoreDefaults();
            
            canvasWidth = theController.getCanvasWidth();
            canvasHeight = theController.getCanvasHeight();
            cardWidth = theController.getCardWidth();
            cardHeight = theController.getCardHeight();
            cardPadding = theController.getCardPadding();
            
            refreshDisplayedValues();
        });
        
        
        
        topMenu.getChildren().addAll(TEXT_OPTIONS);
        //leftMenu.getChildren().addAll(CanvasWidthLabel, CanvasHeightLabel, CardWidthLabel, CardHeightLabel, CardPaddingLabel);
        rightMenu.getChildren().addAll(button_Apply, button_RestoreDefaults, button_Cancel);
        //centerContent.getChildren().addAll(tf_CanvasHeight, tf_CanvasWidth, tf_CardHeight, tf_CardWidth, tf_CardPadding);
        centerContent.getChildren().addAll(box1, box2, box3, box4, box5);
        bottomMenu.getChildren().addAll(button_Apply, button_RestoreDefaults, button_Cancel);
        
        //Center, Top, Right, Bottom, Left
        BorderPane borderpane = new BorderPane(centerContent, topMenu, null, bottomMenu, null);
        
        Scene scene = new Scene(borderpane);
        window.setScene(scene);
        scene.getStylesheets().add(TTS_DeckBuilder.class.getResource("Dark.css").toExternalForm());
        window.showAndWait();
    }
    
    public static void applySettings(){
        System.out.println("Settings>Apply clicked.");
        theController.setCanvasHeight(canvasHeight);
        theController.setCanvasWidth(canvasWidth);
        theController.setCardHeight(cardHeight);
        theController.setCardWidth(cardWidth);
        theController.setCardPadding(cardPadding);
    }
    
    public static void restoreDefaults(){
        System.out.println("Settings>Restore Defaults clicked.");
        theController.restoreDefaults();
    }
    
    public static void refreshDisplayedValues(){
        tf_CanvasWidth.setText("" + canvasWidth);
        tf_CanvasHeight.setText("" + canvasHeight);
        tf_CardWidth.setText("" + cardWidth);
        tf_CardHeight.setText("" + cardHeight);
        tf_CardPadding.setText("" + cardPadding);
    }
}
