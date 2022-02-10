package com.example.restservice.metrics.impl;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class AbstractMetricCalculator implements ILoanMetricCalculator {

    private static final int PERIOD = 12;
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    @Override
    public boolean isSupported(Loan loan) {
        return ILoanMetricCalculator.super.isSupported(loan);
    }

    @Override
    public LoanMetric getLoanMetric(Loan loan) {
        return new LoadMetricBuilder()
                .monthlyInterestRate(this.getMonthlyInterestRate(loan))
                .monthlyPayment(this.getMonthlyPayment(loan))
                .build();
    }

    protected Double getMonthlyInterestRate(Loan loan){
        double monthlyInterest = loan.getAnnualInterest() / PERIOD;
        return BigDecimal.valueOf(monthlyInterest).divide(ONE_HUNDRED).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    abstract Double getMonthlyPayment(Loan loan);

    public static class LoadMetricBuilder {
        private Double monthlyInterestRate;
        private Double monthlyPayment;

        public LoadMetricBuilder monthlyInterestRate(Double monthlyInterestRate){
            this.monthlyInterestRate = monthlyInterestRate;
            return this;
        }

        public LoadMetricBuilder monthlyPayment(Double monthlyPayment){
            this.monthlyPayment = monthlyPayment;
            return this;
        }

        public LoanMetric build(){
            return new LoanMetric(this.monthlyInterestRate, monthlyPayment);
        }
    }

}
