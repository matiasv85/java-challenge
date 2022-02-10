package com.example.restservice.metrics.impl;

import com.example.restservice.metrics.business.LoanType;
import com.example.restservice.metrics.business.Metric;
import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;


@Component
@Metric(LoanType.STUDENT)
public class StudentLoanMetricCalculator extends AbstractMetricCalculator {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 30;

    private static final double RATIO = 0.8;

    @Override
    public boolean isSupported(Loan loan) {
        return Optional.ofNullable(loan)
                .filter(super::isSupported)
                .map(Loan::getBorrower)
                .map(Borrower::getAge)
                .filter(age -> age > MIN_AGE)
                .filter(age -> age < MAX_AGE)
                .isPresent();
    }


    /*
      monthlyPayment = 0.8 * (requestedAmount * monthlyInterestRate) / (1 - (1 + monthlyInterestRate) ^ ((-1) * termMonths) )
    */
    Double getMonthlyPayment(Loan loan){
        Double monthlyInterestRate = this.getMonthlyInterestRate(loan);
        Double monthlyPayment = RATIO * (loan.getRequestedAmount() * monthlyInterestRate)
                / (1 - Math.pow((1 + monthlyInterestRate), ((-1) * loan.getTermMonths())));

        return BigDecimal.valueOf(monthlyPayment).setScale(2, RoundingMode.DOWN).doubleValue();
    }



}
