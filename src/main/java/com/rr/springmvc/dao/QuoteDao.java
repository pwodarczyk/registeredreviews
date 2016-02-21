package com.rr.springmvc.dao;

import java.util.List;

import com.rr.springmvc.model.QuoteRequest;

public interface QuoteDao {

	QuoteRequest findById(Integer id);

	void save(QuoteRequest employee);
	
	List<QuoteRequest> findAllQuotes(String businessId);


}
