package com.test.catalog.model;

import com.test.catalog.db.ReadDb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenreInfo extends Genre {

    private int countBook;
    private Set<String> listAuthors = new HashSet<>();

    public int getCountBook() {

        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS WHERE GENRE=?" , this.getId()).values());
        return books.size();


    }

    public Set<String> getListAuthors() {

        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS WHERE GENRE=?" , this.getId()).values());

        for (Book a : books) {
            if (a.getAuthor() != null)
                listAuthors.add(a.getAuthor().getFio());
        }


        return listAuthors;
    }


}
