package com.test.catalog.db;

import com.test.catalog.model.Author;
import com.test.catalog.model.Book;
import com.test.catalog.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class WriteDb {

    //запрос создания книги
    public static void writeBook(Book book) {

        try {
            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;
            try {

                String sql = "INSERT INTO BOOKS (TITLE, BOOKS_YEAR, ISBN, PUBLISHING) VALUES (?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, book.getTitle());
                stmt.setInt(2, book.getYear());
                stmt.setInt(3, book.getIsbn());
                stmt.setString(4, book.getPublishing());

                stmt.executeUpdate();

                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //запрос создания автора
    public static void writeAuthor(Author author) {

        try {
            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;
            try {

                String sql = "INSERT INTO AUTHORS (FIO, AUTHOR_YEAR) VALUES (?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, author.getFio());
                stmt.setInt(2, author.getYear());


                stmt.executeUpdate();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //запрос создания жанра
    public static void writeGenre(Genre genre) {

        try {
            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;
            try {
                String sql = "INSERT INTO GENRE (GENRE) VALUES (?)";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, genre.getGenre());

                stmt.executeUpdate();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

