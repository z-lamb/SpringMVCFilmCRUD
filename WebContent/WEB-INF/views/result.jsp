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
        <li>${film.id}</li>
        <li>${film.title}</li>
        <li>${film.description}</li>
        <li>${film.release_year}</li>
        <li>${film.rating}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>NO FILM FOUND</p>
    </c:otherwise>
  </c:choose>
</body>
</html>