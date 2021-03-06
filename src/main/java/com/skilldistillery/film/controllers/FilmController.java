package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Actor;
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
		if (f != null) {
			mv.addObject("numberOfNew", f.getConditionList().get(0).getNumberOfNew());
			mv.addObject("numberOfUsed", f.getConditionList().get(1).getNumberOfUsed());
			mv.addObject("numberOfUsed", f.getConditionList().get(2).getNumberOfLost());
			mv.addObject("numberOfLost", f.getConditionList().get(3).getNumberOfDamaged());
			mv.addObject("numberOfNA", f.getConditionList().get(4).getNumberOfNA());
		}
		mv.setViewName("filmResult");
		return mv;
	}

	@RequestMapping(path = "GetFilmData.do", params = "filmKeyword", method = RequestMethod.GET)
	public ModelAndView getFilmsByKeyword(String filmKeyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = filmDAO.getFilmsByKeyword(filmKeyword);
		mv.addObject("films", films);
		mv.setViewName("filmResult");
		return mv;
	}

	@RequestMapping(path = "NewFilm.do", method = RequestMethod.POST)
	public String newFilm(Film f, RedirectAttributes redir) {
		f.setLanguageId(1); // default to English
		Film myFilm = filmDAO.addFilm(f);

		if (myFilm == null) {
			redir.addFlashAttribute("newFilmFailure", "Failed to add the new Film");
		} else {
			redir.addFlashAttribute("film", myFilm);
		}

		return "redirect:filmAdded.do";
	}

	@RequestMapping("filmAdded.do")
	public ModelAndView filmAdded() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmResult");
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
		mv.setViewName("filmResult");
		return mv;
	}

	@RequestMapping(path = "FilmUpdatePage.do", params = "filmId", method = RequestMethod.POST)
	public ModelAndView filmUpdatePage(int filmId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", filmDAO.getFilmById(filmId));
		mv.setViewName("update");
		return mv;
	}

	@RequestMapping(path = "UpdateFilm.do", params = "filmId", method = RequestMethod.POST)
	public String updateFilm(int filmId, Film f, RedirectAttributes redir) {
		f.setId(filmId);
		boolean updateSucceeded = filmDAO.updateFilm(f);

		if (!updateSucceeded) {
			redir.addFlashAttribute("updateFilmFailure",
					"Failed to update the film " + f.getTitle() + " with the ID " + f.getId());
		}

		redir.addFlashAttribute("film", f);
		return "redirect:filmUpdated.do";
	}

	@RequestMapping("filmUpdated.do")
	public ModelAndView filmUpdated() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmResult");
		return mv;
	}

	@RequestMapping(path = "GetActorData.do", params = "actorId", method = RequestMethod.GET)
	public ModelAndView getActorById(int actorId) {
		ModelAndView mv = new ModelAndView();
		Actor a = filmDAO.getActorById(actorId);
		mv.addObject("actor", a);
		mv.setViewName("actorResult");
		return mv;
	}

	@RequestMapping(path = "GetFilmsByActorId.do", params = "actorId", method = RequestMethod.POST)
	public ModelAndView getFilmsByActorId(int actorId) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = filmDAO.getFilmsByActorId(actorId);
		mv.addObject("films", films);
		mv.setViewName("actorResult");
		return mv;
	}

}
