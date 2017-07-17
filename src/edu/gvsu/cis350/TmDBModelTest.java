package edu.gvsu.cis350;


import static org.junit.Assert.assertEquals;
import info.movito.themoviedbapi.TmdbApi;
//import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.model.people.Person;

import org.junit.Test;

/**
 * @author evankiel
 *
 */
public class TmDBModelTest {
	
	/**
	 * 
	 */
	private TmdbApi tmdbApi 
		= new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
	/**
	 * 
	 */
	private TmdbPeople tmdbPeople = tmdbApi.getPeople();
	/**
	 * 
	 */
	//private TmdbMovies tmdbMovies = tmdbApi.getMovies(); 

	/**
	 * 
	 */
	@Test
	public void testIncorrectActor() {
		Person primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		assertEquals(false, 
			TmDBModel.getIncorrectAnswers(tmdbPeople, primaryActor)
			.getCastId() == primaryActor.getCastId());
	}

}
