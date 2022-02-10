package com.example.restservice.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.metrics.impl.AbstractMetricCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratonUtil;

@Service
public class LoanService {

	@Autowired
	private LoanMetricFactory loanMetricFactory;

	public Loan getLoan(Long id) {
		return LoanGeneratonUtil.createLoan(id);
	}

	public LoanMetric calculateLoanMetric(Loan loan) {
		return Optional.ofNullable(loan)
				.filter(this::isSupported)
				.map(this.loanMetricFactory::getInstance)
				.map(m -> m.getLoanMetric(loan))
				.get();


	}

	public LoanMetric calculateLoanMetric(Long loanId) {
		return Optional.ofNullable(this.getLoan(loanId))
				.map(this::calculateLoanMetric)
				.get();
	}

	public Loan getMaxMonthlyPaymentLoan() {
		List<Loan> allLoans = LoanGeneratonUtil.getRandomLoans(20L);
		/*
		En este punto tengo Loans y por cada uno debo calcular su Metric y quedarme con aquella cuyo metric tenga el mayor Monthly Payment.

		Creo un Map cuya Key es un Loan y Value el LoanMetric
		allLoans.stream()
				.collect(Collectors.toMap(Function.identity(), this::calculateLoanMetric))

		Me quedo con el EntrySet de del Map y lo streemeo para quedarme con el Value (LoanMetric) cuyo MonthlyPayment es mayor

		Una vez tengo el Entry<Loan, LoanMetric> hago el getKey, que en este caso retorna el Loan
		*/

		return allLoans.stream()
				.collect(Collectors.toMap(Function.identity(), this::calculateLoanMetric))
				.entrySet()
				.stream()
				.max(Comparator.comparing(entry -> entry.getValue().getMonthlyPayment()))
				.get()
				.getKey();

	}

	private boolean isSupported(Loan loan) {
		return Optional.ofNullable(loan)
				.map(this.loanMetricFactory::getInstance)
				.filter(m -> m.isSupported(loan))
				.isPresent();
	}
}
