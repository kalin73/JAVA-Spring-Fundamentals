package com.softuni.mobilele.domain.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.softuni.mobilele.domain.enums.Engine;
import com.softuni.mobilele.domain.enums.Transmission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
	@Column
	private String description;

	@Enumerated(EnumType.STRING)
	private Engine engine;

	@Column
	private String mileage;

	@Column
	private BigDecimal price;

	@Column
	private Transmission transmission;

	@Column
	private String year;

	@Column
	private Date created;

	@Column
	private Date modified;

	@ManyToOne
	private Model model;

	@ManyToOne
	private User seller;

	public Offer() {

	}

	public Offer(String description, Engine engine, String mileage, BigDecimal price, Transmission transmission,
			String year, Date created, Date modified, Model model, User seller) {
		this.description = description;
		this.engine = engine;
		this.mileage = mileage;
		this.price = price;
		this.transmission = transmission;
		this.year = year;
		this.created = created;
		this.modified = modified;
		this.model = model;
		this.seller = seller;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
}
