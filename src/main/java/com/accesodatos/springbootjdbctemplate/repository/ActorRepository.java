package com.accesodatos.springbootjdbctemplate.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.accesodatos.springbootjdbctemplate.models.Actor;

/**
 * This class is in charge of connecting to the database. 
 * It performs certain sql sentences returning a result 
 * if it has worked correctly.
 */

@Repository
public class ActorRepository implements jdbcRepository {
	
	
	// Here we declare the jdbcTemplate and the sql statements.
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	final String SQL_FIND_ALL = "SELECT * FROM actor;";
	final String SQL_FIND_BY_ID = "SELECT * FROM actor WHERE (actor_id = ?);";
	final String SQL_INSERT = "INSERT INTO actor (first_name, last_name, last_update) VALUES (?, ?, ?);";
	final String SQL_UPDATE = "UPDATE actor SET first_name = ?, last_name = ?, last_update = ? WHERE (actor_id = ?);";
	final String SQL_DELETE = "DELETE FROM actor WHERE (actor_id = ?);";
	
	// We create a rowMapper to be able to map the ResultSet row data to an Actor object
	
	private RowMapper<Actor> rowMapper = (ResultSet rs, int rowNum) -> {
		Actor actor = new Actor();
		actor.setActorId(rs.getShort(1));
		actor.setFirstName(rs.getString(2));
		actor.setLastName(rs.getString(3));
		actor.setLastUpdate(rs.getDate(4));
		return actor;
	};

	/**
	 * This method saves the actor that we pass as a parameter into the actor table of the database.
	 * @param actor
	 * @return The number of how many rows have been modified
	 */
	
	@Override
	public int save(Actor actor) {
		return jdbcTemplate.update(
				SQL_INSERT, 
				new Object[] {
						actor.getFirstName(),
						actor.getLastName(),
						actor.getLastUpdate()
		});
	};
	
	/**
	 * This method updates the actor that we pass as parameter in the actor table of the database.
	 * @param actor
	 * @return The number of how many rows have been modified
	 */

	@Override
	public int update(Actor actor) {
		return jdbcTemplate.update(
				SQL_UPDATE, 
				new Object[] {
						actor.getFirstName(),
						actor.getLastName(),
						actor.getLastUpdate(),
						actor.getActorId()
		});
	};
	
	/**
	 * This method looks for the actor with the id we set as parameter in the actor table.
	 * @param id
	 * @return The actor we found or null if we do not found it.
	 */

	@Override
	public Actor findById(short id) {
		try {
			Actor actor = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, id);
			return actor;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	};
	
	/**
	 * This method deletes the actor of the id that we pass as a parameter in the actor table of the database.
	 * @param id
	 * @return The number of how many rows have been modified
	 */

	@Override
	public int delete(short id) {
		return jdbcTemplate.update(
				SQL_DELETE, id);
	};

	/**
	 * This method returns a list of all the actors that are inside the table.
	 * @return List of all the actors
	 */
	
	@Override
	public List<Actor> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
	};

}
