package com.skilldistillery.film.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	/*
	 * Database address, username, password
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";
	/*
	 * Driver to connect to the database
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method to get Film By Id
	 */
	@Override
	public Film getFilmById(int filmId) throws SQLException {
		
		/*
		 * Connection to the database and create new reference to a film object to be returned
		 */
		Film film = null;
		Connection conn = DriverManager.getConnection(URL, user, pass);

		/*
		 * SQL string to be passed to the prepared statement along with the requested ID which returns the query
		 * If there is a query that is returned it will add each item requested to a new film object
		 * After that has finished it will close the Prepared Statement and connection and return the film object
		 */
		String sql = "SELECT film.id, film.title, film.description, film.release_year, language.name, film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, category.name FROM film JOIN language ON language.id = film.language_id JOIN film_category ON film.id = film_category.film_id JOIN category ON film_category.category_id = category.id WHERE film.id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();

		if (filmResult.next()) {
			film = new Film();
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setReleaseYear(filmResult.getShort("release_year"));
			film.setLanguageName(filmResult.getString("language.name"));
			film.setRentalDuration(filmResult.getInt("rental_duration"));
			film.setRentalRate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setReplacementCost(filmResult.getDouble("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			film.setSpecialFeatures(filmResult.getString("Special_features"));
			film.setCategory(filmResult.getString("category.name"));
			film.setActors(getActorsByFilmId(filmId));
			film.setConditionList(getFilmConditionByFilmId(filmId));
		}

		stmt.close();
		conn.close();
		return film;
	}

	/*
	 * Method to get Actor By Id
	 */
	@Override
	public Actor getActorById(int actorId) throws SQLException {

		/*
		 * Connection to the database and create new reference an actor object to be returned
		 */
		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, user, pass);

		/*
		 * SQL string to be passed to the prepared statement along with the requested ID which returns the query
		 * If there is a query that is returned it will add each item requested to a new actor object
		 * After that has finished it will close the Prepared Statement and connection and return the actor object
		 */
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		
		if (actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setFirstName(actorResult.getString("last_name"));
			actor.setFilms(getFilmsByActorId(actorId));
		}

		stmt.close();
		conn.close();
		return actor;
	}

	/*
	 * Method to get Actor By Film Id
	 */
	@Override
	public List<Actor> getActorsByFilmId(int filmId) throws SQLException {

		/*
		 * Connection to the database and create new object to be returned
		 */
		List<Actor> actors = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, user, pass);

		/*
		 * SQL string to be passed to the prepared statement along with the requested ID which returns the query
		 * If there is a query that is returned it will add each item requested to a new actor object
		 * After that has finished it will close the Prepared Statement and connection and return the actor list object
		 */
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Actor actor = new Actor();
			actor.setId(rs.getInt("id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));

			actors.add(actor);
		}
		
		stmt.close();
		conn.close();
		return actors;
	}

	/*
	 * Method to get Films By Actor Id
	 */
	@Override
	public List<Film> getFilmsByActorId(int actorId) throws SQLException {
		
		/*
		 * Connection to the database and create new object to be returned
		 */
		List<Film> films = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, user, pass);

		/*
		 * SQL string to be passed to the prepared statement along with the requested ID which returns the query
		 * If there is a query that is returned it will add each item requested to a new film object
		 * After that has finished it will close the Prepared Statement and connection and return the film list object
		 */
		String sql = "SELECT film.id, title, description, release_year, language.name, rental_duration, rental_rate, length, replacement_cost, rating, special_features, category.name FROM film JOIN film_actor ON film.id = film_actor.film_id JOIN language ON language.id = film.language_id JOIN film_category ON film.id = film_category.film_id JOIN category ON film_category.category_id = category.id WHERE actor_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Film film = new Film();

			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getShort("release_year"));
			film.setLanguageName(rs.getString("language.name"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setCategory(rs.getString("category.name"));

			films.add(film);
		}
		
		stmt.close();
		conn.close();
		return films;
	}

	/*
	 * Method to get Films By Keyword
	 */
	@Override
	public List<Film> getFilmsByKeyword(String keyword) throws SQLException {
		
		/*
		 * Connection to the database and create new object to be returned
		 */
		List<Film> films = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, user, pass);

		/*
		 * SQL string to be passed to the prepared statement along with the requested ID which returns the query
		 * If there is a query that is returned it will add each item requested to a new film object
		 * After that has finished it will close the Prepared Statement and connection and return the film list object
		 */
		String sql = "SELECT film.id, film.title, film.description, film.release_year, language.name, film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, category.name FROM film JOIN language ON language.id = film.language_id JOIN film_category ON film.id = film_category.film_id JOIN category ON film_category.category_id = category.id WHERE title LIKE ? OR description LIKE ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + keyword + "%");
		stmt.setString(2, "%" + keyword + "%");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Film film = new Film();

			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getShort("release_year"));
			film.setLanguageName(rs.getString("language.name"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setCategory(rs.getString("category.name"));
			film.setActors(getActorsByFilmId(rs.getInt("id")));
			film.setConditionList(getFilmConditionByFilmId(rs.getInt("id")));

			films.add(film);
		}
		
		stmt.close();
		conn.close();
		return films;
	}
	
	/*
	 * Method to get Film Condition By Film Id
	 */
	@Override
	public List<Film> getFilmConditionByFilmId(int filmId) throws SQLException {

		/*
		 * Connection to the database and create new object to be returned
		 */
		List<Film> conditionList = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, user, pass);

		/*
		 * SQL strings to be passed to the prepared statement along with the requested ID which returns the query
		 * If there is a query that is returned it will add each item requested to a new film object
		 * After that has finished it will close the Prepared Statement and connection and return the film list object
		 */
		String sql = "SELECT COUNT(media_condition) from inventory_item where media_condition = \"new\" AND film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet conditionResult = stmt.executeQuery();
		if (conditionResult.next()) {
			Film condition = new Film();
			condition.setNumberOfNew(conditionResult.getInt("Count(media_condition)"));
			conditionList.add(condition);
		}
		
		sql = "SELECT COUNT(media_condition) from inventory_item where media_condition = \"used\" AND film_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		conditionResult = stmt.executeQuery();
		if (conditionResult.next()) {
			Film condition = new Film();
			condition.setNumberOfUsed(conditionResult.getInt("Count(media_condition)"));
			conditionList.add(condition);
		}
		
		sql = "SELECT COUNT(media_condition) from inventory_item where media_condition = \"damaged\" AND film_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		conditionResult = stmt.executeQuery();
		if (conditionResult.next()) {
			Film condition = new Film();
			condition.setNumberOfDamaged(conditionResult.getInt("Count(media_condition)"));
			conditionList.add(condition);
		}
		
		sql = "SELECT COUNT(media_condition) from inventory_item where media_condition = \"lost\" AND film_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		conditionResult = stmt.executeQuery();
		if (conditionResult.next()) {
			Film condition = new Film();
			condition.setNumberOfLost(conditionResult.getInt("Count(media_condition)"));
			conditionList.add(condition);
		}
		
		sql = "SELECT COUNT(media_condition) from inventory_item where media_condition = \"NA\" AND film_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		conditionResult = stmt.executeQuery();
		if (conditionResult.next()) {
			Film condition = new Film();
			condition.setNumberOfNA(conditionResult.getInt("Count(media_condition)"));
			conditionList.add(condition);
		}
		
		stmt.close();
		conn.close();
		return conditionList;
	}
}
