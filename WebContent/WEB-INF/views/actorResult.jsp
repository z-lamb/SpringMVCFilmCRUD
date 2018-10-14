<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actor Detail</title>
</head>
<body>
	<form action="index.html">
		<input type="submit" value="Home">
	</form>
	<h3>Actor Detail</h3>
	<c:choose>
		<c:when test="${! empty actor }">
			<ul>
				<li><strong>Actor ID: </strong> ${actor.id }</li>
				<li><strong>First Name: </strong> ${actor.firstName }</li>
				<li><strong>Last Name: </strong> ${actor.lastName }</li>
			</ul>
			<form action="GetFilmsByActorId.do?actorId=${actor.id}" method="post">
				<input type="submit" value="List Films by Actor">
			</form>
		</c:when>
		<c:when test="${! empty films }">
			<ul>
				<li><strong>Actor ID: </strong> ${actor.id }</li>
				<li><strong>First Name: </strong> ${actor.firstName }</li>
				<li><strong>Last Name: </strong> ${actor.lastName }</li>
				<c:forEach var="f" items="${films }">
					<hr>
					<li><strong>Film ID:</strong> ${f.id}</li>
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
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>NO ACTOR FOUND</p>
		</c:otherwise>
	</c:choose>
</body>
</html>