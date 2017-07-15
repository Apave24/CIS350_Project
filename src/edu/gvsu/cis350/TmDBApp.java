package edu.gvsu.cis350;

import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.TmdbApi;


/**
 * TmDB API Test Application.
 * 
 * @author Nandigam
 *
 */
public final class TmDBApp {
	
	/**
	 * @param args final
	 */
	public static void main(final String[] args) {		
		// demo of retrieving information on movies
		//TmDBModel.demoMovies();
		
		// demo of retrieving information on a specific movie
		//TmDBModel.demoMovieFeatures();
		
		// demo of searching features of TMDb API
		//TmDBModel.demoSearchFeatures();
		
		// demo of account related features of TMDb API
		//TmDBModel.demoAccountFeatures();
		TmdbApi tmdbApi 
			= new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
		TmdbPeople tmdbPeople = tmdbApi.getPeople();
		TmDBModel.getPrimaryActor(tmdbPeople);
	}
	
	/**
	 * 
	 */
	private TmDBApp() {
		
	}
	
}
