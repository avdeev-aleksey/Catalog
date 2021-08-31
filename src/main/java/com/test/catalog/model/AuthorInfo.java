package com.test.catalog.model;

import com.test.catalog.db.ReadDb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// класс расширяющий Author информацией о количестве книг и списком жанров
public class AuthorInfo extends Author {

    private int countBook;
    private Set<String> listGenre = new HashSet<>();

    public int getCountBook() {

        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS WHERE AUTHOR=?" , this.getId()).values());
        return books.size();

    }


    public Set<String> getListGenre() {

        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS WHERE AUTHOR=?" , this.getId()).values());

        for (Book a : books) {
            if (a.getGenre() != null)
                listGenre.add(a.getGenre().getGenre());
        }

        return listGenre;
    }

}
