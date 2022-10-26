package com.example.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping(path = "/update/{quoteId}")
    public ResponseEntity<String> update(
            @PathVariable() Long quoteId,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String text) {
        quoteService.updateQuote(quoteId, author, text);
        return ResponseEntity.ok("Quote updated successfully.");
    }

    @GetMapping(path = "/get/{quoteId}")
    public Optional<Quote> getQuote(@PathVariable("quoteId") Long quoteId) {
        return quoteService.getById(quoteId);
    }

    @DeleteMapping(path = "/delete/{quoteId}")
    public ResponseEntity<String> delete(@PathVariable("quoteId") Long quoteId) {
        quoteService.deleteQuote(quoteId);
        return ResponseEntity.ok("Quote deleted successfully.");
    }

    @GetMapping(path = "/get-random")
    public Optional<Quote> getRandom() {
        return quoteService.getRandomQuote();
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Quote>> getAll() {
        List<Quote> quotes = quoteService.getAll();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping(path = "/get-all-containing/{text}")
    public Optional<List<Quote>> getQuotesContaining(@PathVariable("text") String text) {
        return quoteService.getQuotesContaining(text);
    }
}
