package com.skilldistillery.film.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String newFilm(Film f, RedirectAttributes redir) {
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
	
	@RequestMapping(path = "FilmUpdatePage.do", params = "filmId", method = RequestMethod.POST)
	public ModelAndView filmUpdatePage(int filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/update.jsp");
		mv.addObject("film", filmDAO.getFilmById(filmId));
		return mv;
	}

	@RequestMapping(path = "UpdateFilm.do", params = "filmId", method = RequestMethod.POST)
	public String updateFilm(int filmId, @RequestParam Map<String, String> allRequestParams, RedirectAttributes redir) {
		try {
			//Film f = filmDAO.getFilmById(Integer.parseInt(allRequestParams.get("id")));
			Film f = filmDAO.getFilmById(filmId);
			f.setTitle(allRequestParams.get("title"));
			f.setDescription(allRequestParams.get("description"));
			f.setReleaseYear(Short.parseShort(allRequestParams.get("releaseYear")));
			f.setLanguageId(Integer.parseInt(allRequestParams.get("languageId")));
			f.setRentalDuration(Integer.parseInt(allRequestParams.get("rentalDuration")));
			f.setRentalRate(Double.parseDouble(allRequestParams.get("rentalRate")));
			f.setLength(Integer.parseInt(allRequestParams.get("length")));
			f.setReplacementCost(Double.parseDouble(allRequestParams.get("replacementCost")));
			f.setRating(allRequestParams.get("rating"));
			f.setSpecialFeatures(allRequestParams.get("specialFeatures"));
			f.setCategory(allRequestParams.get("category"));
			f.setNumberOfNew(Integer.parseInt(allRequestParams.get("numberOfNew")));
			f.setNumberOfUsed(Integer.parseInt(allRequestParams.get("numberOfUsed")));
			f.setNumberOfDamaged(Integer.parseInt(allRequestParams.get("numberOfDamaged")));
			f.setNumberOfLost(Integer.parseInt(allRequestParams.get("numberOfLost")));
			f.setNumberOfNA(Integer.parseInt(allRequestParams.get("numberOfNA")));
			filmDAO.updateFilm(f);
			redir.addFlashAttribute("film", filmDAO.getFilmById(filmId));
		} catch (NumberFormatException e) {
			redir.addFlashAttribute("updateMessage", "Failed to update the film");
		}

		return "redirect:filmUpdated.do";
	}

	@RequestMapping("filmUpdated.do")
	public ModelAndView filmUpdated() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/result.jsp");
		return mv;
	}

}
