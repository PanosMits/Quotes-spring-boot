package com.example.quotesapi.quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(value = "SELECT * from quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> getRandom();

    @Query(value = "SELECT * FROM quote q WHERE q.text LIKE %:text%", nativeQuery = true)
    Optional<List<Quote>> getQuotesContaining(@Param("text")String text);
}
