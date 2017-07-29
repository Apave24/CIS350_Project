package edu.gvsu.cis350;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.people.Person;

public class Controller{
	
	private TmdbPeople tmdbPeople;
	private TmdbMovies tmdbMovies;
	private TmdbApi tmdbApi;
	
	/**
	 * List of actors containing both correct and incorrect.
	 */
	private List<Person> actors = new ArrayList<Person>();
	
	/**
	 * The chosen correct answer's spot.
	 */
	private int correctAnswer;
	
	/**
	 * Image selected for the primary actor.
	 */
	private List<Artwork> pAImage;
	
	/**
	 * Image selected for the primary actor.
	 */
	private List<Artwork> Opt1Image;
	
	/**
	 * Image selected for the primary actor.
	 */
	private List<Artwork> Opt2Image;
	
	/**
	 * Image selected for the primary actor.
	 */
	private List<Artwork> Opt3Image;
	
	/**
	 * Image selected for the primary actor.
	 */
	private List<Artwork> Opt4Image;
	
	/**
	 * Image selected for the primary actor.
	 */
	private List<Artwork> Opt5Image;
	/**
	 * The actor chosen to compare to.
	 */
	private Person primaryActor;
	
	/**
	 * The actor chosen as the correct answer.
	 */
	private Person relatedActor;
	
	/**
	 * Number of correct answers.
	 */
	private int wins = -1;
	
	@FXML private ImageView PrimaryActorImage;
	@FXML private Label PrimaryActorName;
	
	@FXML private ImageView OptionImage1;
	@FXML private ImageView OptionImage2;
	@FXML private ImageView OptionImage3;
	@FXML private ImageView OptionImage4;
	@FXML private ImageView OptionImage5;
	
	@FXML private Label OptionName1;
	@FXML private Label OptionName2;
	@FXML private Label OptionName3;
	@FXML private Label OptionName4;
	@FXML private Label OptionName5;
	
	public Controller(){
		this.tmdbApi = new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
	    this.tmdbPeople = tmdbApi.getPeople();
		this.tmdbMovies = tmdbApi.getMovies();
	}
	
	@FXML 
	public void initialize(){
		updateData();
		updateGUI();
	}
	
	/**
	 * Updates the question and answers for the game.
	 */
	public void updateData() {
		wins++;
		Random randomIndex = new Random();
		correctAnswer = randomIndex.nextInt(5);
		
		primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		
		do {
			relatedActor = TmDBModel.getRelatedActor(
				tmdbPeople, tmdbMovies, primaryActor);
		} while (relatedActor.getCastId() == primaryActor.getCastId() 
		    || relatedActor.getName().equals(primaryActor.getName()));
		
		actors.clear();
		for (int x = 0; x < 5; x++) {
			if (correctAnswer == x) {
				actors.add(relatedActor);
			} else {
				actors.add(TmDBModel.getIncorrectAnswers(
						tmdbPeople, primaryActor));
			}
		}
		
		// Update image data
		pAImage = tmdbPeople.getPersonImages(primaryActor.getId());
		Opt1Image = tmdbPeople.getPersonImages(actors.get(0).getId());
		Opt2Image = tmdbPeople.getPersonImages(actors.get(1).getId());
		Opt3Image = tmdbPeople.getPersonImages(actors.get(2).getId());
		Opt4Image = tmdbPeople.getPersonImages(actors.get(3).getId());
		Opt5Image = tmdbPeople.getPersonImages(actors.get(4).getId());
	}
	
	/**
	 * Updates the GUI components with actors name and adds image.
	 */
	public void updateGUI() {
		
		// Update ImageView images
		PrimaryActorImage.setImage(new Image("https://image.tmdb.org/t/p/original" + pAImage.get(0).getFilePath()));
		OptionImage1.setImage(new Image("https://image.tmdb.org/t/p/original" + Opt1Image.get(0).getFilePath()));
		OptionImage2.setImage(new Image("https://image.tmdb.org/t/p/original" + Opt2Image.get(0).getFilePath()));
		OptionImage3.setImage(new Image("https://image.tmdb.org/t/p/original" + Opt3Image.get(0).getFilePath()));
		OptionImage4.setImage(new Image("https://image.tmdb.org/t/p/original" + Opt4Image.get(0).getFilePath()));
		OptionImage5.setImage(new Image("https://image.tmdb.org/t/p/original" + Opt5Image.get(0).getFilePath()));
		
		// Update Label text
		PrimaryActorName.setText(primaryActor.getName());
		OptionName1.setText(actors.get(0).getName());
		OptionName2.setText(actors.get(1).getName());
		OptionName3.setText(actors.get(2).getName());
		OptionName4.setText(actors.get(3).getName());
		OptionName5.setText(actors.get(4).getName());
	}

    @FXML protected void handleOption1Click(MouseEvent me) {
    	//TODO
    	//checkCorrectAnswer()
    }
    
    @FXML protected void handleOption2Click(MouseEvent me) {
    	//TODO
    	//checkCorrectAnswer()
    }

    @FXML protected void handleOption3Click(MouseEvent me) {
    	//TODO
    	//checkCorrectAnswer()
    }
    
    @FXML protected void handleOption4Click(MouseEvent me) {
    	//TODO
    	//checkCorrectAnswer()
    }
    
    @FXML protected void handleOption5Click(MouseEvent me) {
    	//TODO
    	//checkCorrectAnswer()
    }
    
    @FXML protected void handleSkipQuestion(ActionEvent event){
    	updateData();
    	updateGUI();
    }
}
