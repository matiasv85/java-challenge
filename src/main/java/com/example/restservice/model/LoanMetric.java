package com.example.restservice.model;

public class LoanMetric {

	private Double monthlyInterestRate;
	private Double monthlyPayment;

	public LoanMetric(double monthlyInterestRate, double monthlyPayment) {
		this.monthlyInterestRate = monthlyInterestRate;
		this.monthlyPayment = monthlyPayment;
	}

	public Double getMonthlyInterestRate() {
		return monthlyInterestRate;
	}

	public void setMonthlyInterestRate(Double monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}

	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(Double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

}