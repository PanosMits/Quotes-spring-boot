package com.example.quotesapi.quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(value = "SELECT * from quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> getRandom();
}
