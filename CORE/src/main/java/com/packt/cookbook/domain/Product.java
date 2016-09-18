package com.packt.cookbook.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Product implements Serializable{

	private static final long serialVersionUID = 327112233223169566L;

	private int id_p;
	
	private String name;
	
	@SuppressWarnings("deprecation")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Float quantity;
	
	@SuppressWarnings("deprecation")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String type;
	
	public int getId_p() {
		return id_p;
	}

	public void setId_p(int id_p) {
		this.id_p = id_p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + id_p;
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
	    Product other = (Product) obj;
	    if (id_p != other.id_p)
	        return false;
	    return true;
	}

}
