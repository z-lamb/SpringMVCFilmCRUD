package com.skilldistillery.film.controllers;

public class FilmQueryMenus {

	/*
	 * Display main menu
	 */
	protected void mainMenu() {

		System.out.println("+----------------------------------+");
		System.out.println("|            MAIN MENU             |");
		System.out.println("+----------------------------------+");
		System.out.println("Choose from the menu options below");
		System.out.println("1. Look up a film by its id");
		System.out.println("2. Look up a film by a search keyword");
		System.out.println("0. Exit");
		System.out.print(">> ");
	}

	/*
	 * Display sub menu
	 */
	protected void subMenu() {

		System.out.println("+----------------------------------+");
		System.out.println("|           DETAILS MENU           |");
		System.out.println("+----------------------------------+");
		System.out.println("Choose from the menu options below");
		System.out.println("1. See all film details");
		System.out.println("0. Return to main menu");
		System.out.print(">> ");
	}
}
