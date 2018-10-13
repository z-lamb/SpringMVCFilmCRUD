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
	public String updateFilm(int filmId, Film f) {
		f.setId(filmId);
		System.out.println(f.getLanguageId());
		//f.setLanguageId(1);
		boolean updateSucceeded = filmDAO.updateFilm(f);

		if (updateSucceeded) {
			System.out.println("It worked!");
		} else {
			System.out.println("It failed!");
		}

		return "redirect:filmUpdated.do";
	}
	/*
	 * @RequestMapping(path = "FilmUpdatePage.do", method = RequestMethod.POST)
	 * public ModelAndView filmUpdatePage(@RequestParam int filmId,
	 * 
	 * @RequestParam(value="title") String title,
	 * 
	 * @RequestParam(value="description") String description,
	 * 
	 * @RequestParam(value="releaseYear") short releaseYear,
	 * 
	 * @RequestParam(value="languageId") int languageId,
	 * 
	 * @RequestParam(value="rentalDuration") int rentalDuration,
	 * 
	 * @RequestParam(value="rentalRate") double rentalRate,
	 * 
	 * @RequestParam(value="length") int length,
	 * 
	 * @RequestParam(value="replacementCost") double replacementCost,
	 * 
	 * @RequestParam(value="rating") String rating,
	 * 
	 * @RequestParam(value="specialFeatures") String specialFeatures) { ModelAndView
	 * mv = new ModelAndView(); mv.setViewName("WEB-INF/views/update.jsp"); return
	 * mv; }
	 */
	/*
	 * @RequestMapping(path = "UpdateFilm.do", method = RequestMethod.POST) public
	 * String updateFilm(@RequestParam(value="filmId") int filmId,
	 * 
	 * @RequestParam(value="title") String title,
	 * 
	 * @RequestParam(value="description") String description,
	 * 
	 * @RequestParam(value="releaseYear") short releaseYear,
	 * 
	 * @RequestParam(value="languageId") int languageId,
	 * 
	 * @RequestParam(value="rentalDuration") int rentalDuration,
	 * 
	 * @RequestParam(value="rentalRate") double rentalRate,
	 * 
	 * @RequestParam(value="length") int length,
	 * 
	 * @RequestParam(value="replacementCost") double replacementCost,
	 * 
	 * @RequestParam(value="rating") String rating,
	 * 
	 * @RequestParam(value="specialFeatures") String specialFeatures) { return
	 * "redirect:filmUpdated.do"; }
	 */

	@RequestMapping("filmUpdated.do")
	public ModelAndView filmUpdated() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/result.jsp");
		return mv;
	}

}
