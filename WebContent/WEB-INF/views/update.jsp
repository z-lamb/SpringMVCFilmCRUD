<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Film Detail</title>
</head>
<body>
	<a href="index.html">Home Page</a>
	<h3>Update Film Detail</h3>

	<h4>Please provide the following details:</h4>
		<form action="NewFilm.do" method="post">
			<label for="title">Title:</label> <input type="text" name="title" value="${film.title }"><br>
			<label for="description">Description:</label> <input type="text" name="description" value="${film.description }"><br> 
			<label for="releaseYear">Release Year:</label> <input type="text" name="releaseYear" size="4" value="${film.releaseYear }"><br> 
			<label for="languageId">Language ID:</label> 
				<select>
				  <option value="1">1: English</option>
				  <option value="2">2: Italian</option>
				  <option value="3">3: Japanese</option>
				  <option value="4">4: Mandarin</option>
				  <option value="5">5: French</option>
				  <option value="6">6: German</option>
				</select><br>
			<label for="rentalDuration">Rental Duration:</label> <input type="text" name="rentalDuration" size="4" value="${film.rentalDuration }"><br> 
			<label for="rentalRate">Rental Rate:</label> <input type="text" name="rentalRate" value="${film.rentalRate }"><br> 
			<label for="length">Length:</label> <input type="text" name="length" size="4" value="${film.length }"><br> 
			<label for="replacementCost">Replacement Cost:</label> <input type="text" name="replacementCost" value="${film.replacementCost }"><br> 
			<label for="rating">Rating:</label> 
				<select>
				  <option value="G">G</option>
				  <option value="PG">PG</option>
				  <option value="PG13">PG 13</option>
				  <option value="R">R</option>
				  <option value="NC17">NC 17</option>
				</select><br>
			<label for="specialFeatures">Special Features:</label> 
				<select>
				  <option value="Trailers">Trailers</option>
				  <option value="Commentaries">Commentaries</option>
				  <option value="DeletedScenes">Deleted Scenes</option>
				  <option value="BehindTheScenes">Behind The Scenes</option>
				</select><br>
			<input type="submit" value="Update">
		</form>

</body>
</html>