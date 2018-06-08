package impiccato9; 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class ImpGui extends Application {
	
 private static final double WIDTH = 750.0;
 private static final double HEIGHT = 350.0;
 private static final double PADDING = 10.0; 
 
 private Imp hh = null; 
 private String message = "";
  private String digita = "";
  
 AnchorPane root = new AnchorPane(); 
 private Button newGame = new Button("New Game");
 private Button info = new Button("Info"); 
 private TextArea area = new TextArea();
 private TextField text = new TextField();
 private TextField messageField = new TextField();
 private TextField lettereDigitate = new TextField();
 private TextField turno = new TextField(); 
 private Alert alert = new Alert(AlertType.WARNING);

 
   @Override
   public void start(Stage primaryStage) throws Exception {
	   
	   final ComboBox <String>languages = new ComboBox<>();
	   languages.getItems().addAll("DEU","ITA","ENG"); 
	   languages.setValue("DEU");
       
       text.setAlignment(Pos.CENTER_RIGHT); 
       lettereDigitate.setAlignment(Pos.CENTER);
       messageField.setAlignment(Pos.CENTER);
       area.setWrapText(true);
       
       										text.setStyle("-fx-font: 40px Tahoma; "); 
       area.setStyle("-fx-font: 35px Tahoma ; "); 
       messageField.setStyle("-fx-font: 30px Tahoma; ");
       lettereDigitate.setStyle("-fx-font: 23px Tahoma; "); 
       turno.setStyle("-fx-font: 20px Tahoma; ");
       newGame.setStyle("-fx-font: 15px Tahoma; ");
       info.setStyle("-fx-font: 20px Tahoma; ");
       languages.setStyle("-fx-font: 15px Tahoma; ");
       
       messageField.setEditable(false);
       text.setEditable(false);
       area.setEditable(false);
       lettereDigitate.setEditable(false);	
       turno.setEditable(false);
		
       
       text.setOnAction(new EventHandler<ActionEvent>() { 
		@Override
		public void handle(ActionEvent event) { 
			lettereDigitate.setText(""); 
			
			 digita = text.getText().toUpperCase() ;
			
			
			if(digita.length() >0) { 
					 digita = digita.substring(digita.length()-3,digita.length()-2); 
					if(hh.validInput(digita) && !hh.enteredLetter(digita)) {  
					   if( hh.isThere(digita)) {
						   hh.updateShow(digita);
					   }
					   else {										
						   hh.increaseTurn();							
					   }  
					}
				
				 
				area.setText(hh.showText()); 
				lettereDigitate.setText(printEnteredLetter());
				turno.setText("Rimasti: " + (hh.getTentativi()-hh.getTurno()));
				text.setText("");
				
				 
				if(hh.win()) {
					message = "HAI VINTO";
					messageField.setText(message);
					text.setEditable(false);
				} 
				else if(hh.lose()) { 
					messageField.setText("HAI PERSO");
					alert.setTitle("Game Over");
					alert.setHeaderText("Das war das Sprichwort");
					alert.setContentText( hh.getText().toUpperCase());
					alert.showAndWait();
					text.setEditable(false);
				}
				else if(!hh.validInput(digita)) { 
					messageField.setText("inserire valido" );
				} 
				else if(!hh.enteredLetter(digita)) { 
					messageField.setText( "gia la");
				}
				else { 
					messageField.setText( "Inserisci una lettera");
				}
				
				
			}
		}
     });
       
       //add two spaces in the input text field
       text.setOnKeyPressed(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent event) {
        	   text.appendText("  "); 
        	    
            }
       });
       
   
       
       newGame.setOnAction(new EventHandler<ActionEvent>() {
    	   @Override
   		public void handle(ActionEvent event) { 
    		  text.setEditable(true);
    		  messageField.setText("Inserisci una lettera");
    		  lettereDigitate.setText(""); 
    		   hh = new Imp();
    		   hh.setTurno(1);
    		   hh.setFile(languages.getValue());
    		   hh.resetEntered();
    		   
    		   try {
				hh.createProverb();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
    		  
    		 hh.initializeShowArray(hh.getText());  									
    		 area.setText(hh.showText());
    	   }
       }); 
       
       
       
       info.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
           	Image ranking;
           	ImageView rView = new ImageView();
           	
				try {
					ranking = new Image   (new FileInputStream("info.pdf"));
					 rView = new ImageView(ranking)  ;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
   			
               StackPane wertung = new StackPane();
               wertung.getChildren().add(rView);

               Scene secondScene = new Scene(wertung, 230, 100);

               Stage wertungWindow = new Stage();       
               wertungWindow.setTitle("Info");
               wertungWindow.setScene(secondScene);
               wertungWindow.setHeight(570);
               wertungWindow.setWidth(510);
               wertungWindow.setX(  200);
               wertungWindow.setY(  100);
               wertungWindow.setResizable(false);
               wertungWindow.show();
               
           }
       });
       
       
       
       
       
       
       
       
       
					       
       AnchorPane.setTopAnchor(newGame, PADDING); 
       AnchorPane.setRightAnchor(newGame, WIDTH - 2*PADDING - 80);
       AnchorPane.setLeftAnchor(newGame, PADDING);
       AnchorPane.setBottomAnchor(newGame, HEIGHT -50);			       
					      
       AnchorPane.setTopAnchor(languages, PADDING);
       AnchorPane.setRightAnchor(languages, PADDING);
      // AnchorPane.setLeftAnchor(languages, WIDTH - 100);
       AnchorPane.setLeftAnchor(languages, WIDTH - 2*PADDING - 80);
       AnchorPane.setBottomAnchor(languages, HEIGHT -50);	       
			 	       
       AnchorPane.setTopAnchor(messageField, PADDING );
       AnchorPane.setLeftAnchor(messageField, 4*PADDING + 80);
       AnchorPane.setBottomAnchor(messageField, HEIGHT -50);
       AnchorPane.setRightAnchor(messageField, 4*PADDING + 80);
       
       AnchorPane.setTopAnchor(area, 2 * PADDING + 50);
       AnchorPane.setLeftAnchor(area, PADDING);
       AnchorPane.setRightAnchor(area, PADDING);
       AnchorPane.setBottomAnchor(area,80.0);
       
       AnchorPane.setTopAnchor(text, 200.0);
       AnchorPane.setLeftAnchor(text, WIDTH/2 -30);
       AnchorPane.setRightAnchor(text, WIDTH/2 -30);
       AnchorPane.setBottomAnchor(text, 80.0);
       
       AnchorPane.setTopAnchor(turno, 3* PADDING + 260.0 );
       AnchorPane.setLeftAnchor(turno, PADDING);
       AnchorPane.setRightAnchor(turno, WIDTH - 2*PADDING - 110);
       AnchorPane.setBottomAnchor(turno, PADDING);
       
       AnchorPane.setTopAnchor(info,  3* PADDING + 260.0);
       AnchorPane.setLeftAnchor(info, WIDTH - 2*PADDING - 80);
       AnchorPane.setRightAnchor(info, PADDING);
       AnchorPane.setBottomAnchor(info, PADDING);
       
       AnchorPane.setTopAnchor(lettereDigitate,   3* PADDING + 260.0);
       AnchorPane.setLeftAnchor(lettereDigitate, 4*PADDING + 110);
       AnchorPane.setBottomAnchor(lettereDigitate, PADDING);
       AnchorPane.setRightAnchor(lettereDigitate, 4*PADDING + 80);
  
       
       root.getChildren().addAll (newGame, info, languages, lettereDigitate,messageField, area, text, turno);
 
       Scene scene = new Scene(root, WIDTH, HEIGHT); 
        
       primaryStage.setTitle("Game");
       primaryStage.setScene(scene);
       primaryStage.setResizable(false);
       primaryStage.show();
   }
   
   
   public String printEnteredLetter() {
		String allLetters = "";
		ArrayList<String> enteredLetters = hh.getEnterd();
		Collections.sort(enteredLetters);
		for(String tmp : enteredLetters)
		allLetters += tmp + " "; 
		return allLetters ;
	}
 
   public static void main(String[] args) {
       launch(args);
   }
 
}