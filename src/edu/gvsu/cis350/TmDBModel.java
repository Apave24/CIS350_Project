package edu.gvsu.cis350;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import info.movito.themoviedbapi.TmdbAccount;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Reviews;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.AccountID;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;


/**
 * Model class for the application.
 * 
 * @author nandigam
 *
 */
public final class TmDBModel {

	/**
	 * 
	 */
	private static TmdbApi tmdbApi 
		= new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
	/**
	 * 
	 */
	private static SessionToken sessionToken = getSessionToken();
	
	/**
	 * @return SessionToken
	 */
	private static SessionToken getSessionToken() {
		// There are two ways to generate a session id
		
		/* Method 1: Generating session id using API calls
			(requires username and password)
		*/
		
		
		/*TmdbAuthentication tmdbAuth = tmdbApi.getAuthentication();
		TokenSession tokenSession 
			= tmdbAuth.getSessionLogin("kielstreak","Password");
		System.out.println("Session ID: " 
			+ tokenSession.getSessionId());
		SessionToken sessionToken 
			= new SessionToken(tokenSession.getSessionId());
		*/
		
		// Method 2: Generating session id via the website
		// Step 1: create a new request token
		//		http://api.themoviedb.org/3/authentication
		//				/token/new?api_key=your-api-key
		//		(note down the request_token from the response)
		// Step 2: ask the user for permission
		//		https://www.themoviedb.org/authenticate
		//				/request_token
		// Step 3: create a session id
		//		http://api.themoviedb.org/3/authentication
		//			/session/new?api_key=api-key
		//			&request_token=request-token
		//	    (use session-id value in the response to set the 
		//		value for sessionId variable in the code below)
		
		// hard-coded session id generated from Method 1 above
		SessionToken sessionToken 
			= new SessionToken(
				"8d9dc5390a6fcf433d7406c0c47453b8ce23fd4c");
		return sessionToken;
	}
	
	/**
	 * 
	 */
	public static void demoMovies() {
		TmdbMovies tmdbMovies = tmdbApi.getMovies();
		
		// top rated movies
		MovieResultsPage results 
			= tmdbMovies.getTopRatedMovies("en", 0);
		
		System.out.println("======= Top rated movies ============");
		Iterator<MovieDb> iterator = results.iterator();
		while (iterator.hasNext()) {
			MovieDb movie = iterator.next();
			System.out.println(movie.getTitle());
			System.out.println(movie.getOriginalTitle());
			System.out.println(movie.getReleaseDate());
			System.out.println();
		}
		System.out.println("======= Top rated movies ============");
	}
	
	/**
	 * 
	 */
	public static void demoMovieFeatures() {
		TmdbMovies tmdbMovies = tmdbApi.getMovies();
		
		System.out.println("======= Movies features ============");
		
		// retrieve information on a movie given movie id
		MovieDb movie = tmdbMovies.getMovie(271110, "en", 
				MovieMethod.credits, MovieMethod.reviews, 
				MovieMethod.videos);
		System.out.println(movie.getTitle());
		System.out.println(movie.getReleaseDate());
		System.out.println(movie.getRuntime());
		System.out.println(movie.getOverview());
		System.out.println();
		
		// retrieve information on movie cast
		List<PersonCast> cast = movie.getCast();
		if (cast == null) {
			System.out.println(
				"Cast info not available for this movie");
		} else {
			Iterator<PersonCast> iterator = cast.iterator();
			while (iterator.hasNext()) {
				PersonCast person = iterator.next();
				System.out.println(person.getName());
				System.out.println(person.getCharacter());
				System.out.println();
			}
		}
		
		// retrieve information on movie crew
		List<PersonCrew> crew = movie.getCrew();
		if (crew == null) {
			System.out.println(
				"Crew info not available for this movie");
		} else {
			Iterator<PersonCrew> iterator = crew.iterator();
			while (iterator.hasNext()) {
				PersonCrew person = iterator.next();
				System.out.println(person.getName());
				System.out.println(person.getJob());
				System.out.println();
			}
		}
		
		// get review for the movie
		List<Reviews> reviews = movie.getReviews();
		if (reviews == null) {
			System.out.println(
				"Reviews not available for this movie");
		} else {
			Iterator<Reviews> iterator = reviews.iterator();
			while (iterator.hasNext()) {
				Reviews review = iterator.next();
				System.out.println(review.getAuthor());
				System.out.println(review.getContent());
				System.out.println();
			}
		}
		
		// retrieve information on movie videos
		List<Video> videos = movie.getVideos();
		if (videos == null) {
			System.out.println(
				"Videos info not available for this movie");
		} else {
			Iterator<Video> iterator = videos.iterator();
			while (iterator.hasNext()) {
				Video video = iterator.next();
				System.out.println(video.getSite());
				System.out.println(video.getKey());
				System.out.println(video.getName());
				System.out.println(video.getType());
				System.out.println(video.getSize());
				System.out.println();
			}
		}
		
		System.out.println("======= Movies features ============");
	}
	
	/**
	 * 
	 */
	public static void demoSearchFeatures() {
		TmdbSearch tmdbSearch = tmdbApi.getSearch();
		
		// search for movies containing "civil war" in title
		MovieResultsPage results 
		    = tmdbSearch.searchMovie("civil war", 0, "en", false, 0);
		Iterator<MovieDb> iterator = results.iterator();
		while (iterator.hasNext()) {
			MovieDb movie = iterator.next();
			System.out.println(movie.getTitle());
			System.out.println(movie.getOriginalTitle());
			System.out.println(movie.getReleaseDate());
			System.out.println();
		}	
	}
	
	/**
	 * 
	 */
	public static void demoAccountFeatures() {
		TmdbAccount tmdbAccount = tmdbApi.getAccount();
		
		// get basic account information
		Account act = tmdbAccount.getAccount(sessionToken);
		System.out.println(act.getId());
		System.out.println(act.getUserName());
		System.out.println(act.getName());
		System.out.println();
		
		// get favorite movies of the account
		AccountID actId = new AccountID(act.getId());
		MovieResultsPage results 
			= tmdbAccount.getFavoriteMovies(sessionToken, actId);
		System.out.println(
			"Movies in the favorite list: " 
			+ results.getTotalResults());
		Iterator<MovieDb> iterator = results.iterator();
		while (iterator.hasNext()) {
			MovieDb movie = iterator.next();
			System.out.println(movie.getTitle());
			System.out.println(movie.getReleaseDate());
			System.out.println();
		}
		
		// get movies in the watch list of the account
		results 
		   = tmdbAccount.getWatchListMovies(sessionToken, actId, 0);
		System.out.println(
			"Movies in the watch list: " 
			+ results.getTotalResults());
		iterator = results.iterator();
		while (iterator.hasNext()) {
			MovieDb movie = iterator.next();
			System.out.println(movie.getTitle());
			System.out.println(movie.getId());
			System.out.println(movie.getReleaseDate());
			System.out.println();
		}	
	}
	
	public static Person getPrimaryActor(TmdbPeople tmdbPeople) {
		List<Person> popularList = tmdbPeople.getPersonPopular(0).getResults();
		Random randomIndex = new Random();
		return popularList.get(randomIndex.nextInt(popularList.size()));
	}
	
	/**
	 * 
	 */
	private TmDBModel() {
		
	}


}
