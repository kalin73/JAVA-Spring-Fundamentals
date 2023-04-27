package com.softuni.thymeleafvalidation.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.thymeleafvalidation.domain.dtos.models.CompanyAddModel;
import com.softuni.thymeleafvalidation.domain.entities.Company;
import com.softuni.thymeleafvalidation.repositories.CompanyRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyController {

	private final CompanyRepository companyRepository;

	public CompanyController(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@GetMapping("/add")
	public String getAddPage() {
		return "company-add";
	}

	@PatchMapping("/add")
	public ModelAndView patchCompany(@Valid @ModelAttribute(name = "companyAddModel") CompanyAddModel companyAddModel,
			BindingResult bindingResult, ModelAndView modelAndView) {
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("company-add");
			modelAndView.addObject("companyAddModel", companyAddModel);

			return modelAndView;
		}
		this.companyRepository.saveAndFlush(companyAddModel.toCompany());
		modelAndView.setViewName("redirect:/companies/all");

		return modelAndView;
	}

	@GetMapping("/all")
	public String getAllCompanyPage() {
		return "company-all";
	}

	@GetMapping("/{id}")
	public ModelAndView getCompanyPage(@PathVariable(name = "id") Long id, ModelAndView modelAndView) {
		Company company = this.companyRepository.findById(id).orElseThrow();
		modelAndView.setViewName("company-details");
		modelAndView.addObject("company", company);

		return modelAndView;

	}

	@DeleteMapping("/{id}")
	public String deleteCompany(@PathVariable(name = "id") Long id) {
		this.companyRepository.deleteById(id);

		return "redirect:all";
	}

	@ModelAttribute(name = "companyAddModel")
	public CompanyAddModel initCompanyAddModel() {
		return new CompanyAddModel();
	}

	@ModelAttribute(name = "allCompanies")
	public List<Company> listOfAllCompanies() {
		return this.companyRepository.findAll();
	}
}
