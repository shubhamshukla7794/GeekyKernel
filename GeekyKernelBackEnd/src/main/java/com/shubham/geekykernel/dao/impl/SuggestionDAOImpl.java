package com.shubham.geekykernel.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.SuggestionDAO;
import com.shubham.geekykernel.domain.Suggestion;

@Repository("suggestionDAO")
@Transactional
public class SuggestionDAOImpl implements SuggestionDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	public boolean addSuggestion(Suggestion suggestion) {
		try {
			 
			suggestion.setAdded_date(new Date(System.currentTimeMillis()));
			sessionFactory.getCurrentSession().save(suggestion);
		}
		catch (Exception e) {
			// print the complete exception stack trace
			e.printStackTrace();
			return false;
		}
		return true;
}



	public List<Suggestion> list() {
		return sessionFactory.getCurrentSession().createQuery("from Suggestion").list();
	}

}
