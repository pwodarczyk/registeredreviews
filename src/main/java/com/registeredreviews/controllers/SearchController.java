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
public class SearchController {
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchHome(Locale locale, Model model) {		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
				
		return "search/index";
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchResults(Locale locale, Model model,@RequestParam(value="target") String target, @RequestParam(value="q") String q) {		
		if(target == "business"){

			ModelAndView modelAndView = new  ModelAndView("search/results");
			

			
			return modelAndView;
			
		}else return new ModelAndView("redirect:search/index");
	}
	
}
