package com.skilldistillery.film.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

// interface to create contract with the Database Accessor Object on the methods listed
public interface DatabaseAccessor {
	public Film getFilmById(int filmId) throws SQLException;

	public Actor getActorById(int actorId) throws SQLException;

	public List<Film> getFilmsByActorId(int actorId) throws SQLException;

	public List<Actor> getActorsByFilmId(int filmId) throws SQLException;

	public List<Film> getFilmsByKeyword(String keyword) throws SQLException;

	public List<Film> getFilmConditionByFilmId(int filmId) throws SQLException;

}
