package com.shubham.geekykernel.dao;

import java.util.List;

import com.shubham.geekykernel.domain.Suggestion;

public interface SuggestionDAO {
	
	public boolean addSuggestion(Suggestion suggestion);
	public List<Suggestion> list();

}
