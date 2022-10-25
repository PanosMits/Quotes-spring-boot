package com.example.quotesapi.quote;

import javax.persistence.*;

@Entity
@Table
public class Quote {
    @Id
    @GeneratedValue
    private Long id; // Tried using UUID but Hibernate seems to have some issues: https://github.com/spring-projects/spring-data-jpa/issues/2590
    @Column(nullable = true)
    private String author;
    @Column(nullable = false) // @NotNull did not work, the column was accepting NULL
    private String text;

    public Quote() {
    }

    public Quote(long id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }

    public Quote(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Quote(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean textIsValid() {
        return this.text != null && this.text.length() >= 1;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
