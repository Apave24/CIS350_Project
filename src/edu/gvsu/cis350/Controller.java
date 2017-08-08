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

/**
 * @author evankiel
 *
 */
public class Controller {
	
	/**
	 * 
	 */
	private TmdbPeople tmdbPeople;
	/**
	 * 
	 */
	private TmdbMovies tmdbMovies;
	/**
	 * 
	 */
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
	 * Track stage of the game.
	 */
	private int degreeCounter = 1;
	
	/**
	 * Image list for the primary actor.
	 */
	private List<Artwork> pAImageList;
	
	/**
	 * 
	 */
	private List<Artwork> optImageList1;
	/**
	 * 
	 */
	private List<Artwork> optImageList2;
	/**
	 * 
	 */
	private List<Artwork> optImageList3;
	/**
	 * 
	 */
	private List<Artwork> optImageList4;
	/**
	 * 
	 */
	private List<Artwork> optImageList5;
	
	/**
	 * Image selected for the primary actor.
	 */
	private Image pAImage;
	
	/**
	 * 
	 */
	private Image optImage1;
	/**
	 * 
	 */
	private Image optImage2;
	/**
	 * 
	 */
	private Image optImage3;
	/**
	 * 
	 */
	private Image optImage4;
	/**
	 * 
	 */
	private Image optImage5;
	
	/**
	 * 
	 */
	private Image coverPhotoImage;
	
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
	
	/**
	 * 
	 */
	private int difficultySetting = 5;
	
	/**
	 * 
	 */
	private int[] relatedMovie = {0};
	
	/**
	 * 
	 */
	private Artwork noArtworkAvailable;
	/**
	 * 
	 */
	private Artwork noHint;
	
	/**
	 * 
	 */
	@FXML private ImageView primaryActorImage;
	/**
	 * 
	 */
	@FXML private Label primaryActorName;
	/**
	 * 
	 */
	@FXML private ImageView coverPhoto;
	/**
	 * 
	 */
	@FXML private Label relatedMovieName;
	/**
	 * 
	 */
	@FXML private Label hintIn;
	
	/**
	 * 
	 */
	@FXML private ImageView optionImage1;
	/**
	 * 
	 */
	@FXML private ImageView optionImage2;
	/**
	 * 
	 */
	@FXML private ImageView optionImage3;
	/**
	 * 
	 */
	@FXML private ImageView optionImage4;
	/**
	 * 
	 */
	@FXML private ImageView optionImage5;
	
	/**
	 * 
	 */
	@FXML private Label optionName1;
	/**
	 * 
	 */
	@FXML private Label optionName2;
	/**
	 * 
	 */
	@FXML private Label optionName3;
	/**
	 * 
	 */
	@FXML private Label optionName4;
	/**
	 * 
	 */
	@FXML private Label optionName5;
	
	/**
	 * 
	 */
	@FXML private ImageView firstDegreeImg;
	/**
	 * 
	 */
	@FXML private ImageView secondDegreeImg;
	/**
	 * 
	 */
	@FXML private ImageView thirdDegreeImg;
	/**
	 * 
	 */
	@FXML private ImageView fourthDegreeImg;
	/**
	 * 
	 */
	@FXML private ImageView fifthDegreeImg;
	/**
	 * 
	 */
	@FXML private ImageView sixthDegreeImg;
	
	/**
	 * 
	 */
	@FXML private Label firstDegreeName;
	/**
	 * 
	 */
	@FXML private Label secondDegreeName;
	/**
	 * 
	 */
	@FXML private Label thirdDegreeName;
	/**
	 * 
	 */
	@FXML private Label fourthDegreeName;
	/**
	 * 
	 */
	@FXML private Label fifthDegreeName;
	/**
	 * 
	 */
	@FXML private Label sixthDegreeName;
	
	/**
	 * 
	 */
	@FXML private Label userScore;
	/**
	 * 
	 */
	@FXML private Label answerStatus;
	
	/**
	 * 
	 */
	@FXML private ChoiceBox<String> difficulty;
	
