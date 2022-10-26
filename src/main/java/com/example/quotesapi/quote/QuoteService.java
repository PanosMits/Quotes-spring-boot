package com.example.quotesapi.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void saveQuote(Quote quote) {
        if (!quote.textIsValid()) throw new IllegalStateException("Please provide some text.");
        quoteRepository.save(quote);
    }

    @Transactional
    // Allows to use Entity's setters to update database fields without the need of writing my own SQL queries
    public void updateQuote(Long quoteId, String author, String text) {
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(QuoteNotFoundException::new);

        if (text == null || text.length() < 1) {
            throw new IllegalStateException("Please provide some text.");
        }
        quote.setText(text);

        if (author != null && author.length() > 0) {
            quote.setAuthor(author);
        }
    }

    public Optional<Quote> getById(Long quoteId) {
        if(!quoteRepository.existsById(quoteId)) throw new QuoteNotFoundException();
        return quoteRepository.findById(quoteId);
    }

    public void deleteQuote(Long quoteId) {
        if(!quoteRepository.existsById(quoteId)) throw new QuoteNotFoundException();
        quoteRepository.deleteById(quoteId);
    }

    public Optional<Quote> getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        if (quotes.isEmpty()) throw new QuoteNotFoundException();
        return quoteRepository.getRandom();
    }
}
