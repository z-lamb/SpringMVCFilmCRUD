package com.skilldistillery.film.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDAOImpl implements FilmDAO {

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
	public Film getFilmById(int filmId) {

		/*
		 * Connection to the database and create new reference to a film object to be
		 * returned
		 */
		Film film = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			/*
			 * SQL string to be passed to the prepared statement along with the requested ID
			 * which returns the query If there is a query that is returned it will add each
			 * item requested to a new film object After that has finished it will close the
			 * Prepared Statement and connection and return the film object
			 */
			String sql = "SELECT film.id, film.title, film.description, film.release_year, language.name, "
					+ "film.rental_duration, film.rental_rate, film.length, film.replacement_cost, "
					+ "film.rating, film.special_features, category.name " + "FROM film "
					+ "JOIN language ON language.id = film.language_id "
					+ "JOIN film_category ON film.id = film_category.film_id "
					+ "JOIN category ON film_category.category_id = category.id " + "WHERE film.id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				film = new Film();
				film = setFilmObject(film, filmResult);
				film.setActors(getActorsByFilmId(filmId));
				film.setConditionList(getFilmConditionByFilmId(filmId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return film;
	}

	/*
	 * Method to get Actor By Id
	 */
	@Override
	public Actor getActorById(int actorId) {

		/*
		 * Connection to the database and create new reference an actor object to be
		 * returned
		 */
		Actor actor = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);

			/*
			 * SQL string to be passed to the prepared statement along with the requested ID
			 * which returns the query If there is a query that is returned it will add each
			 * item requested to a new actor object After that has finished it will close
			 * the Prepared Statement and connection and return the actor object
			 */
			String sql = "SELECT id, first_name, last_name " + "FROM actor " + "WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setFirstName(actorResult.getString("last_name"));
				actor.setFilms(getFilmsByActorId(actorId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return actor;
	}

	/*
	 * Method to get Actor By Film Id
	 */
	@Override
	public List<Actor> getActorsByFilmId(int filmId) {

		/*
		 * Connection to the database and create new object to be returned
		 */
		List<Actor> actors = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);

			/*
			 * SQL string to be passed to the prepared statement along with the requested ID
			 * which returns the query If there is a query that is returned it will add each
			 * item requested to a new actor object After that has finished it will close
			 * the Prepared Statement and connection and return the actor list object
			 */
			String sql = "SELECT actor.id, actor.first_name, actor.last_name " + "FROM actor "
					+ "JOIN film_actor ON actor.id = film_actor.actor_id " + "WHERE film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));

				actors.add(actor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actors;
	}

	/*
	 * Method to get Films By Actor Id
	 */
	@Override
	public List<Film> getFilmsByActorId(int actorId) {

		/*
		 * Connection to the database and create new object to be returned
		 */
		List<Film> films = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);

			/*
			 * SQL string to be passed to the prepared statement along with the requested ID
			 * which returns the query If there is a query that is returned it will add each
			 * item requested to a new film object After that has finished it will close the
			 * Prepared Statement and connection and return the film list object
			 */
			String sql = "SELECT film.id, title, description, release_year, language.name, "
					+ "rental_duration, rental_rate, length, replacement_cost, rating, "
					+ "special_features, category.name " + "FROM film "
					+ "JOIN film_actor ON film.id = film_actor.film_id "
					+ "JOIN language ON language.id = film.language_id "
					+ "JOIN film_category ON film.id = film_category.film_id "
					+ "JOIN category ON film_category.category_id = category.id " + "WHERE actor_id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film = setFilmObject(film, rs);
				films.add(film);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return films;
	}

	/*
	 * Method to get Films By Keyword
	 */
	@Override
	public List<Film> getFilmsByKeyword(String keyword) {

		/*
		 * Connection to the database and create new object to be returned
		 */
		List<Film> films = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);

			/*
			 * SQL string to be passed to the prepared statement along with the requested ID
			 * which returns the query If there is a query that is returned it will add each
			 * item requested to a new film object After that has finished it will close the
			 * Prepared Statement and connection and return the film list object
			 */
			String sql = "SELECT film.id, film.title, film.description, film.release_year, "
					+ "language.name, film.rental_duration, film.rental_rate, film.length, "
					+ "film.replacement_cost, film.rating, film.special_features, category.name " + "FROM film "
					+ "JOIN language ON language.id = film.language_id "
					+ "JOIN film_category ON film.id = film_category.film_id "
					+ "JOIN category ON film_category.category_id = category.id " + "WHERE title "
					+ "LIKE ? OR description LIKE ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film = setFilmObject(film, rs);
				film.setActors(getActorsByFilmId(rs.getInt("id")));
				film.setConditionList(getFilmConditionByFilmId(rs.getInt("id")));
				films.add(film);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return films;
	}

	/*
	 * Method to get Film Condition By Film Id
	 */
	@Override
	public List<Film> getFilmConditionByFilmId(int filmId) {

		/*
		 * Connection to the database and create new object to be returned
		 */
		List<Film> conditionList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		ResultSet conditionResult = null;
		Film condition = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * SQL strings to be passed to the prepared statement along with the requested
		 * ID which returns the query If there is a query that is returned it will add
		 * each item requested to a new film object After that has finished it will
		 * close the Prepared Statement and connection and return the film list object
		 */

		try {
			sql = "SELECT COUNT(media_condition) " + "FROM inventory_item "
					+ "WHERE media_condition = \"new\" AND film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			conditionResult = stmt.executeQuery();
			if (conditionResult.next()) {
				condition = new Film();
				condition.setNumberOfNew(conditionResult.getInt("Count(media_condition)"));
				conditionList.add(condition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			sql = "SELECT COUNT(media_condition) " + "FROM inventory_item "
					+ "WHERE media_condition = \"used\" AND film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			conditionResult = stmt.executeQuery();
			if (conditionResult.next()) {
				condition = new Film();
				condition.setNumberOfUsed(conditionResult.getInt("Count(media_condition)"));
				conditionList.add(condition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			sql = "SELECT COUNT(media_condition) " + "FROM inventory_item "
					+ "WHERE media_condition = \"damaged\" AND film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			conditionResult = stmt.executeQuery();
			if (conditionResult.next()) {
				condition = new Film();
				condition.setNumberOfDamaged(conditionResult.getInt("Count(media_condition)"));
				conditionList.add(condition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			sql = "SELECT COUNT(media_condition) " + "FROM inventory_item "
					+ "WHERE media_condition = \"lost\" AND film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			conditionResult = stmt.executeQuery();
			if (conditionResult.next()) {
				condition = new Film();
				condition.setNumberOfLost(conditionResult.getInt("Count(media_condition)"));
				conditionList.add(condition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			sql = "SELECT COUNT(media_condition) " + "FROM inventory_item "
					+ "WHERE media_condition = \"NA\" AND film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			conditionResult = stmt.executeQuery();
			if (conditionResult.next()) {
				condition = new Film();
				condition.setNumberOfNA(conditionResult.getInt("Count(media_condition)"));
				conditionList.add(condition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conditionList;
	}

	@Override
	public Film addFilm(Film film) {
		/*
		 * Connection to the database and create new reference to a film object to be
		 * returned
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		int newFilmId = 0;

		/*
		 * SQL string to be passed to the prepared statement along with the requested ID
		 * which returns the query If there is a query that is returned it will add each
		 * item requested to a new film object After that has finished it will close the
		 * Prepared Statement and connection and return the film object
		 */
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, description, release_year, language_id, "
					+ "rental_duration, rental_rate, length, replacement_cost, rating, special_features)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setShort(3, film.getReleaseYear());
			stmt.setInt(4, 1);
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keysResult = stmt.getGeneratedKeys();

				sql = "insert into film_category (film_id, category_id) " + "values(last_insert_id(), 1)";

				stmt = conn.prepareStatement(sql);
				updateCount += stmt.executeUpdate();

				if (updateCount == 2) {
					if (keysResult.next()) {
						newFilmId = keysResult.getInt(1);
						film.setId(newFilmId);
					}
				}
			} else {
				film = null;
			}
			conn.commit();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					sqle2.printStackTrace();
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film);

		}
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException sqleClose) {
			sqleClose.printStackTrace();
		}

		return film;
	}

	
	@Override
	public boolean deleteFilm(Film film) {
		/*
		 * Connection to the database and create new reference to a film object to be
		 * returned
		 */
		Connection conn = null;
		PreparedStatement stmt = null;

		/*
		 * SQL string to be passed to the prepared statement along with the requested ID
		 * which returns the query If there is a query that is returned it will add each
		 * item requested to a new film object After that has finished it will close the
		 * Prepared Statement and connection and return the film object
		 */
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM film_category WHERE film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			stmt.executeUpdate();
			stmt.close();
			
			sql = "DELETE FROM film WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			stmt.executeUpdate();

			conn.commit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					sqle2.printStackTrace();
					System.err.println("Error trying to rollback");
				}
			}
			return false;

		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqleClose) {
				sqleClose.printStackTrace();
			}
		}

		return true;
	}

	
	public boolean updateFilm(Film film) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "UPDATE film set title=?, description=?, release_year=?, language_id=?, "
					+ "rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=?, "
					+ "special_features=?" + "WHERE id = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setShort(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			stmt.setInt(11, film.getId());

			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				conn.commit();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					System.err.println("Error trying to rollback");
					e.printStackTrace();
				}
			}
			
			return false;
		}

		return true;
	}

	private Film setFilmObject(Film film, ResultSet rs) {
		try {
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
}
