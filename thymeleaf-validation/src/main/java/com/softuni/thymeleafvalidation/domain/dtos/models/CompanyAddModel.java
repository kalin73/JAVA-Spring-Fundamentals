package com.softuni.thymeleafvalidation.domain.dtos.models;

import java.math.BigDecimal;

import com.softuni.thymeleafvalidation.domain.entities.Company;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyAddModel {
	
	@NotNull
	@DecimalMin(value = "1")
	private BigDecimal budget;

	@Size(min = 10, max = 23423423)
	private String description;

	@Size(min = 2, max = 10)
	private String name;

	@Size(min = 2, max = 10)
	private String town;

	public Company toCompany() {
		return Company.builder().budget(budget).description(description).name(name).town(town).build();
	}
}
