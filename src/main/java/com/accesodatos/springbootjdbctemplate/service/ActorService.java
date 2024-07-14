package com.accesodatos.springbootjdbctemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.springbootjdbctemplate.models.Actor;
import com.accesodatos.springbootjdbctemplate.repository.ActorRepository;

/**
 * This class acts as an intermediary between the repository and the controller.
 */

@Service
public class ActorService {
	
	// We create an instance of an ActorRepository
	@Autowired
	ActorRepository actorRepository;
	
	/**
	 * This method call the calls the findAll method 
	 * of the ActorRepository returns its result.
	 * @return List of all the actors
	 */
	
	public List<Actor> getAllActors(){
		return actorRepository.findAll();
	}
	
	/**
	 * This method call the calls the findById method 
	 * of the ActorRepository returns its result.
	 * @return List of all the actors
	 */
	
	public Actor getActorById(short id) {
		return actorRepository.findById(id);
	}
	
	/**
	 * This method call the calls the save method 
	 * of the ActorRepository returns its result.
	 * @return The actor we found or null if we do not found it.
	 */
	
	public int createActor(Actor actor) {
		return actorRepository.save(new Actor(
				actor.getFirstName(),
				actor.getLastName()
				));
	}
	
	/**
	 * This method call the calls the update method 
	 * of the ActorRepository returns its result.
	 * @return The number of how many rows have been modified
	 */
	
	public int updateActor(Actor actor) {
		return actorRepository.update(actor);
	}
	
	/**
	 * This method call the calls the delete method 
	 * of the ActorRepository returns its result.
	 * @return The number of how many rows have been modified
	 */
	
	public int deleteActor(short id) {
		return actorRepository.delete(id);
	}
	
	
}
