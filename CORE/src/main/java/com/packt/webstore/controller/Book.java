package com.packt.webstore.controller;

import org.apache.solr.client.solrj.beans.Field;

public class Book {
	@Field
	private String id;
    @Field
	private String title_t;
    @Field
    private String author_s;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle_t() {
		return title_t;
	}
	public void setTitle_t(String title_t) {
		this.title_t = title_t;
	}
	public String getAuthor_s() {
		return author_s;
	}
	public void setAuthor_s(String author_s) {
		this.author_s = author_s;
	}
    
}
