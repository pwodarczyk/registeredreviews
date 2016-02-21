package com.rr.springmvc.service;

import com.rr.springmvc.model.QuoteRequest;

public interface QuoteService {
	public QuoteRequest getQuoteById(Integer id);
	public Long save(QuoteRequest quoteRequest);
}
