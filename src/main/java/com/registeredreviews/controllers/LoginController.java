package com.registeredreviews.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.registeredreviews.mobile.Constants;
import com.registeredreviews.service.LoginManager;
import com.registeredreviews.util.StringUtils;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String searchHome(Locale locale, Model model) {		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("isHomePage", true);
		
		return "search/index";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView searchResults(Locale locale, Model model,@RequestParam(value="pass") String password, @RequestParam(value="un") String userName) {		
		if(!StringUtils.isNullOrEmpty(password)&&!StringUtils.isNullOrEmpty(userName)){

			
			String token = LoginManager.verifyAccount(userName,password);
			if(token==null){
				ModelAndView modelAndView = new  ModelAndView("login");
				modelAndView.addObject("error",Constants.ERROR_LOGIN);
				return modelAndView;
			}
			ModelAndView modelAndView = new  ModelAndView("search/results");
			

			
			return modelAndView;
			
		}else return new ModelAndView("redirect:search/index");
	}
	
}
