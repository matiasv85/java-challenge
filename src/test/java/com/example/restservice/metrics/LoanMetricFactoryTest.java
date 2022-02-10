package com.example.restservice.metrics;

import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.util.LoanGeneratonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoanMetricFactoryTest {

    @Autowired
    private LoanMetricFactory target;

    @Test
    public void getConsumerMetricCalculatorTest(){
        Loan loan = LoanGeneratonUtil.createLoan(1l);
        ILoanMetricCalculator instance = this.target.getInstance(loan);
        Assertions.assertTrue(instance instanceof ConsumerLoanMetricCalculator);
    }

    @Test
    public void getStudentMetricCalculatorTest(){
        Loan loan = LoanGeneratonUtil.createLoan(2l);
        ILoanMetricCalculator instance = this.target.getInstance(loan);
        Assertions.assertTrue(instance instanceof StudentLoanMetricCalculator);
    }

    @Test
    public void getConsumerMetricCalculatorFailBecauseLoanTypeTest(){
        Loan loan = LoanGeneratonUtil.createLoan(2l);
        ILoanMetricCalculator instance = this.target.getInstance(loan);
        Assertions.assertFalse(instance instanceof ConsumerLoanMetricCalculator);
    }

    @Test
    public void getStudentMetricCalculatorFailBecauseLoanTypeTest(){
        Loan loan = LoanGeneratonUtil.createLoan(1l);
        ILoanMetricCalculator instance = this.target.getInstance(loan);
        Assertions.assertFalse(instance instanceof StudentLoanMetricCalculator);
    }

    @Test
    public void getUnknownMetricCalculatorTest(){
        Loan loan = LoanGeneratonUtil.createLoan(1l);
        loan.setType("unknown");
        try {
            ILoanMetricCalculator instance = this.target.getInstance(loan);
        } catch (IllegalStateException ex){
            Assertions.assertEquals("Loan Metric unknown for type:unknown", ex.getMessage());
        }
    }
}
