package com.accesodatos.springbootjdbctemplate.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accesodatos.springbootjdbctemplate.models.Actor;
import com.accesodatos.springbootjdbctemplate.service.ActorService;

/**
 * This controller class is in charge of displaying or returning the data 
 * or the result that we make when making the requests.
 * As we can see, the root endpoint is api/v1, 
 * from there we will make the corresponding requests
 */

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ActorController {
	
	// We create an instance of an ActorService
	@Autowired
	ActorService actorService;
	
	/**
	 * In this method, using the endpoint api/v1/actors and its httpVerb is get, 
	 * we will return the result of method getAllActors of the ActorService instance.
	 * @return
	 * If the list is completely empty it will return a 204 indicating that it does not contain any data. 
	 * If the list is not empty it will return a list of Actors and a status of 200 indicating 
	 * that it has been sent successfully.
	 * If the server is down it will send an 500 error.
	 */
	
	@GetMapping("/actors")
	public ResponseEntity<List<Actor>> getAllActors(){
		try {
			List<Actor> actors = new ArrayList<Actor>();
			actorService.getAllActors().forEach(actors::add);
			if (actors.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(actors, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * In this method, using the endpoint api/v1/actors/{id} and its httpVerb is get, 
	 * we will return the result of method getActorById of the ActorService instance.
	 * @param id
	 * @return
	 * If the actor is null it will return a 404. 
	 * If the actor is not null it will return an actor and a status of 200 indicating 
	 * that it has been sent successfully.
	 * If the server is down it will send an 500 error.
	 */
	
	@GetMapping("/actors/{id}")
	public ResponseEntity<Actor> getActorById( @PathVariable("id") short id){
		try {
			Actor actor = actorService.getActorById(id);
			if (actor != null) {
				return new ResponseEntity<Actor>(actor, HttpStatus.OK);
			}else {
				return new ResponseEntity<Actor>(actor, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * In this method, using the endpoint api/v1/actors and its httpVerb is post, 
	 * we will return the result of method createActors of the ActorService instance.
	 * @param actor
	 * @return
	 * If the actor is created inside the table it will automatically return a 201 indicating 
	 * that it has been created successfully.
	 * If the server is down it will send an 500 error.
	 */
	
	@PostMapping("/actors")
	public ResponseEntity<String> createActor( @RequestBody Actor actor){
		try {
			actorService.createActor(new Actor(
					actor.getFirstName(), 
					actor.getLastName()
				));
			return new ResponseEntity<>("Actor was created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * In this method, using the endpoint api/v1/actors/{id} and its httpVerb is put, 
	 * we will return the result of method updateActor of the ActorService instance.
	 * @param id
	 * @param actor
	 * @return
	 * If the actor is null it will return a 404. 
	 * If the actor is not null it will return an text and a status of 200 indicating 
	 * that it has been updated successfully.
	 * If the server is down it will send an 500 error.
	 */
	
	@PutMapping("/actors/{id}")
	public ResponseEntity<String> updateActor( @PathVariable("id") short id, @RequestBody Actor actor){
		
		try {
			Actor _actor = actorService.getActorById(id);
			if (_actor != null) {
				_actor.setActorId(id);
				_actor.setFirstName(actor.getFirstName());
				_actor.setLastName(actor.getLastName());
				_actor.setLastUpdate(new Date());
				actorService.updateActor(_actor);
				return new ResponseEntity<String>("Actor was updated successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Cannot find actor with id " +  id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * In this method, using the endpoint api/v1/actors/{id} and its httpVerb is delete, 
	 * we will return the result of method deleteActor of the ActorService instance.
	 * @param id
	 * @return
	 * If the actor is null it will return a 404. 
	 * If the actor is not null it will return an text and a status of 200 indicating 
	 * that it has been deleted successfully.
	 * If the server is down it will send an 500 error.
	 */
	
	@DeleteMapping("/actors/{id}")
	public ResponseEntity<String> deleteActor( @PathVariable("id") short id){
		try {
			Actor actor = actorService.getActorById(id);
			if (actor != null) {
				actorService.deleteActor(id);
				return new ResponseEntity<String>("Actor was deleted successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Cannot find actor with id " +  id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
	
}
