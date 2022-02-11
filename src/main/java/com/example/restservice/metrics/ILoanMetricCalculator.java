package com.example.restservice.metrics;

import com.example.restservice.metrics.business.Metric;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public interface ILoanMetricCalculator {

	/**
	 * Validates if a loan is supported to calculate metrics
	 * 
	 * @param loan
	 */
	public default boolean isSupported(Loan loan) {
		return Optional.ofNullable(loan)
				.filter(l -> StringUtils
						.equalsIgnoreCase(this.getClass().getAnnotation(Metric.class).value().name, l.getType()))
				.isPresent();
	}

	/**
	 * Calculates the Loan Metric of a Loan entity
	 * 
	 * @param loan
	 */
	public LoanMetric getLoanMetric(Loan loan);

}
