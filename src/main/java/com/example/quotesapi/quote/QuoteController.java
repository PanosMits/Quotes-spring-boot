package com.example.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/quote")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> create(@RequestBody Quote quote) {
        quoteService.saveQuote(quote);
        return ResponseEntity.ok("Quote created successfully.");
    }

    @GetMapping(path = "/test")
    public String test() {
        return "TEST";
    }
}
