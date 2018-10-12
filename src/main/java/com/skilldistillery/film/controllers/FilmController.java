package com.skilldistillery.film.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAOImpl;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private FilmDAOImpl filmDAO;

	public void setFilmDAO(FilmDAOImpl filmDAO) {
		this.filmDAO = filmDAO;
	}

	@RequestMapping(path = "GetFilmData.do", params = "id", method = RequestMethod.GET)
	public ModelAndView getFilmById(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film f = null;
		try {
			f = filmDAO.getFilmById(filmId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
}
