package com.skilldistillery.film.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

// interface to create contract with the Database Accessor Object on the methods listed
public interface FilmDAO {
	public Film getFilmById(int filmId);

	public Actor getActorById(int actorId);

	public List<Film> getFilmsByActorId(int actorId);

	public List<Actor> getActorsByFilmId(int filmId);

	public List<Film> getFilmsByKeyword(String keyword);

	public List<Film> getFilmConditionByFilmId(int filmId) throws SQLException;

	public boolean deleteFilm(Film film);

	public Film addFilm(Film film);

}
