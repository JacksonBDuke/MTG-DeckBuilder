/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtg_tts_deckbuilder;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Jackson
 */
public class MTG_TTS_DeckBuilder extends Application implements EventHandler<ActionEvent> {
    Image deckImage;
    Image cardImage;
    Image previousImage;
    Image previousImage2;
    ImageView ivCurrent;
    ImageView ivPrevious;
    ImageView ivPrevious2;
    ArrayList<Image> imageList = new ArrayList();
    Button button;
    Stage window;
    Scene scene1;
    
    //File imageTemplateFile = new File(MTG_TTS_DeckBuilder.class.getResource("template.jpg").toExternalForm());
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //makeImage();
        
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //window.setOnCloseRequest(e -> closeProgram());
        window = primaryStage;
        
        cardImage = previousImage = previousImage2 = null;
        
        //scene1 = new Scene(layout1, 312, 545);
        
        ivCurrent = new ImageView();
        ivPrevious = new ImageView();
        ivPrevious2 = new ImageView();
        Text cardCounter = new Text("No cards added");
        cardCounter.setFill(Color.web("#FEFEFE"));
        
        ivCurrent.setFitWidth(312);
        ivCurrent.setFitHeight(445);
        ivCurrent.setPreserveRatio(false);
        ivCurrent.setSmooth(true);
        ivCurrent.setCache(true);
        
        ivPrevious.setFitWidth(312);
        ivPrevious.setFitHeight(445);
        //ivPrevious.setPreserveRatio(false);
        //ivPrevious.setFitWidth(234);
        //ivPrevious.setFitHeight(367);
        ivPrevious.setPreserveRatio(true);
        ivPrevious.setSmooth(true);
        ivPrevious.setCache(true);
        
        ivPrevious2.setFitWidth(312);
        ivPrevious2.setFitHeight(445);
        //ivPrevious2.setFitWidth(156);
        //ivPrevious2.setFitHeight(289);
        ivPrevious2.setPreserveRatio(true);
        ivPrevious2.setSmooth(true);
        ivPrevious2.setCache(true);
        
        //Label label1 = new Label("MTG TTS Deck Image Generator");
        
        Button button_GrabClipboard = new Button("Grab from Clipboard");
        Button button_AddToDeck = new Button("Add Card to Deck");
        button_AddToDeck.setDisable(true);
        Button button_Export = new Button("Export");
        button_Export.setDisable(true);
        Button button_PreviousCard = new Button("Previous Card");
        Button button_NextCard = new Button("Next Card");
        
        button_GrabClipboard.setOnAction(e -> {
            System.out.println("Grabbing image...");
            cardImage = grabImage();
            System.out.println("Got image.");
            
            if(cardImage != null){
                ivCurrent.setImage(cardImage);
                makeButtonActive(button_AddToDeck);
            }
            
            else{
                makeButtonInactive(button_AddToDeck);
            }
            
        });
        
        
        button_AddToDeck.setOnAction(e ->{
            if(cardImage != null){
                System.out.println("Added image to list");
                imageList.add(cardImage);
                ivPrevious2.setImage(ivPrevious.getImage());
                ivPrevious.setImage(ivCurrent.getImage());
                ivCurrent.setImage(null);
                cardCounter.setText("Cards Added: " + imageList.size());
                cardImage = null;
            }
            if(button_Export.isDisable()){
                makeButtonActive(button_Export);
            }
        });
        
        
        button_Export.setOnAction(e ->{
           System.out.println("Exporting to image...");
           exportImage();
           System.out.println("Done.");
        });
        
        button_PreviousCard.setOnAction(e ->{
            
        });
        
        button_NextCard.setOnAction(e ->{
            
        });
        
        HBox topMenu = new HBox();
        HBox bottomMenu = new HBox();
        HBox centerContent = new HBox();
        
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
        
        currentCardBox.getChildren().addAll(ivCurrent, currentText);
        previousCardBox.getChildren().addAll(ivPrevious, lastText);
        previousCardBox2.getChildren().addAll(ivPrevious2, previousText);
        
        VBox leftMenu = new VBox();
        VBox rightMenu = new VBox();
        
        topMenu.getChildren().addAll(cardCounter);
        bottomMenu.getChildren().addAll(button_GrabClipboard, button_AddToDeck, button_Export);
        leftMenu.getChildren().addAll(button_PreviousCard);
        rightMenu.getChildren().addAll(button_NextCard);
        
        //centerContent.getChildren().addAll(ivPrevious2, ivPrevious, ivCurrent);
        centerContent.getChildren().addAll(previousCardBox2, previousCardBox, currentCardBox);
        
        //cardCounter.setText("No cards added");
        
        //BorderPane borderPane = new BorderPane( centerContent, topMenu, rightMenu, bottomMenu, leftMenu);
        topMenu.setAlignment(Pos.TOP_CENTER);
        bottomMenu.setAlignment(Pos.BOTTOM_CENTER);
        BorderPane borderPane = new BorderPane( centerContent, topMenu, null, bottomMenu, null);
        //borderPane.setAlignment(borderPane.getCenter(), Pos.CENTER);
        /*
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);
        borderPane.setRight(rightMenu);
        borderPane.setBottom(bottomMenu);
        borderPane.setCenter(centerContent);
        */
        borderPane.setAlignment(borderPane.getBottom(), Pos.BOTTOM_CENTER);
        //borderPane.setAlignment(bottomMenu, Pos.BOTTOM_CENTER);
        
        
        borderPane.autosize();
        
        scene1 = new Scene(borderPane);
        
        window.setScene(scene1);
        scene1.getStylesheets().add(MTG_TTS_DeckBuilder.class.getResource("Dark.css").toExternalForm());
        window.setTitle("MTG TTS Deck Image Generator");
        window.show();
        
    }
    
    public void makeButtonInactive(Button b){
        if(!b.isDisable()){
            b.setDisable(true);
        }
    }
    
    public void makeButtonActive(Button b){
        if(b.isDisable()){
            b.setDisable(false);
        }
    }
    
    @Override
    public void handle(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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
    
    public void exportImage(){
        
        Canvas deckCanvas = new Canvas(3320, 3255);
        GraphicsContext test = deckCanvas.getGraphicsContext2D();
        test.setFill(Color.BLACK);
        test.fillRect(0, 0, deckCanvas.getWidth(), deckCanvas.getHeight());
        //test.rect(0, 0, 3320, 3255);
        
        
        //Graphics2D deckMaker = imageTemplate.createGraphics();
        
        int x = 10;
        int y = 10;
        for(int i = 0; i < imageList.size() && i < 70; ++i){
            
            test.drawImage((Image)imageList.get(i), x, y);
            
            x+=332;
            
            if(i%9 == 0 && i > 0){
                //BufferedImage tempImage = (BufferedImage)imageList.get(i);
                x = 10;
                y += 465;
                //deckMaker.drawImage(imageList.get(i), x, y);   
            }
        }
        
        try{
            SnapshotParameters parameters = new SnapshotParameters();
            WritableImage exportImage = new WritableImage(3320, 3255);
            WritableImage snapshot = deckCanvas.snapshot(new SnapshotParameters(), exportImage);

            File output = new File("deck" + new Date().getTime() + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", output);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        
        //canvas.snapshot
    }
    public void handle(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.V) {
            cardImage = grabImage();
            ivCurrent.setImage(cardImage);
        }
    }
}