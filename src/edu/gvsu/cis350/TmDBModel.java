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
import info.movito.themoviedbapi.model.people.PersonCredit;
import info.movito.themoviedbapi.model.people.PersonCredits;
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
	 * @param tmdbPeople TmdbPeople
	 * @return Person
	 */
	public static Person getPrimaryActor(final TmdbPeople tmdbPeople) {
		List<Person> popularList 
			= tmdbPeople.getPersonPopular(0).getResults();
		Random randomIndex = new Random();
		return popularList.get(randomIndex.nextInt(popularList.size()));
	}
	
	/**
	 * @param tmdbPeople TmdbPeople
	 * @param primaryActor Person
	 * @return Person
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
	 * @param tmdbPeople TmdbPeople
	 * @param tmdbMovies TmdbMovies
	 * @param primaryActor Person
	 * @return PersonCast
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
