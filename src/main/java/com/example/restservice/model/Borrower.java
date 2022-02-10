package com.example.restservice.model;

public class Borrower {

	private String name;
	private Integer age;
	private Double annualIncome;
	private Boolean delinquentDebt;
	private Double annualDebt;
	private Integer creditHistory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public Boolean getDelinquentDebt() {
		return delinquentDebt;
	}

	public void setDelinquentDebt(Boolean delinquentDebt) {
		this.delinquentDebt = delinquentDebt;
	}

	public Double getAnnualDebt() {
		return annualDebt;
	}

	public void setAnnualDebt(Double annualDebt) {
		this.annualDebt = annualDebt;
	}

	public Integer getCreditHistory() {
		return creditHistory;
	}

	public void setCreditHistory(Integer creditHistory) {
		this.creditHistory = creditHistory;
	}

}