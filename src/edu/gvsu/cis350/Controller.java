package edu.gvsu.cis350;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	 * Stage of the game
	 */
	private int degreeCounter = 1;
	
	/**
	 * Image list for the primary actor.
	 */
	private List<Artwork> pAImageList;
	
	private List<Artwork> Opt1ImageList;
	private List<Artwork> Opt2ImageList;
	private List<Artwork> Opt3ImageList;
	private List<Artwork> Opt4ImageList;
	private List<Artwork> Opt5ImageList;
	
	/**
	 * Image selected for the primary actor.
	 */
	private Image pAImage;
	
	private Image Opt1Image;
	private Image Opt2Image;
	private Image Opt3Image;
	private Image Opt4Image;
	private Image Opt5Image;
	
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
	private int wins = 0;
	
	private Artwork noArtworkAvailable;
	
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
	
	@FXML private ImageView FirstDegreeImg;
	@FXML private ImageView SecondDegreeImg;
	@FXML private ImageView ThirdDegreeImg;
	@FXML private ImageView FourthDegreeImg;
	@FXML private ImageView FifthDegreeImg;
	@FXML private ImageView SixthDegreeImg;
	
	@FXML private Label FirstDegreeName;
	@FXML private Label SecondDegreeName;
	@FXML private Label ThirdDegreeName;
	@FXML private Label FourthDegreeName;
	@FXML private Label FifthDegreeName;
	@FXML private Label SixthDegreeName;
	
	@FXML private Label UserScore;
	@FXML private Label AnswerStatus;
	
	@FXML private ChoiceBox Difficulty;
	
	public Controller(){
		this.tmdbApi = new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
	    this.tmdbPeople = tmdbApi.getPeople();
		this.tmdbMovies = tmdbApi.getMovies();
		
		this.noArtworkAvailable = new Artwork();
		this.noArtworkAvailable.setFilePath("file://" + getClass().getResource("NoPhoto.jpg").getPath());
	}
	
	@FXML 
	public void initialize(){
		Difficulty.setItems(FXCollections.observableArrayList("Easy", "Medium", "Hard")); // adds options to the choicebox
		Difficulty.setValue("Easy"); // sets the default to easy hopefully
		
		//weirdly creates selection handler
		Difficulty.getSelectionModel().selectedIndexProperty().addListener(new 
				ChangeListener<Number>() {
					public void changed(ObservableValue ov,
							Number value, Number new_value){
						changeDifficulty(new_value);
					}
		});
		
		//TODO: initialize the game to easy
		primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		
		updateData();
		updateGUI();
		addDegreePhoto(pAImage);
	}
	
	/**
	 * Updates the question and answers for the game.
	 */
	public void updateData() {
		
		if(degreeCounter != 1){
			primaryActor = actors.get(correctAnswer);
		}
		
		Random randomIndex = new Random();
		correctAnswer = randomIndex.nextInt(5);
		
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
		pAImageList = tmdbPeople.getPersonImages(primaryActor.getId());
		if(pAImageList.isEmpty()){
			pAImage = new Image(noArtworkAvailable.getFilePath());
		}
		else {
			pAImage = new Image("https://image.tmdb.org/t/p/original" + pAImageList.get(0).getFilePath());
		}
		
		Opt1ImageList = tmdbPeople.getPersonImages(actors.get(0).getId());
		if(Opt1ImageList.isEmpty()){
			Opt1Image = new Image(noArtworkAvailable.getFilePath());
		}
		else {
			Opt1Image = new Image("https://image.tmdb.org/t/p/original" + Opt1ImageList.get(0).getFilePath());
		}
		
		Opt2ImageList = tmdbPeople.getPersonImages(actors.get(1).getId());
		if(Opt2ImageList.isEmpty()){
			Opt2Image = new Image(noArtworkAvailable.getFilePath());
		}
		else {
			Opt2Image = new Image("https://image.tmdb.org/t/p/original" + Opt2ImageList.get(0).getFilePath());
		}
		
		Opt3ImageList = tmdbPeople.getPersonImages(actors.get(2).getId());
		if(Opt3ImageList.isEmpty()){
			Opt3Image = new Image(noArtworkAvailable.getFilePath());
		}
		else {
			Opt3Image = new Image("https://image.tmdb.org/t/p/original" + Opt3ImageList.get(0).getFilePath());
		}
		
		Opt4ImageList = tmdbPeople.getPersonImages(actors.get(3).getId());
		if(Opt4ImageList.isEmpty()){
			Opt4Image = new Image(noArtworkAvailable.getFilePath());
		}
		else {
			Opt4Image = new Image("https://image.tmdb.org/t/p/original" + Opt4ImageList.get(0).getFilePath());
		}
		
		Opt5ImageList = tmdbPeople.getPersonImages(actors.get(4).getId());
		if(Opt5ImageList.isEmpty()){
			Opt5Image = new Image(noArtworkAvailable.getFilePath());
		}
		else {
			Opt5Image = new Image("https://image.tmdb.org/t/p/original" + Opt5ImageList.get(0).getFilePath());
		}
		
	}
	
	/**
	 * Updates the GUI components with actors name and adds image.
	 */
	public void updateGUI() {
		
		// Update ImageView images
		OptionImage1.setImage(Opt1Image);
		OptionImage2.setImage(Opt2Image);
		OptionImage3.setImage(Opt3Image);
		OptionImage4.setImage(Opt4Image);
		OptionImage5.setImage(Opt5Image);
		
		// Update Label text
		PrimaryActorName.setText(primaryActor.getName());
		OptionName1.setText(actors.get(0).getName());
		OptionName2.setText(actors.get(1).getName());
		OptionName3.setText(actors.get(2).getName());
		OptionName4.setText(actors.get(3).getName());
		OptionName5.setText(actors.get(4).getName());
	}
	
	private void addDegreePhoto(Image correctImage){
		switch(degreeCounter){
		case 1:
			FirstDegreeImg.setImage(correctImage);
			FirstDegreeName.setText(primaryActor.getName());
			break;
		case 2:
			SecondDegreeImg.setImage(correctImage);
			SecondDegreeName.setText(actors.get(correctAnswer).getName());
			break;
		case 3:
			ThirdDegreeImg.setImage(correctImage);
			ThirdDegreeName.setText(actors.get(correctAnswer).getName());
			break;
		case 4:
			FourthDegreeImg.setImage(correctImage);
			FourthDegreeName.setText(actors.get(correctAnswer).getName());
			break;
		case 5:
			FifthDegreeImg.setImage(correctImage);
			FifthDegreeName.setText(actors.get(correctAnswer).getName());
			break;
		case 6:
			SixthDegreeImg.setImage(correctImage);
			SixthDegreeName.setText(actors.get(correctAnswer).getName());
			break;
		}
		
		degreeCounter++;
	}
	
	private void changeDifficulty(Number difficulty){
		switch(difficulty.intValue()){
			case 0:
				//TODO: set difficulty to easy
				break;
			case 1:
				//TODO: set difficulty to medium
				break;
			case 2:
				//TODO: set difficulty to hard
				break;
		}
	}

    @FXML protected void handleOption1Click(MouseEvent me) {
    	if(correctAnswer == 0){
    		wins++;
    		AnswerStatus.setText("Correct!");
    		AnswerStatus.setTextFill(Color.web("#008000"));
    		if(me.getSource() instanceof ImageView){
    			ImageView clickedImage = (ImageView) me.getSource();
    			addDegreePhoto(clickedImage.getImage());
    		}
    		updateData();
        	updateGUI();
    	}
    	else {
    		wins--;
    		AnswerStatus.setText("Incorrect!");
    		AnswerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	UserScore.setText(new Integer(wins).toString());
    }
    
    @FXML protected void handleOption2Click(MouseEvent me) {
    	if(correctAnswer == 1){
    		wins++;
    		AnswerStatus.setText("Correct!");
    		AnswerStatus.setTextFill(Color.web("#008000"));
    		if(me.getSource() instanceof ImageView){
    			ImageView clickedImage = (ImageView) me.getSource();
    			addDegreePhoto(clickedImage.getImage());
    		}
    		updateData();
        	updateGUI();
    	}
    	else {
    		wins--;
    		AnswerStatus.setText("Incorrect!");
    		AnswerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	UserScore.setText(new Integer(wins).toString());
    }

    @FXML protected void handleOption3Click(MouseEvent me) {
    	if(correctAnswer == 2){
    		wins++;
    		AnswerStatus.setText("Correct!");
    		AnswerStatus.setTextFill(Color.web("#008000"));
    		if(me.getSource() instanceof ImageView){
    			ImageView clickedImage = (ImageView) me.getSource();
    			addDegreePhoto(clickedImage.getImage());
    		}
    		updateData();
        	updateGUI();
    	}
    	else {
    		wins--;
    		AnswerStatus.setText("Incorrect!");
    		AnswerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	UserScore.setText(new Integer(wins).toString());
    }
    
    @FXML protected void handleOption4Click(MouseEvent me) {
    	if(correctAnswer == 3){
    		wins++;
    		AnswerStatus.setText("Correct!");
    		AnswerStatus.setTextFill(Color.web("#008000"));
    		if(me.getSource() instanceof ImageView){
    			ImageView clickedImage = (ImageView) me.getSource();
    			addDegreePhoto(clickedImage.getImage());
    		}
    		updateData();
        	updateGUI();
    	}
    	else {
    		wins--;
    		AnswerStatus.setText("Incorrect!");
    		AnswerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	UserScore.setText(new Integer(wins).toString());
    }
    
    @FXML protected void handleOption5Click(MouseEvent me) {
    	if(correctAnswer == 4){
    		wins++;
    		AnswerStatus.setText("Correct!");
    		AnswerStatus.setTextFill(Color.web("#008000"));
    		if(me.getSource() instanceof ImageView){
    			ImageView clickedImage = (ImageView) me.getSource();
    			addDegreePhoto(clickedImage.getImage());
    		}
    		updateData();
        	updateGUI();
    	}
    	else {
    		wins--;
    		AnswerStatus.setText("Incorrect!");
    		AnswerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	UserScore.setText(new Integer(wins).toString());
    }
    
    @FXML protected void handleSkipQuestion(ActionEvent event){
    	updateData();
    	updateGUI();
    }
    
    @FXML protected void handleHintClicked(MouseEvent me){
    	//TODO: implement Hint button with either a popup or label underneath the buttons
    }
    
    
    @FXML protected void handleQuit(ActionEvent event) {
    		System.exit(0);
    }
}
