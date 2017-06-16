/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tts_deckbuilder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.*;
/**
 *
 * @author Jackson
 */
public class OptionsMenu {
    public static void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings");
        window.setMinWidth(250);
        
        
        VBox optionsContents = new VBox();
        HBox bottomMenu = new HBox();
        
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
        
        bottomMenu.getChildren().addAll(button_Apply, button_RestoreDefaults, button_Cancel);
        optionsContents.setAlignment(Pos.CENTER);
        
        optionsContents.getChildren().addAll(bottomMenu);
        optionsContents.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(optionsContents);
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
