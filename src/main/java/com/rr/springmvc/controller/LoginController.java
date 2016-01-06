package com.rr.springmvc.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String searchHome(Locale locale, Model model) {		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );

		
		return "login";
	}
	 
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ModelAndView searchResults(Locale locale, Model model,@RequestParam(value="pass") String password, @RequestParam(value="un") String userName) {		
//		if(!StringUtils.isNullOrEmpty(password)&&!StringUtils.isNullOrEmpty(userName)){
//
//			
//			String token = LoginService.verifyAccount(userName,password);
//			if(token==null){
//				ModelAndView modelAndView = new  ModelAndView("login");
//				modelAndView.addObject("error",Constants.ERROR_LOGIN);
//				return modelAndView;
//			}
//			ModelAndView modelAndView = new  ModelAndView("search/results");
//			
//
//			
//			return modelAndView;
//			
//		}else return new ModelAndView("redirect:search/index");
//	}
	
}
