package com.spring.springacademylearning.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cashcard")
public class CashcardController {

    CashCardRepository cashCardRepository;

    public CashcardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping
    public String cashcard() {
        return "cashcard";
    }

    @GetMapping("{requestId}")
    private ResponseEntity<CashCard> getCashCard(@PathVariable Long requestId) {
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestId);
        return cashCardOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    private ResponseEntity<CashCard> createCashCard(@RequestBody CashCard cashCard, UriComponentsBuilder ucb) {
        CashCard savedCashCard = cashCardRepository.save(cashCard);
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.Id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }
}
