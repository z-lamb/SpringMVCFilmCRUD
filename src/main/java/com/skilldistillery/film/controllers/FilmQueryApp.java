package com.skilldistillery.film.controllers;

import java.sql.SQLException;

// Imports to ensure connectivity to the database at the beginning of the project
//import com.skilldistillery.filmquery.database.DatabaseAccessor;
//import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
//import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	
	/*
	 * Main method which creates a new instance of the app to call the launch method
	 */
	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	/*
	 * Launch method creates new instance of Film Query Operation and calls the start Operation all on a single line
	 */
	private void launch() {
		new FilmQueryOperation().startOperation();
		
	}
	
	
	
	
	// created to ensure connectivity to the database at the beginning of the project
//	private DatabaseAccessor db = new DatabaseAccessorObject();

	// Used to ensure connectivity to the database at the beginning of the project
//	private void test() throws SQLException {
//		Film film = db.getFilmById(1);
//		if (film == null) {
//			System.out.println("Error with connection to database");
//		}
//	}


}
