package com.packt.cookbook.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/solr")
public class SOLRController {
	
	//@Resource
	//private SolrTemplate solrTemplate;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> test(HttpServletRequest request){
		String[] words = {"book1"};
		Criteria conditions = null;
		try {
			conditions = new Criteria("id").contains("book1");
			SimpleQuery search = new SimpleQuery(conditions);
			//ScoredPage<Book> result = solrTemplate.queryForPage(search, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("test", HttpStatus.OK);
	}
}
