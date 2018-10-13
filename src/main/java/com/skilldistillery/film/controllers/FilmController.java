package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDAO;

	public void setFilmDAO(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}

	@RequestMapping(path = "GetFilmData.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView getFilmById(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film f = filmDAO.getFilmById(filmId);
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/views/result.jsp");
		return mv;
	}

	@RequestMapping(path = "GetFilmData.do", params = "filmKeyword", method = RequestMethod.GET)
	public ModelAndView getFilmsByKeyword(String filmKeyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = filmDAO.getFilmsByKeyword(filmKeyword);
		mv.addObject("films", films);
		mv.setViewName("WEB-INF/views/result.jsp");
		return mv;
	}

	@RequestMapping(path = "NewFilm.do", method = RequestMethod.POST)
	public ModelAndView newFilm(Film f) {
		filmDAO.addFilm(f);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/result.jsp");
		return mv;
	}

	@RequestMapping(path = "filmAdded.do", method = RequestMethod.POST)
	public String newFilm(Film f, RedirectAttributes redir) {
		Film myFilm = filmDAO.addFilm(f);
		redir.addFlashAttribute("film", myFilm);
		return "redirect:filmAdded.do";
	}

	@RequestMapping("filmAdded.do")
	public ModelAndView filmAdded() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/result.jsp");
		return mv;
	}

	@RequestMapping(path = "DeleteFilm.do", params = "filmId", method = RequestMethod.POST)
	public ModelAndView deleteFilm(int filmId) {
		Film f = filmDAO.getFilmById(filmId);
		boolean deleteSucceeded = filmDAO.deleteFilm(f);
		ModelAndView mv = new ModelAndView();
		String deleteMessage;
		if (deleteSucceeded) {
			deleteMessage = "You successfully deleted the film " + f.getTitle() + " (id " + f.getId() + ")";
		} else {
			deleteMessage = "Could not delete the film " + f.getTitle() + " (id " + f.getId() + ")";
		}
		mv.addObject("deleteMessage", deleteMessage);
		mv.setViewName("WEB-INF/views/result.jsp");
		return mv;
	}
}
