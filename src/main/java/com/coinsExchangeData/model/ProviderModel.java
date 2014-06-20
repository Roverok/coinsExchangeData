package com.coinsExchangeData.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "provider")
@NamedQueries({ 
	@NamedQuery(name = "ProviderModel.findByName", 
			query = "SELECT pm FROM ProviderModel pm WHERE pm.name = :name"),
})
public class ProviderModel {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;

	public ProviderModel() {
	}
	
	public ProviderModel(String name) {
		this.setName(name);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
