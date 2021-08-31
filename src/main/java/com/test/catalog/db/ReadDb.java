package com.test.catalog.db;

import com.test.catalog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ReadDb {

    //чтение списка книг из базы данных
    public static Map<Integer, Book> readBook(String sql) {

        Map<Integer, Book> booksAll = new HashMap<>();

        try {

            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {

                    Book book = new Book();

                    book.setId(rs.getInt("BOOKS_ID"));
                    book.setTitle(rs.getString("TITLE"));
                    book.setYear(rs.getInt("BOOKS_YEAR"));
                    book.setIsbn(rs.getInt("ISBN"));
                    book.setPublishing(rs.getString("PUBLISHING"));

                    Author author = readAuthor("SELECT * FROM AUTHORS").get(rs.getInt("AUTHOR"));
                    book.setAuthor(author);

                    Genre genre = readGenre("SELECT * FROM GENRE").get(rs.getInt("GENRE"));
                    book.setGenre(genre);

                    booksAll.put(rs.getInt("BOOKS_ID"), book);

                }

                rs.close();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksAll;
    }

    // чтение списка книг с условием
    public static Map<Integer, Book> readBook(String sql, int id) {


        Map<Integer, Book> booksAll = new HashMap<>();

        try {

            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;


            try {


                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();


                while (rs.next()) {

                    Book book = new Book();

                    book.setId(rs.getInt("BOOKS_ID"));
                    book.setTitle(rs.getString("TITLE"));
                    book.setYear(rs.getInt("BOOKS_YEAR"));
                    book.setIsbn(rs.getInt("ISBN"));
                    book.setPublishing(rs.getString("PUBLISHING"));

                    Author author = readAuthor("SELECT * FROM AUTHORS").get(rs.getInt("AUTHOR"));
                    book.setAuthor(author);

                    Genre genre = readGenre("SELECT * FROM GENRE").get(rs.getInt("GENRE"));
                    book.setGenre(genre);

                    booksAll.put(rs.getInt("BOOKS_ID"), book);

                }

                rs.close();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksAll;
    }


    // чтение списка авторов
    public static Map<Integer, Author> readAuthor(String sql) {

        Map<Integer, Author> authorsAll = new HashMap<>();

        try {

            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;

            try {
                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(sql);


                while (rs.next()) {

                    Author author = new Author();

                    author.setId(rs.getInt("AUTHORS_ID"));
                    author.setFio(rs.getString("FIO"));
                    author.setYear(rs.getInt("AUTHOR_YEAR"));

                    authorsAll.put(rs.getInt("AUTHORS_ID"), author);

                }
                rs.close();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorsAll;
    }

    // чтение списка авторов с кол-вом книг и жанрами
    public static Map<Integer, AuthorInfo> readAuthorInfo(String sql) {

        Map<Integer, AuthorInfo> authorsAll = new HashMap<>();

        try {

            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;

            try {
                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(sql);


                while (rs.next()) {

                    AuthorInfo author = new AuthorInfo();

                    author.setId(rs.getInt("AUTHORS_ID"));
                    author.setFio(rs.getString("FIO"));
                    author.setYear(rs.getInt("AUTHOR_YEAR"));

                    authorsAll.put(rs.getInt("AUTHORS_ID"), author);

                }
                rs.close();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorsAll;
    }

    // чтение списка жанров
    public static Map<Integer, Genre> readGenre(String sql) {


        Map<Integer, Genre> genreAll = new HashMap<>();

        try {

            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {

                    Genre genre = new Genre();

                    genre.setId(rs.getInt("GENRE_ID"));
                    genre.setGenre(rs.getString("GENRE"));

                    genreAll.put(rs.getInt("GENRE_ID"), genre);

                }
                rs.close();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genreAll;
    }

    // чтение списка жанров с количеством книг и авторами
    public static Map<Integer, GenreInfo> readGenreInfo(String sql) {


        Map<Integer, GenreInfo> genreAll = new HashMap<>();

        try {

            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {

                    GenreInfo genre = new GenreInfo();

                    genre.setId(rs.getInt("GENRE_ID"));
                    genre.setGenre(rs.getString("GENRE"));

                    genreAll.put(rs.getInt("GENRE_ID"), genre);

                }
                rs.close();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genreAll;
    }

}
