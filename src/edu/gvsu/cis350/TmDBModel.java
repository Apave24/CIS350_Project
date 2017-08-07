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
 * Background game base functionality.
 * 
 * @author Evan Kiel
 *
 */
public final class TmDBModel {
	/**
	 * Pulls a random actor from the list of popular actors.
	 * @param tmdbPeople full list of people in the database
	 * @return random popular actor to be compared to
	 */
	public static Person getPrimaryActor(final TmdbPeople tmdbPeople) {
		List<Person> popularList 
			= tmdbPeople.getPersonPopular(0).getResults();
		Random randomIndex = new Random();
		return popularList.get(randomIndex.nextInt(popularList.size()));
	}
	
	public static Person getFinalActor(final TmdbPeople tmdbPeople, Person primaryActor) {
		Person finalActor = null;
		
		do {
			finalActor = getPrimaryActor(tmdbPeople);
		} while(finalActor == primaryActor);
		
		return finalActor;
	} 
	
	/**
	 * Finds an actor that is not in a movie with the primary actor.
	 * @param tmdbPeople full list of people in the database
	 * @param primaryActor the actor chosen to be compared to
	 * @return an actor unrelated to the primaryActor
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
	 * Finds an actor that was in a movie with the primary actor.
	 * @param tmdbPeople full list of people in the database
	 * @param tmdbMovies full list of Movies in the database
	 * @param primaryActor the actor chosen to be compared to
	 * @return a correct answer- actors are in a movie together
	 */
	public static PersonCast getRelatedActor(final TmdbPeople tmdbPeople, 
		   final TmdbMovies tmdbMovies, final Person primaryActor, final int difficultySetting, int [] relatedMovie) {
		int actorId = primaryActor.getId();
		int maxValue = difficultySetting;
        List<PersonCredit> movieList 
        	= tmdbPeople.getPersonCredits(actorId).getCast();
        Random randomIndex = new Random();
        relatedMovie[0]
        	= movieList.get(randomIndex.nextInt(
        			movieList.size())).getMovieId();
        System.out.println(tmdbMovies.getMovie(relatedMovie[0], "en"));
        List<PersonCast> cast = tmdbMovies.getCredits(relatedMovie[0]).getCast();
		if (maxValue > cast.size() || maxValue <= 0) {
			maxValue = cast.size();
		}
        PersonCast randomRelatedPerson 
        	= cast.get(randomIndex.nextInt(maxValue));
       
        System.out.println(randomRelatedPerson.getName());
        return randomRelatedPerson;
}
	/**
	 * 
	 */
	private TmDBModel() {
		
	}


}
