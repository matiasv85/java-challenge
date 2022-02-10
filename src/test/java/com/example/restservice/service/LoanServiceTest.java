package com.example.restservice.service;

import com.example.restservice.metrics.business.LoanType;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LoanServiceTest {

    @Autowired
    private LoanService target;

    @Test
    public void calculateSupportedLoanMetricTest(){
        Loan loan = LoanGeneratonUtil.createLoan(1l);
        loan.setAnnualInterest(6d);
        Assertions.assertEquals(LoanType.CONSUMER.name, loan.getType());
        LoanMetric loanMetric = this.target.calculateLoanMetric(loan);
        Assertions.assertNotNull(loanMetric);
    }


    public void calculateNotSupportedLoanMetricTest(){
        Loan loan = LoanGeneratonUtil.createLoan(2l);
        loan.setAnnualInterest(6d);
        loan.getBorrower().setAge(17);
        Assertions.assertEquals(LoanType.STUDENT.name, loan.getType());
        try {
            Optional.ofNullable(this.target.calculateLoanMetric(loan))
                    .orElseThrow(() -> new IllegalStateException("Cannot get a LoanMetric"));
        } catch (IllegalStateException ex){
            Assertions.assertEquals("Cannot get a LoanMetric", ex.getMessage());
        }
    }

    @Test
    public void getMaxMonthlyPaymentLoanTest(){
        Loan maxMonthyPaymentLoan = this.target.getMaxMonthlyPaymentLoan();
        Assertions.assertEquals(20l, maxMonthyPaymentLoan.getLoanId());
    }
}
