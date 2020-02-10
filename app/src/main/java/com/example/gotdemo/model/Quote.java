package com.example.gotdemo.model;

public class Quote {
    private String quote;
    private String character;

    public Quote(String quote, String character) {
        this.quote = quote;
        this.character = character;
    }

    public String getQuote() {
        return quote;
    }

    public String getCharacter() {
        return character;
    }
}
