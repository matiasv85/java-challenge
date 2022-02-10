package com.example.restservice.metrics.impl;

import com.example.restservice.metrics.business.LoanType;
import com.example.restservice.metrics.business.Metric;
import com.example.restservice.model.Loan;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Component
@Metric(LoanType.CONSUMER)
public class ConsumerLoanMetricCalculator extends AbstractMetricCalculator {

    @Override
    public boolean isSupported(Loan loan) {
        return Optional.ofNullable(loan)
                .filter(super::isSupported)
                .isPresent();
    }

    @Override
    Double getMonthlyPayment(Loan loan) {
        Double monthlyInterestRate = this.getMonthlyInterestRate(loan);
        Double monthlyPayment = (loan.getRequestedAmount() * monthlyInterestRate)
                / (1 - Math.pow((1 + monthlyInterestRate), ((-1) * loan.getTermMonths())));

        return BigDecimal.valueOf(monthlyPayment).setScale(2, RoundingMode.DOWN).doubleValue();
    }

}
