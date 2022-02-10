package com.example.restservice.controller;

import com.example.restservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController()
@Validated
public class LoanController {

	@Autowired
	private LoanService loanService;

	@GetMapping("/loan/{id}")
	public Loan getLoan(@PathVariable(value = "id") Long loanId) {
		return Optional.ofNullable(this.loanService.getLoan(loanId)).orElseThrow(() -> new IllegalStateException("Loan not found"));
	}

	@GetMapping("/loan/{id}/metric")
	public LoanMetric calculateLoanMetric(@PathVariable(value = "id") Long loanId) {
		return Optional.ofNullable(this.loanService.calculateLoanMetric(loanId))
				.orElseThrow(() -> new IllegalStateException("Cannot get a Metric for given LoanId"));
	}

	@PostMapping("/loan/metric")
	public LoanMetric calculateLoanMetric(Loan loan) {
		return Optional.ofNullable(this.loanService.calculateLoanMetric(loan))
				.orElseThrow(() -> new IllegalStateException("Cannot get a Metric for given Loan"));
	}
	@GetMapping("/loan/max-monthly-payment-loan")
	public Loan getMaxMonthlyPaymentLoan() {
		return Optional.ofNullable(this.loanService.getMaxMonthlyPaymentLoan())
				.orElseThrow(() -> new IllegalStateException("Cannot calculate Max Monthly Payment Loan"));
	}

}
