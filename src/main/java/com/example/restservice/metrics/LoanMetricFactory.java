package com.example.restservice.metrics;

import com.example.restservice.metrics.business.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanMetricFactory {

   private Map<String, ILoanMetricCalculator> metricCalculatorMap;

   @Autowired
   private List<ILoanMetricCalculator> loanMetricCalculatorList;

   @PostConstruct
   public void init(){
      this.metricCalculatorMap = this.loanMetricCalculatorList
              .stream()
              .collect(Collectors.toMap(
                      c -> c.getClass().getDeclaredAnnotation(Metric.class).value().name, c -> c));
   }
   
   public ILoanMetricCalculator getInstance(Loan loan) {
      return Optional.ofNullable(this.metricCalculatorMap.get(loan.getType()))
              .orElseThrow(() -> new IllegalStateException("Loan Metric unknown for type:"+loan.getType()));
   }

}
