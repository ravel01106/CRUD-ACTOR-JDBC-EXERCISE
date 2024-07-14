package com.accesodatos.springbootjdbctemplate.models;

import java.util.Date;

/**
 * This class will be used as a template for the data 
 * we will collect from the actor table.
 */

public class Actor {
	private short actorId;
	private String firstName;
	private String lastName;
	private Date lastUpdate;
	
	public Actor() {
		super();
	}

	public Actor(short actorId, String firstName, String lastName) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = new Date();
	}
	public Actor(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = new Date();
	}

	public short getActorId() {
		return actorId;
	}

	public void setActorId(short actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate=" + lastUpdate
				+ "]";
	}
	
	
}
