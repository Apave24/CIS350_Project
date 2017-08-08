package edu.gvsu.cis350;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 * @author evankiel
 *
 */
public class Main extends Application {
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage primaryStage) {
		try {
			
			Parent root =  FXMLLoader.load(getClass()
						.getResource("GameGui.fxml"));
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.setTitle("Six Degrees of Separation");
		    primaryStage.show();
		    
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args Unused command line input
	 */
	public static void main(final String[] args) {
		launch(args);
	}
	

}
