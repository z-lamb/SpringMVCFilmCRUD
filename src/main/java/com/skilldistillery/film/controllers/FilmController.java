package com.skilldistillery.film.controllers;

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

	@RequestMapping(path = "NewFilm.do", method = RequestMethod.POST)
	public ModelAndView newFilm(Film f) {
		filmDAO.addFilm(f);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		return null;
	}

	@RequestMapping(path = "NewFilm.do", method = RequestMethod.POST)
	public String newFilm(Film f, RedirectAttributes redir) {
		filmDAO.addFilm(f);
		redir.addFlashAttribute("film", f);
		return "redirect:filmAdded.do";
	}

	@RequestMapping("filmAdded.do")
	public ModelAndView filmAdded() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		return mv;
	}
}
