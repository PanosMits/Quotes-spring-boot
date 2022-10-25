package com.example.quotesapi.quote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/quote")
public class QuoteController {

    @GetMapping(path = "/all")
    public String test() {
        return "Test 2";
    }
}
