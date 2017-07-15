package edu.gvsu.cis350;


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
		
		TmDBModel.getPopular();
	}
	
	/**
	 * 
	 */
	private TmDBApp() {
		
	}
	
}
