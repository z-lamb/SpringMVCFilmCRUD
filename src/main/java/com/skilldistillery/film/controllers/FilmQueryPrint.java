package com.skilldistillery.film.controllers;

import java.util.Iterator;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public class FilmQueryPrint {

	/*
	 * Print standard details of a film query
	 * Film
	 * Release Year
	 * Rating
	 * Description
	 * Language
	 * Actors
	 */
	protected void printQuery(Film filmRequested) {
		
		System.out.println("Film:\t      " + filmRequested.getTitle());
		System.out.println("Release Year: " + filmRequested.getReleaseYear());
		System.out.println("Rating:\t      " + filmRequested.getRating());
		System.out.println("Description:  " + filmRequested.getDescription());
		System.out.println("Language:     " + filmRequested.getLanguageName());
		System.out.print("Actors:");
		
		for (int i = 0; i < filmRequested.getActors().size(); i++) {
			StringBuilder stringBuilder = new StringBuilder();
			System.out.println("\t      " + stringBuilder.append(filmRequested.getActors().get(i).getFirstName() + " ")
														.append(filmRequested.getActors().get(i).getLastName()));
		}
		System.out.println("\n-----------------------------------\n");
	}

	/* 
	 * Print all details about film query
	 * Film
	 * Description
	 * Release Year
	 * Language
	 * Rental Duration
	 * Rental Rate
	 * Length
	 * Cost To Replace
	 * Rating
	 * Special Features
	 * Category
	 * Actors
	 * Condition Quantity
	 */
	protected void printQueryAll(Film filmRequested) {
		
		System.out.println("Film:\t\t  " + filmRequested.getTitle());
		System.out.println("Description: \t  " + filmRequested.getDescription());
		System.out.println("Release Year: \t  " + filmRequested.getReleaseYear());
		System.out.println("Language: \t  " + filmRequested.getLanguageName());
		System.out.println("Rental Duration:  " + filmRequested.getRentalDuration());
		System.out.println("Rental Rate: \t  $" + filmRequested.getRentalRate());
		System.out.println("Length: \t  " + filmRequested.getLength());
		System.out.println("Cost To Replace:  $" + filmRequested.getReplacementCost());
		System.out.println("Rating: \t  " + filmRequested.getRating());
		System.out.println("Special Features: " + filmRequested.getSpecialFeatures());
		System.out.println("Category: \t  " + filmRequested.getCategory());
		System.out.print("Actors:");
		
		for (int i = 0; i < filmRequested.getActors().size(); i++) {
			StringBuilder stringBuilder = new StringBuilder();
			System.out.println("\t\t  " + stringBuilder.append(filmRequested.getActors().get(i).getFirstName() + " ")
														.append(filmRequested.getActors().get(i).getLastName()));
		}
		
		for (int i = 0; i < filmRequested.getConditionList().size(); i++) {
			if(i == 0) {
				System.out.println("Quantity New: \t  " + filmRequested.getConditionList().get(i).getNumberOfNew());
			}
			else if(i == 1) {
				System.out.println("Quantity Used: \t  " + filmRequested.getConditionList().get(i).getNumberOfUsed());
			}
			else if(i == 2) {
				System.out.println("Quantity Damaged: " + filmRequested.getConditionList().get(i).getNumberOfDamaged());
			}
			else if(i == 3) {
				System.out.println("Quantity Lost: \t  " + filmRequested.getConditionList().get(i).getNumberOfLost());
			}
			else if(i == 4) {
				System.out.println("Quantity NA: \t  " + filmRequested.getConditionList().get(i).getNumberOfNA());
			}
		}
		System.out.println("\n-----------------------------------\n");
	}

	/*
	 * If there are multiple queries print all in standard output
	 */
	protected void printQuery(List<Film> filmList) {
		Iterator<Film> iterator = filmList.iterator();

		while (iterator.hasNext()) {
			Film film = iterator.next();
			printQuery(film);
		}
	}

	/*
	 * If there are multiple queries print all queries with all details displayed
	 */
	protected void printQueryAll(List<Film> filmList) {
		Iterator<Film> iterator = filmList.iterator();

		while (iterator.hasNext()) {
			Film film = iterator.next();
			printQueryAll(film);
		}
	}
}
