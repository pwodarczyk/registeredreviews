package com.registeredreviews.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.xalan.lib.Redirect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {
	
	@RequestMapping(value = "/review/add", method = RequestMethod.GET)
	public String addReview(Locale locale, Model model) {		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
				
		return "review/add";
	}
	@RequestMapping(value = "/review/save", method = RequestMethod.POST)
	public ModelAndView saveReview(Locale locale, Model model,@RequestParam(value="target") String target, @RequestParam(value="q") String q) {		
		 return new ModelAndView("review/index");
	}
	@RequestMapping(value = "/review/dispute", method = RequestMethod.POST)
	public ModelAndView disputeReview(Locale locale, Model model,@RequestParam(value="target") String target, @RequestParam(value="q") String q) {		
		return new ModelAndView("review/index");
	}
	
}
