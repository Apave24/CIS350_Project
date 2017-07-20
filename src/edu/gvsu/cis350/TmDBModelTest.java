package edu.gvsu.cis350;


import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCredit;

/**
 * @author Alex Pavey
 *
 */
public class TmDBModelTest {
	
	/**
	 * Created key using session id.
	 */
	private TmdbApi tmdbApi 
		= new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
	/**
	 * Object containing all people in database.
	 */
	private TmdbPeople tmdbPeople = tmdbApi.getPeople();
	/**
	 * Object containing all movies in database.
	 */
	private TmdbMovies tmdbMovies = tmdbApi.getMovies(); 

	/**
	 * Tests to make sure getPrimaryActor finds any actor. 
	 */
	@Test
	public void testPrimaryActor() {
		Person primaryActor = null;
		primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		Assert.assertNotNull(primaryActor);
	}
	
	/**
	 * Tests to see if getIncorrectAnswers returns an incorrect answer.
	 */
	@Test
	public void testIncorrectActor() {
		boolean actorInMovieList = false;
		Person primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		Person unrelatedActor 
		   = TmDBModel.getIncorrectAnswers(tmdbPeople, primaryActor);
		
		List<PersonCredit> actorMovies = tmdbPeople
			.getPersonCredits(primaryActor.getId()).getCast();
		Iterator<PersonCredit> iterator = actorMovies.iterator();
		while (iterator.hasNext()) {
			PersonCredit movie = iterator.next();
			List<PersonCast> cast = tmdbMovies
				.getCredits(movie.getMovieId()).getCast();
			Iterator<PersonCast> relatedActors = cast.iterator();
			while (relatedActors.hasNext()) {
				PersonCast actor = relatedActors.next();
				if (actor.getId() == unrelatedActor.getId()) {
					actorInMovieList = true;
				}
			}
		}
		
		Assert.assertEquals(false, actorInMovieList);
	}
	
	/**
	 * Tests to see if getRelatedActor returns a correct answer.
	 */
	@Test
	public void testMatchingActor() {
		boolean actorInMovieList = false;
		Person primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		Person matchingActor = TmDBModel
			.getRelatedActor(tmdbPeople, tmdbMovies, primaryActor);
		
		List<PersonCredit> actorMovies = tmdbPeople
			.getPersonCredits(primaryActor.getId()).getCast();
		Iterator<PersonCredit> iterator = actorMovies.iterator();
		while (iterator.hasNext()) {
			PersonCredit movie = iterator.next();
			List<PersonCast> cast = tmdbMovies
				.getCredits(movie.getMovieId()).getCast();
			Iterator<PersonCast> relatedActors = cast.iterator();
			while (relatedActors.hasNext()) {
				PersonCast actor = relatedActors.next();
				if (actor.getId() == matchingActor.getId()) {
					actorInMovieList = true;
				}
			}
		}
		
		Assert.assertEquals(true, actorInMovieList);
	}

}
