package com.test.security.SpringSecurity;

import com.test.security.SpringSecurity.error.Author;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Please provide a name")
    private String name;
    @Author
    @NotEmpty(message = "Please provide a author")
    private String author;
    @NotEmpty(message = "Please provide a price")
    @DecimalMin("1.00")
    private BigDecimal price;

    public Book() {
    }

    public Book(Long id, @NotEmpty(message = "Please provide a name") String name, @NotEmpty(message = "Please provide a author") String author, @NotEmpty(message = "Please provide a price") @DecimalMin("1.00") BigDecimal price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(@NotEmpty(message = "Please provide a name") String name, @NotEmpty(message = "Please provide a author") String author, @NotEmpty(message = "Please provide a price") @DecimalMin("1.00") BigDecimal price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}