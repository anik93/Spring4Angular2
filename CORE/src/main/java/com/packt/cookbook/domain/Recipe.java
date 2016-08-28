package com.packt.cookbook.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Recipe implements Serializable{

	private static final long serialVersionUID = -5198862637974450505L;

	private int id_re;
	
	private String name;
	
	private int time;
	
	private String level;
	
	private Float rating;
	
	private String linkPhoto;
	
	@JsonIgnore
	private boolean approve;
	
	private Set<Section> listOfSection = new HashSet<>();
	
	@SuppressWarnings("deprecation")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Set<ProductRecipe> listOfRecipe_Product = new HashSet<>(0);
	
	private List<Product> listOfProduct = new LinkedList<>();
	
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
	
	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
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

	public Set<ProductRecipe> getListOfRecipe_Product() {
		return listOfRecipe_Product;
	}

	public void setListOfRecipe_Product(Set<ProductRecipe> listOfRecipe_Product) {
		this.listOfRecipe_Product = listOfRecipe_Product;
	}
	
	public List<Product> getListOfProduct() {
		return listOfProduct;
	}

	public void setListOfProduct(List<Product> listOfProduct) {
		this.listOfProduct = listOfProduct;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + id_re;
	    return result;
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Recipe other = (Recipe) obj;
	    if (id_re != other.id_re)
	        return false;
	    return true;
	}

}
