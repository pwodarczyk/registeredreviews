package com.rr.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rr.springmvc.dao.QuoteDao;
import com.rr.springmvc.model.QuoteRequest;

@Service("quoteService")
@Transactional
public class QuoteServiceImpl implements QuoteService {


	@Autowired
	private QuoteDao dao;

	public QuoteRequest getQuoteById(Integer id) {
		if(id!=null)
			dao.findById(id);
		return null;
	}

	public Long save(QuoteRequest quoteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
