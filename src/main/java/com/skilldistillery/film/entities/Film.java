package com.skilldistillery.film.entities;

import java.util.List;

public class Film {
	
	/*
	 * Private Film fields
	 */
	private int id;
	private String title;
	private String description;
	private short releaseYear;
	private int languageId;
	private String languageName;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private String category;
	private List<Actor> actors;
	private List<Film> conditionList;
	private int numberOfNew;
	private int numberOfUsed;
	private int numberOfDamaged;
	private int numberOfLost;
	private int numberOfNA;
	
	/*
	 * Items generated for Actor Class
	 * Getters and Setters
	 * Hashcode and Equals
	 * To String
	 * Constructors
	 */

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Film> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<Film> conditionList) {
		this.conditionList = conditionList;
	}

	public int getNumberOfNew() {
		return numberOfNew;
	}

	public void setNumberOfNew(int numberOfNew) {
		this.numberOfNew = numberOfNew;
	}

	public int getNumberOfUsed() {
		return numberOfUsed;
	}

	public void setNumberOfUsed(int numberOfUsed) {
		this.numberOfUsed = numberOfUsed;
	}

	public int getNumberOfDamaged() {
		return numberOfDamaged;
	}

	public void setNumberOfDamaged(int numberOfDamaged) {
		this.numberOfDamaged = numberOfDamaged;
	}

	public int getNumberOfLost() {
		return numberOfLost;
	}

	public void setNumberOfLost(int numberOfLost) {
		this.numberOfLost = numberOfLost;
	}

	public int getNumberOfNA() {
		return numberOfNA;
	}

	public void setNumberOfNA(int numberOfNA) {
		this.numberOfNA = numberOfNA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((conditionList == null) ? 0 : conditionList.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + languageId;
		result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
		result = prime * result + length;
		result = prime * result + numberOfDamaged;
		result = prime * result + numberOfLost;
		result = prime * result + numberOfNA;
		result = prime * result + numberOfNew;
		result = prime * result + numberOfUsed;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + rentalDuration;
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(replacementCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialFeatures == null) ? 0 : specialFeatures.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (conditionList == null) {
			if (other.conditionList != null)
				return false;
		} else if (!conditionList.equals(other.conditionList))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (languageId != other.languageId)
			return false;
		if (languageName == null) {
			if (other.languageName != null)
				return false;
		} else if (!languageName.equals(other.languageName))
			return false;
		if (length != other.length)
			return false;
		if (numberOfDamaged != other.numberOfDamaged)
			return false;
		if (numberOfLost != other.numberOfLost)
			return false;
		if (numberOfNA != other.numberOfNA)
			return false;
		if (numberOfNew != other.numberOfNew)
			return false;
		if (numberOfUsed != other.numberOfUsed)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (rentalDuration != other.rentalDuration)
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(replacementCost) != Double.doubleToLongBits(other.replacementCost))
			return false;
		if (specialFeatures == null) {
			if (other.specialFeatures != null)
				return false;
		} else if (!specialFeatures.equals(other.specialFeatures))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
				+ ", languageId=" + languageId + ", languageName=" + languageName + ", rentalDuration=" + rentalDuration
				+ ", rentalRate=" + rentalRate + ", length=" + length + ", replacementCost=" + replacementCost
				+ ", rating=" + rating + ", specialFeatures=" + specialFeatures + ", category=" + category + ", actors="
				+ actors + ", conditionList=" + conditionList + ", numberOfNew=" + numberOfNew + ", numberOfUsed="
				+ numberOfUsed + ", numberOfDamaged=" + numberOfDamaged + ", numberOfLost=" + numberOfLost
				+ ", numberOfNA=" + numberOfNA + "]";
	}

	public Film(int id, String title, String description, short releaseYear, int languageId, String languageName,
			int rentalDuration, double rentalRate, int length, double replacementCost, String rating,
			String specialFeatures, String category, List<Actor> actors, List<Film> conditionList, int numberOfNew,
			int numberOfUsed, int numberOfDamaged, int numberOfLost, int numberOfNA) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.languageName = languageName;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.category = category;
		this.actors = actors;
		this.conditionList = conditionList;
		this.numberOfNew = numberOfNew;
		this.numberOfUsed = numberOfUsed;
		this.numberOfDamaged = numberOfDamaged;
		this.numberOfLost = numberOfLost;
		this.numberOfNA = numberOfNA;
	}

	public Film() {
		super();
	}

}
