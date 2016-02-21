package com.rr.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rr.springmvc.model.QuoteRequest;

@Repository("quoteDao")
public class QuoteDaoImpl extends AbstractDao<Integer, QuoteRequest> implements QuoteDao {

	public QuoteRequest findById(Integer id) {
		if(id!=null){
			return getByKey(id);
		}
		return null;
	}

	public void deleteQuoteRequest(Integer id) {
		Query query = getSession().createSQLQuery("delete from QuoteRequest where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public void save(QuoteRequest quoteRequest) {
		persist(quoteRequest);
		
	}

	public List<QuoteRequest> findAllQuotes(String businessId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("businessId", businessId));
		return (List<QuoteRequest>) criteria.list();
	}
}
