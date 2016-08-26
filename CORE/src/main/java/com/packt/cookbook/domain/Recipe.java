package com.packt.cookbook.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Recipe implements Serializable{

	private static final long serialVersionUID = -7530439859393958513L;

	private int id_re;
	
	private String name;
	
	private int time;
	
	private String level;
	
	@SuppressWarnings("deprecation")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Float rating;
	
	private String linkPhoto;
	
	private Set<Section> listOfSection = new HashSet<>();
	
	private Set<Product> listOfProducts = new HashSet<>();
	
	public int getId_re() {
		return id_re;
	}

	public void setId_re(int id_re) {
		this.id_re = id_re;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	public String getLinkPhoto() {
		return linkPhoto;
	}

	public void setLinkPhoto(String linkPhoto) {
		this.linkPhoto = linkPhoto;
	}

	public Set<Section> getListOfSection() {
		return listOfSection;
	}

	public void setListOfSection(Set<Section> listOfSection) {
		this.listOfSection = listOfSection;
	}

	public Set<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(Set<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

}
