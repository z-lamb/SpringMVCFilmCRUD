<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film Detail</title>
</head>
<body>
	<form action="index.html">
		<input type="submit" value="Home">
	</form>
	<h3>Film Detail</h3>
	<c:choose>
		<c:when test="${! empty deleteMessage }">
			<c:out value="${deleteMessage}" />					
		</c:when>
		<c:when test="${! empty newFilmFailure }">
			<c:out value="${newFilmFailure }" />
		</c:when> 
		<c:when test="${! empty film}">
			<ul>
				<li><strong>ID:</strong> ${film.id}</li>
				<li><strong>Title:</strong> ${film.title}</li>
				<li><strong>Description:</strong> ${film.description}</li>
				<li><strong>Release Year:</strong> ${film.releaseYear}</li>
				<li><strong>Language:</strong> ${film.languageId}</li>
				<li><strong>Rental Duration:</strong> ${film.rentalDuration}</li>
				<li><strong>Rental Rate:</strong> ${film.rentalRate}</li>
				<li><strong>Length:</strong> ${film.length}</li>
				<li><strong>Replacement Cost:</strong> ${film.replacementCost}</li>
				<li><strong>Rating:</strong> ${film.rating}</li>
				<li><strong>Special Features:</strong> ${film.specialFeatures}</li>
			</ul>
						
			<form action="FilmUpdatePage.do?filmId=${film.id}" method="post">
				<input type="submit" value="Edit">
			</form>
			<form action="DeleteFilm.do?filmId=${film.id}" method="post">
				<input type="submit" value="Delete">
			</form> 
		</c:when>
		<c:when test="${! empty films }">
			<ul>
				<c:forEach var="f" items="${films }">
					<li><strong>ID:</strong> ${f.id}</li>
					<li><strong>Title:</strong> ${f.title}</li>
					<li><strong>Description:</strong> ${f.description}</li>
					<li><strong>Release Year:</strong> ${f.releaseYear}</li>
					<li><strong>Language:</strong> ${f.languageId}</li>
					<li><strong>Rental Duration:</strong> ${f.rentalDuration}</li>
					<li><strong>Rental Rate:</strong> ${f.rentalRate}</li>
					<li><strong>Length:</strong> ${f.length}</li>
					<li><strong>Replacement Cost:</strong> ${f.replacementCost}</li>
					<li><strong>Rating:</strong> ${f.rating}</li>
					<li><strong>Special Features:</strong> ${f.specialFeatures}</li>
					<form action="UpdateFilm.do?filmId=${film.id}" method="post">
						<input type="submit" value="Edit">
					</form>
					<form action="DeleteFilm.do?filmId=${f.id}" method="post">
						<input type="submit" value="Delete">
					</form> 
					<br>
				</c:forEach>
			</ul>
		</c:when>

		<c:otherwise>
			<p>NO FILM FOUND</p>
		</c:otherwise>
	</c:choose>
</body>
</html>