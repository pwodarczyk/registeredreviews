package com.rr.springmvc.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rr.springmvc.model.QuoteRequest;
import com.rr.springmvc.service.QuoteService;

@Controller
@RequestMapping("/quote")
public class QuoteController {
	
	@Autowired
	QuoteService quoteService;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getAQuote(Locale locale, Model model) {		
	
		
		model.addAttribute("quoteRequest", new QuoteRequest());
		
		return "quote/index";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String  saveQuote(@Valid QuoteRequest quoteRequest, BindingResult result,
			ModelMap model) {	
		
		if (result.hasErrors()) {
			return "quote/index";
		}
		Date date = new Date();
		quoteRequest.setDateRequested(date);

		

		//if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			//FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
		    //result.addError(ssnError);
			//return "employees/registration";
		//}
		

		model.addAttribute("success", "Thank You " + quoteRequest.getName() + " registered successfully");		
		quoteService.save(quoteRequest);
		
		
		//TODO trigger business email
		return "quote/success";
	}
	
	
}
