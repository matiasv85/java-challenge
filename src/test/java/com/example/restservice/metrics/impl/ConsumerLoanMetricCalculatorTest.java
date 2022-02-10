package com.example.restservice.metrics.impl;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class ConsumerLoanMetricCalculatorTest {

    @Autowired
    private ConsumerLoanMetricCalculator target;

    @Test
    public void isSupportedTrueTest(){
        Loan loan = LoanGeneratonUtil.createLoan(1l);
        Assertions.assertTrue(this.target.isSupported(loan));
    }


    @Test
    public void isSupportedFalseBecauseLoanTypeTest(){
        Loan loan = LoanGeneratonUtil.createLoan(2l);
        Assertions.assertFalse(this.target.isSupported(loan));
    }

    @Test
    public void getLoanMetricTest(){
        Loan loan = LoanGeneratonUtil.createLoan(1l);
        loan.setRequestedAmount(10000d);
        // El enunciado pone de ejemplo 2 años, pero termYears no existe en Loan
        loan.setTermMonths(24);
        loan.setAnnualInterest(6d);

        LoanMetric loanMetric = this.target.getLoanMetric(loan);
        Assertions.assertEquals("consumer", loan.getType());
        Assertions.assertEquals(0.005d, loanMetric.getMonthlyInterestRate());
        Assertions.assertEquals(443.20d, loanMetric.getMonthlyPayment());

    }
}
