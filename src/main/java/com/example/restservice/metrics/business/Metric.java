package com.example.restservice.metrics.business;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Metric {
    public LoanType value();

}
