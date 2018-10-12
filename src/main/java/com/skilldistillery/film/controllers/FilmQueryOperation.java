package com.skilldistillery.film.controllers;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.film.dao.FilmDAOImpl;
import com.skilldistillery.film.entities.Film;

/*
 * Most of the operation happens in this class which will either call other methods in this class or call other classes
 */
public class FilmQueryOperation {

	/*
	 * Fields for either just reference or reference/object to be declared or created
	 */
	private FilmQueryMenus filmQueryMenus = new FilmQueryMenus();
	private FilmQueryLookUp lookUp;
	private Film filmReturned;
	private List<Film> filmListReturned;
	private String subMenuSelection;
	private FilmQueryPrint printQuery = new FilmQueryPrint();

	/*
	 * Declared database accessor object and initialized to null as to not create a new item on the heap unless it is needed
	 */
	private FilmDAOImpl dao = null;

	/*
	 * Simple method to start the operation of looking up the query
	 * Also creates and closes the scanner. Scanner is passed to other methods or classes as needed 
	 */
	public void startOperation() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	/*
	 * Starting point for the user interface
	 * Loop keeps printing the menu (by calling the class for menus) for the user until the select a correct option
	 * If the user enters 0 the program will end
	 * Menu selection and scanner are passed to the menu options method to determine what to do with the users selection
	 */
	private void startUserInterface(Scanner input) {

		String menuSelection;
		System.out.println("************************************");
		System.out.println("*   Welcome to the Film Database   *");
		System.out.println("************************************\n");
		
		do {
			filmQueryMenus.mainMenu();
			menuSelection = input.next();
			System.out.println();
			menuOptions(menuSelection, input);
		} while (!menuSelection.equals("0"));
	}

	/*
	 * Method to determine what to do with the users selection
	 * If option 1 is selected the user will be promp
	 */
	private void menuOptions(String menuSelection, Scanner input) {

		/*
		 * Only create new object on heap if user decides to look something up and it
		 * hasn't already been created
		 */
		if (menuSelection != "0") {
			if (dao == null) {
				dao = new FilmDAOImpl();
				lookUp = new FilmQueryLookUp();
			}
		}

		switch (menuSelection) {
			case "1":
				filmById(input);
				break;
			case "2":
				filmByKeyword(input);
				break;
			case "0":
				return;
			default:
				System.out.println("\n-------------------------------------");
				System.out.println("Please make a selection from the menu");
				System.out.println("-------------------------------------\n");
		}
	}

	/*
	 * Looks up film by id based on user input
	 * User is asked to enter the id of the movie they would like to look up
	 * Messages are printed to console if the user did not enter the correct information
	 * If the user did not enter the correct information it will loop to have them try again
	 */
	private void filmById(Scanner input) {

		String filmId = null;
		Film filmRequested = null;

		do {
			System.out.println("Enter the ID number of the film you would like to see:");
			System.out.print(">> ");
			filmId = input.next();
			System.out.println();
			filmReturned = lookUp.lookUpFilmById(dao, input, filmId, filmRequested);
			
			if (filmReturned == null) {
				StringBuilder stringBuilder = new StringBuilder();
				String printDash = "Id: \"" + filmId + "\" is not a valid Id number. Enter a number between 1 - 1000";
				System.out.println(printDash);
				
				for (int i = 0; i < printDash.length(); i++) {
					stringBuilder.append("-");
				}
				System.out.println(stringBuilder + "\n");
			} else {
				System.out.println("The film you requested is: ");
				System.out.println("--------------------------");
				printQuery.printQuery(filmReturned);
			}
		} while (!filmId.matches("^[0-9]*$") || filmReturned == null);
		
		subMenuOptions(filmReturned, input);
	}
	
	/*
	 * Looks up film by keyword based on user input
	 * User is asked to enter the keyword of the movie they would like to look up
	 * Messages are printed to console if the user did not enter the correct information
	 * If the user did not enter the correct information it will loop to have them try again
	 */
	private void filmByKeyword(Scanner input) {
		
		String keyword;
		List<Film> keywordFilms = null;

		keywordLoop: do {
			System.out.println("Enter the keyword of the film you would like to see:");
			System.out.print(">> ");
			keyword = input.next();
			System.out.println();
			filmListReturned = lookUp.lookUpFilmByKeyword(dao, input, keyword, keywordFilms);
			
			if (filmListReturned.size() == 0) {
				
				StringBuilder stringBuilder = new StringBuilder();
				String printDash = "There were no films that containted the keyword: " + keyword;
				System.out.println(printDash);

				for (int i = 0; i < printDash.length(); i++) {
					stringBuilder.append("-");
				}
				System.out.println(stringBuilder + "\n");

				String retry;
				do {
					System.out.println("Would you like to try again?");
					System.out.println("1. Yes");
					System.out.println("2. No");
					System.out.print(">> ");
					retry = input.next();
					System.out.println();
					
					switch(retry) {
						case "1":
							continue keywordLoop;
						case "2":
							break keywordLoop;
						default:
							System.out.println("--------------------");	
							System.out.println("Please choose 1 or 2");
							System.out.println("--------------------\n");
					}
				} while(!retry.equals("2"));
			}
		} while(filmListReturned.size() == 0);
		
		if(filmListReturned.size() !=0) {
			StringBuilder stringBuilder = new StringBuilder();
			String printDash = "Here are the films that contained the keyword: " + keyword;
			System.out.println(printDash);
			
			for (int i = 0; i < printDash.length(); i++) {
				stringBuilder.append("-");
			}
			System.out.println(stringBuilder + "\n");
			printQuery.printQuery(filmListReturned);
			subMenuOptions(filmListReturned, input);
		}
	}

	/*
	 * sub menu for a list of objects for the user to choose if they would like to see all of the details of a film or return to the main menu
	 */
	private void subMenuOptions(List<Film> filmListReturned, Scanner input) {

		do {
			filmQueryMenus.subMenu();
			subMenuSelection = input.next();
			switch (subMenuSelection) {
				case "1":
					System.out.println();
					System.out.println("Here are all of the film details of the movie requested: ");
					System.out.println("--------------------------------------------------------");
					printQuery.printQueryAll(filmListReturned);
					return;
				case "0":
					System.out.println();
					return;
				default:
					System.out.println("\n-------------------");
					System.out.println("Please enter 1 or 0");
					System.out.println("-------------------\n");
			}
		} while (!subMenuSelection.equals("0"));
	}

	/*
	 * sub menu for a single object for the user to choose if they would like to see all of the details of a film or return to the main menu
	 */
	private void subMenuOptions(Film filmReturned, Scanner input) {
		do {
			filmQueryMenus.subMenu();
			subMenuSelection = input.next();
			switch (subMenuSelection) {
			case "1":
				System.out.println();
				System.out.println("Here are all of the film details of the movie requested: ");
				System.out.println("--------------------------------------------------------");
				printQuery.printQueryAll(filmReturned);
				return;
			case "0":
				System.out.println();
				return;
			default:
				System.out.println("\n-----------------------");
				System.out.println("Make a proper selection");
				System.out.println("-----------------------\n");
			}
		} while (!subMenuSelection.equals("0"));
	}
}
