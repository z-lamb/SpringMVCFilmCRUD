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
<h3>Film Detail</h3>
  <c:choose>
    <c:when test="${! empty film}">
      <ul>
        <li><strong>ID:</strong> ${film.id}</li>
        <li><strong>Title:</strong> ${film.title}</li>
        <li><strong>Description:</strong> ${film.description}</li>
        <li><strong>Release Year:</strong> ${film.releaseYear}</li>
        <li><strong>Rating:</strong> ${film.rating}</li>
      </ul>
    </c:when>
    <c:when test="${! empty films }">
    	<ul>
    		<c:forEach var="f" items="${films }">
    		    <li><strong>ID:</strong> ${f.id}</li>
		        <li><strong>Title:</strong> ${f.title}</li>
		        <li><strong>Description:</strong> ${f.description}</li>
		        <li><strong>Release Year:</strong> ${f.releaseYear}</li>
		        <li><strong>Rating:</strong> ${f.rating}</li>
    		</c:forEach>
    	</ul>
    </c:when>
    <c:otherwise>
      <p>NO FILM FOUND</p>
    </c:otherwise>
  </c:choose>
</body>
</html>