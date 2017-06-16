/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tts_deckbuilder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public static void display(){
        Stage window = new Stage();
        window.getIcons().add(new Image(OptionsMenu.class.getResourceAsStream("icon.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings");
        window.setMinWidth(250);
        
        VBox topMenu = new VBox();
        VBox centerContent = new VBox();
        VBox bottomMenu = new VBox();
        
        topMenu.autosize();
        topMenu.setAlignment(Pos.TOP_CENTER);
        
        HBox menuButtons = new HBox();
        menuButtons.autosize();
        menuButtons.setAlignment(Pos.BOTTOM_CENTER);
        
        Text optionsText = new Text("Settings can not be changed after you add cards!");
        optionsText.setFill(Color.web("#FEFEFE"));
        
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
        });
        
        menuButtons.getChildren().addAll(button_Apply, button_RestoreDefaults, button_Cancel);
        
        topMenu.getChildren().addAll(optionsText);
        //centerContent.getChildren().addAll(optionsText);
        bottomMenu.getChildren().addAll(menuButtons);
        
        //Center, Top, Right, Bottom, Left
        BorderPane borderpane = new BorderPane(centerContent, topMenu, null, bottomMenu, null);
        
        Scene scene = new Scene(borderpane);
        window.setScene(scene);
        scene.getStylesheets().add(TTS_DeckBuilder.class.getResource("Dark.css").toExternalForm());
        window.showAndWait();
    }
    
    public static void applySettings(){
        System.out.println("Settings>Apply clicked.");
    }
    
    public static void restoreDefaults(){
        System.out.println("Settings>Restore Defaults clicked.");
    }
}
