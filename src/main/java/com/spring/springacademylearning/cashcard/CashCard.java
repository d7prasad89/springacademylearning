package com.spring.springacademylearning.cashcard;

import org.springframework.data.annotation.Id;

record  CashCard(@org.springframework.data.annotation.Id Long Id, Double amount) {
}
