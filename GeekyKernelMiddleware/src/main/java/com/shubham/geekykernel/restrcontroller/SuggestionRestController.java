package com.shubham.geekykernel.restrcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.geekykernel.dao.SuggestionDAO;
import com.shubham.geekykernel.domain.Blog;
import com.shubham.geekykernel.domain.Suggestion;

@RestController
public class SuggestionRestController {
	
	@Autowired
	SuggestionDAO suggestionDAO;
	@PostMapping("/addSuggestion")
	public ResponseEntity<Suggestion> AddSuggestion(@RequestBody Suggestion suggestion)
	{
		
		
		if(suggestionDAO.addSuggestion(suggestion))
		{
			return new ResponseEntity<Suggestion>(suggestion,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Suggestion>(suggestion,HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/listSuggestions")
	public ResponseEntity<List<Suggestion>> listSuggestions()
	{
		List<Suggestion> listSuggestions=suggestionDAO.list();
		if(listSuggestions.size()>0)
		{
			return new ResponseEntity<List<Suggestion>>(listSuggestions,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Suggestion>>(listSuggestions,HttpStatus.NOT_FOUND);
		}
	
	}
	


}