	/**
	 * 
	 */
	public Controller() {
		this.tmdbApi = new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
	    this.tmdbPeople = tmdbApi.getPeople();
		this.tmdbMovies = tmdbApi.getMovies();
		
		this.noArtworkAvailable = new Artwork();
		this.noArtworkAvailable.setFilePath(
				"file://" + getClass().getResource(
						"NoPhoto.jpg").getPath());
		
		this.noHint = new Artwork();
		this.noHint.setFilePath(
				"file://" + getClass().getResource(
						"NoHint.jpg").getPath());
	}
	
	/**
	 * 
	 */
	@FXML 
	public void initialize() {
		difficulty.setItems(FXCollections
				.observableArrayList("Easy", "Medium", "Hard"));
		difficulty.setValue("Easy"); 
		
		//weirdly creates selection handler
		difficulty.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
			public void changed(final ObservableValue ov,
				final Number value, final Number newValue) {
				changeDifficulty(newValue);
			}
		});
	
		
		updateData();
		updateGUI();
		addDegreePhoto(pAImage);
	}
	

	
	/**
	 * Updates the question and answers for the game.
	 */
	public void updateData() {
		
		if (degreeCounter != 1) {
			primaryActor = actors.get(correctAnswer);
		} else {
			primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		}
		
		Random randomIndex = new Random();
		correctAnswer = randomIndex.nextInt(5);
		
		do {
			relatedActor = TmDBModel.getRelatedActor(
				tmdbPeople, tmdbMovies, primaryActor, 
				difficultySetting, relatedMovie);
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
		if (pAImageList.isEmpty()) {
			pAImage = new Image(noArtworkAvailable.getFilePath());
		} else {
			pAImage = new Image(
					"https://image.tmdb.org/t/p/original" 
					+ pAImageList.get(0).getFilePath());
		}
		
		optImageList1 = tmdbPeople
				.getPersonImages(actors.get(0).getId());
		if (optImageList1.isEmpty()) {
			optImage1 = new Image(noArtworkAvailable.getFilePath());
		} else {
			optImage1 = new Image(
					"https://image.tmdb.org/t/p/original" 
					+ optImageList1.get(0).getFilePath());
		}
		
		optImageList2 = tmdbPeople
				.getPersonImages(actors.get(1).getId());
		if (optImageList2.isEmpty()) {
			optImage2 = new Image(noArtworkAvailable.getFilePath());
		} else {
			optImage2 = new Image(
					"https://image.tmdb.org/t/p/original" 
					+ optImageList2.get(0).getFilePath());
		}
		
		optImageList3 = tmdbPeople
				.getPersonImages(actors.get(2).getId());
		if (optImageList3.isEmpty()) {
			optImage3 = new Image(noArtworkAvailable.getFilePath());
		} else {
			optImage3 = new Image(
					"https://image.tmdb.org/t/p/original" 
					+ optImageList3.get(0).getFilePath());
		}
		
		optImageList4 = tmdbPeople
				.getPersonImages(actors.get(3).getId());
		if (optImageList4.isEmpty()) {
			optImage4 = new Image(noArtworkAvailable.getFilePath());
		} else {
			optImage4 = new Image(
					"https://image.tmdb.org/t/p/original" 
					+ optImageList4.get(0).getFilePath());
		}
		
		optImageList5 = tmdbPeople
				.getPersonImages(actors.get(4).getId());
		if (optImageList5.isEmpty()) {
			optImage5 = new Image(noArtworkAvailable.getFilePath());
		} else {
			optImage5 = new Image(
					"https://image.tmdb.org/t/p/original" 
					+ optImageList5.get(0).getFilePath());
		}
		
		coverPhotoImage = new Image(noHint.getFilePath());
		coverPhoto.setImage(coverPhotoImage);
		hintIn.setVisible(false);
	}
	
	/**
	 * Updates the GUI components with actors name and adds image.
	 */
	public void updateGUI() {
		
		// Update ImageView images
		optionImage1.setImage(optImage1);
		optionImage2.setImage(optImage2);
		optionImage3.setImage(optImage3);
		optionImage4.setImage(optImage4);
		optionImage5.setImage(optImage5);
	
		// Update Label text
		primaryActorName.setText(primaryActor.getName());
		optionName1.setText(actors.get(0).getName());
		optionName2.setText(actors.get(1).getName());
		optionName3.setText(actors.get(2).getName());
		optionName4.setText(actors.get(3).getName());
		optionName5.setText(actors.get(4).getName());
		relatedMovieName.setText(null);
		
		
	}
	
	/**
	 * @param correctImage Image to be used for degree
	 */
	private void addDegreePhoto(final Image correctImage) {
		switch (degreeCounter) {
		case 1:
			firstDegreeImg.setImage(correctImage);
			firstDegreeName.setText(primaryActor.getName());
			break;
		case 2:
			secondDegreeImg.setImage(correctImage);
			secondDegreeName
				.setText(actors.get(correctAnswer).getName());
			break;
		case 3:
			thirdDegreeImg.setImage(correctImage);
			thirdDegreeName
				.setText(actors.get(correctAnswer).getName());
			break;
		case 4:
			fourthDegreeImg.setImage(correctImage);
			fourthDegreeName
				.setText(actors.get(correctAnswer).getName());
			break;
		case 5:
			fifthDegreeImg.setImage(correctImage);
			fifthDegreeName
				.setText(actors.get(correctAnswer).getName());
			break;
		case 6:
			sixthDegreeImg.setImage(correctImage);
			sixthDegreeName
				.setText(actors.get(correctAnswer).getName());
			break;
		default:
			break;
		}
		
		
		degreeCounter++;
	}
	
	/**
	 * 
	 */
	public void clearDegree() {
		firstDegreeImg.setImage(null);
		secondDegreeImg.setImage(null);
		thirdDegreeImg.setImage(null);
		fourthDegreeImg.setImage(null);
		fifthDegreeImg.setImage(null);
		sixthDegreeImg.setImage(null);
		firstDegreeName.setText(null);
		secondDegreeName.setText(null);
		thirdDegreeName.setText(null);
		fourthDegreeName.setText(null);
		fifthDegreeName.setText(null);
		sixthDegreeName.setText(null);
		
		degreeCounter = 1;
		updateData();
    	updateGUI();
    	userScore.setText(new Integer(wins).toString());
		addDegreePhoto(pAImage);
	}
	
	/**
	 * @param difficulty Difficulty setting for the game
	 */
	private void changeDifficulty(final Number difficulty) {
		switch (difficulty.intValue()) {
			case 0:
				difficultySetting = 5;
				wins = 0;
				clearDegree();
				break;
			case 1:
				difficultySetting = 20;
				wins = 0;
				clearDegree();
				break;
			case 2:
				difficultySetting = 100;
				wins = 0;
				clearDegree();
				break;
			default:
				break;
		}
	}

    /**
     * @param me MouseEvent captured
     */
    @FXML protected void handleOption1Click(final MouseEvent me) {
    	if (correctAnswer == 0) {
    		if (degreeCounter < 6) {
    			answerStatus.setText("Correct!");
        		wins++;
        		if (me.getSource() instanceof ImageView) {
        			ImageView clickedImage 
        				= (ImageView) me.getSource();
        			addDegreePhoto(clickedImage.getImage());
        		}
        		updateData();
            	updateGUI();
    		} else {
    			answerStatus.setText("Six Degrees!");
    			wins = wins + difficultySetting;
    			clearDegree();
    		}
    		answerStatus.setTextFill(Color.web("#008000"));
    	} else {
    		wins--;
    		answerStatus.setText("Incorrect!");
    		answerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	userScore.setText(new Integer(wins).toString());
    }
    
    /**
     * @param me MouseEvent captured
     */
    @FXML protected void handleOption2Click(final MouseEvent me) {
    	if (correctAnswer == 1) {
    		if (degreeCounter < 6) {
    			answerStatus.setText("Correct!");
        		wins++;
        		if (me.getSource() instanceof ImageView) {
        			ImageView clickedImage 
        				= (ImageView) me.getSource();
        			addDegreePhoto(clickedImage.getImage());
        		}
        		updateData();
            	updateGUI();
    		} else {
    			answerStatus.setText("Six Degrees!");
    			wins = wins + difficultySetting;
    			clearDegree();
    		}
    		answerStatus.setTextFill(Color.web("#008000"));
    	} else {
    		wins--;
    		answerStatus.setText("Incorrect!");
    		answerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	userScore.setText(new Integer(wins).toString());
    }

    /**
     * @param me MouseEvent captured
     */
    @FXML protected void handleOption3Click(final MouseEvent me) {
    	if (correctAnswer == 2) {
    		if (degreeCounter < 6) {
    			answerStatus.setText("Correct!");
        		wins++;
        		if (me.getSource() instanceof ImageView) {
        			ImageView clickedImage 
        				= (ImageView) me.getSource();
        			addDegreePhoto(clickedImage.getImage());
        		}
        		updateData();
            	updateGUI();
    		} else {
    			answerStatus.setText("Six Degrees!");
    			wins = wins + difficultySetting;
    			clearDegree();
    		}
    		answerStatus.setTextFill(Color.web("#008000"));
    	} else {
    		wins--;
    		answerStatus.setText("Incorrect!");
    		answerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	userScore.setText(new Integer(wins).toString());
    }
    
    /**
     * @param me MouseEvent captured
     */
    @FXML protected void handleOption4Click(final MouseEvent me) {
    	if (correctAnswer == 3) {
    		if (degreeCounter < 6) {
    			answerStatus.setText("Correct!");
        		wins++;
        		if (me.getSource() instanceof ImageView) {
        			ImageView clickedImage 
        				= (ImageView) me.getSource();
        			addDegreePhoto(clickedImage.getImage());
        		}
        		updateData();
            	updateGUI();
    		} else {
    			answerStatus.setText("Six Degrees!");
    			wins = wins + difficultySetting;
    			clearDegree();
    		}
    		answerStatus.setTextFill(Color.web("#008000"));
    	} else {
    		wins--;
    		answerStatus.setText("Incorrect!");
    		answerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	userScore.setText(new Integer(wins).toString());
    }
    
    /**
     * @param me MouseEvent captured
     */
    @FXML protected void handleOption5Click(final MouseEvent me) {
    	if (correctAnswer == 4) {
    		if (degreeCounter < 6) {
    			answerStatus.setText("Correct!");
        		wins++;
        		if (me.getSource() instanceof ImageView) {
        			ImageView clickedImage 
        				= (ImageView) me.getSource();
        			addDegreePhoto(clickedImage.getImage());
        		}
        		updateData();
            	updateGUI();
    		} else {
    			answerStatus.setText("Six Degrees!");
    			wins = wins + difficultySetting;
    			clearDegree();
    		}
    		answerStatus.setTextFill(Color.web("#008000"));
    	} else {
    		wins--;
    		answerStatus.setText("Incorrect!");
    		answerStatus.setTextFill(Color.web("#FF0000"));
    	}
    	userScore.setText(new Integer(wins).toString());
    }
    
    /**
     * @param event Captured skip button press
     */
    @FXML protected void handleSkipQuestion(final ActionEvent event) {
    		clearDegree();
    }
    
    /**
     * @param me Captured mouse click on Hint button
     */
    @FXML protected void handleHintClicked(final MouseEvent me) {
    		coverPhotoImage = new Image(
    				"https://image.tmdb.org/t/p/w1280" 
    				+ tmdbMovies.getMovie(relatedMovie[0], "en")
    				.getPosterPath());
    		relatedMovieName.setText(tmdbMovies
    				.getMovie(relatedMovie[0], "en").getTitle());
    		hintIn.setVisible(true);
    		coverPhoto.setImage(coverPhotoImage);
    		wins--;
    		userScore.setText(new Integer(wins).toString());
    }
    
    
    /**
     * @param event Captured quit button press
     */
    @FXML protected void handleQuit(final ActionEvent event) {
    		System.exit(0);
    }
}
