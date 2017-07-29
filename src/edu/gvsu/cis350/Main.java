package edu.gvsu.cis350;
	
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbPeople;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root =  FXMLLoader.load(getClass().getResource("GameGui.fxml"));
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.setTitle("Six Degrees of Separation");
		    primaryStage.show();
		    
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}