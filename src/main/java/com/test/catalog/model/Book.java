package com.test.catalog.model;

import java.util.Comparator;


public class Book{


    private String title;
    private Integer year;
    private Integer isbn;
    private String publishing;
    private Author author;
    private  Genre genre;
    private Integer id;

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }



}
