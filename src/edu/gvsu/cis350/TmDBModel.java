package edu.gvsu.cis350;

import java.util.Iterator;
import java.util.List;
import java.util.Random;


import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCredit;
import info.movito.themoviedbapi.model.people.PersonCredits;


/**
 * Model class for the application.
 * 
 * @author nandigam
 *
 */
public final class TmDBModel {
	/**
	 * @param tmdbPeople full list of people in the database
	 * @return random popular actor to be compared to
	 */
	public static Person getPrimaryActor(final TmdbPeople tmdbPeople) {
		List<Person> popularList 
			= tmdbPeople.getPersonPopular(0).getResults();
		Random randomIndex = new Random();
		return popularList.get(randomIndex.nextInt(popularList.size()));
	}
	
	/**
	 * @param tmdbPeople full list of people in the database
	 * @param primaryActor the actor chosen to be compared to
	 * @return an incorrect answer
	 */
	public static Person getIncorrectAnswers(
		   final TmdbPeople tmdbPeople, final Person primaryActor) {
		boolean unrelatedActors = true;
		PersonCredits primaryActorCredits 
			= tmdbPeople.getPersonCredits(primaryActor.getId());
		Random randomIndex = new Random();
		List<Person> popularList 
			= tmdbPeople.getPersonPopular(
				randomIndex.nextInt(100)).getResults();
		Person secondaryActor 
			= popularList.get(randomIndex.nextInt(
				popularList.size()));
		PersonCredits secondaryActorCredits 
			= tmdbPeople.getPersonCredits(secondaryActor.getId());
		Iterator<PersonCredit> primaryIterator 
			= primaryActorCredits.getCast().iterator();
		Iterator<PersonCredit> secondaryIterator 
			= secondaryActorCredits.getCast().iterator();
		while (primaryIterator.hasNext()) {
			PersonCredit primaryCredit = primaryIterator.next();
			int primaryMovieID = primaryCredit.getMovieId();
			while (secondaryIterator.hasNext()) {
				PersonCredit secondaryCredit 
					= secondaryIterator.next();
				int secondaryMovieID 
					= secondaryCredit.getMovieId();
				if (primaryMovieID == secondaryMovieID) {
					unrelatedActors = false;
					break;
				}
			}
			if (!unrelatedActors) {
				break;
			}
		}
		if (!unrelatedActors) {
			return getIncorrectAnswers(tmdbPeople, primaryActor);
		} else {
			return secondaryActor;
		}
	}
	
	/**
	 * @param tmdbPeople full list of people in the database
	 * @param tmdbMovies full list of Movies in the database
	 * @param primaryActor the actor chosen to be compared to
	 * @return a correct answer- actors are in a movie together
	 */
	public static PersonCast getRelatedActor(final TmdbPeople tmdbPeople, 
		   final TmdbMovies tmdbMovies, final Person primaryActor) {
		int actorId = primaryActor.getId(); 
        List<PersonCredit> movieList 
        	= tmdbPeople.getPersonCredits(actorId).getCast();
        Random randomIndex = new Random();
        int randomMovieId 
        	= movieList.get(randomIndex.nextInt(
        			movieList.size())).getMovieId();
        System.out.println(tmdbMovies.getMovie(randomMovieId, "en"));
        List<PersonCast> cast = tmdbMovies.getCredits(randomMovieId).getCast();
       
        PersonCast randomRelatedPerson 
        	= cast.get(randomIndex.nextInt(cast.size()));
       
        System.out.println(randomRelatedPerson.getName());
        return randomRelatedPerson;
}
	/**
	 * 
	 */
	private TmDBModel() {
		
	}


}
