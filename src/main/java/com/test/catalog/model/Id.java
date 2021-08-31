package com.test.catalog.model;

public class Id {

    private Integer idBook;
    private Integer idAuthor;
    private Integer idGenre;
    private String nameBook;
    private String nameAuthor;
    private String nameGenre;

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getNameBook() {
        return nameBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public Integer getIdGenre() {
        return idGenre;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
    }
}
