package com.accesodatos.springbootjdbctemplate.repository;

import java.util.List;

/**
 * This is the interface that the repository class will use, 
 * declaring the methods it will need
 */

import com.accesodatos.springbootjdbctemplate.models.Actor;

public interface jdbcRepository {
	int save(Actor actor);
	int update(Actor actor);
	Actor findById(short id);
	int delete(short id);
	List<Actor> findAll();
}
