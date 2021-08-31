package com.test.catalog.controller;

import com.test.catalog.db.ReadDb;
import com.test.catalog.db.Db;
import com.test.catalog.db.WriteDb;
import com.test.catalog.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
public class ClientController {

    // создание книги
    @PostMapping(value = "/create_book")
    public ResponseEntity<?> create(@RequestBody Book o) {
        WriteDb.writeBook(o);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //создание автора
    @PostMapping(value = "/create_author")
    public ResponseEntity<?> create(@RequestBody Author o) {
        WriteDb.writeAuthor(o);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //создание жанра
    @PostMapping(value = "/create_genre")
    public ResponseEntity<?> create(@RequestBody Genre o) {
        WriteDb.writeGenre(o);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // привязка автора к книге по id
    @PutMapping(value = "/book_set_author")
    public ResponseEntity<?> setAuthor(@RequestBody Id o) {
        Db.makeRequest("UPDATE BOOKS SET AUTHOR = ? WHERE BOOKS_ID = ?", o.getIdAuthor(), o.getIdBook());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // привязка жанра к книге по id
    @PutMapping(value = "/book_set_genre")
    public ResponseEntity<?> setGenre(@RequestBody Id o) {
        Db.makeRequest("UPDATE BOOKS SET GENRE = ? WHERE BOOKS_ID = ?", o.getIdGenre(), o.getIdBook());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // привязка автора к книге по имени
    @PutMapping(value = "/book_set_author_by_name")
    public ResponseEntity<?> setAuthorByName(@RequestBody Id o) {
        int idBook = Db.getId("BOOKS", o.getNameBook());
        int idAuthor = Db.getId("AUTHORS", o.getNameAuthor());
        Db.makeRequest("UPDATE BOOKS SET AUTHOR = ? WHERE BOOKS_ID = ?", idAuthor, idBook);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // привязка жанра к книге по названию жанра
    @PutMapping(value = "/book_set_genre_by_name")
    public ResponseEntity<?> setGenreByName(@RequestBody Id o) {
        int idBook = Db.getId("BOOKS", o.getNameBook());
        int idGenre = Db.getId("GENRE", o.getNameGenre());
        Db.makeRequest("UPDATE BOOKS SET GENRE = ? WHERE BOOKS_ID = ?", idGenre, idBook);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // вывод списка книг
    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> read(@RequestBody Book o) {
        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS").values());
        return !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // вывод списка книг отсортированный по автору
    @GetMapping(value = "/books_sorted_by_author")
    public ResponseEntity<List<Book>> readSortedAuthor(@RequestBody Book o) {

        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS").values());
        Collections.sort(books, new Comparator<Book>() {
            public int compare(Book o1, Book o2) {
                if (o1.getAuthor() == null) {
                    return (o2.getAuthor() == null) ? 0 : -1;
                }
                if (o2.getAuthor() == null) {
                    return 1;
                }
                return o1.getAuthor().getFio().compareTo(o2.getAuthor().getFio());
            }
        });
        return !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // вывод списка книг отсортированный по жанру
    @GetMapping(value = "/books_sorted_by_genre")
    public ResponseEntity<List<Book>> readSortedGenre(@RequestBody Book o) {

        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS").values());
        Collections.sort(books, new Comparator<Book>() {
            public int compare(Book o1, Book o2) {
                if (o1.getGenre() == null) {
                    return (o2.getGenre() == null) ? 0 : -1;
                }
                if (o2.getGenre() == null) {
                    return 1;
                }
                return o1.getGenre().getGenre().compareTo(o2.getGenre().getGenre());
            }
        });
        return !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // вывод списка авторов
    @GetMapping(value = "/authors")
    public ResponseEntity<List<Author>> read(@RequestBody Author o) {

        List<Author> authors = new ArrayList<Author>(ReadDb.readAuthor("SELECT * FROM AUTHORS").values());

        return !authors.isEmpty()
                ? new ResponseEntity<>(authors, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // вывод списка авторов с указанием кол-ва его книг и в каких жанрах
    @GetMapping(value = "/authors_info")
    public ResponseEntity<List<AuthorInfo>> read(@RequestBody AuthorInfo o) {
        List<AuthorInfo> authors = new ArrayList<>(ReadDb.readAuthorInfo("SELECT * FROM AUTHORS").values());
        return !authors.isEmpty()
                ? new ResponseEntity<>(authors, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // вывод списка жанров с указанием кол-ва книг и авторов в них
    @GetMapping(value = "/genres_info")
    public ResponseEntity<List<GenreInfo>> read(@RequestBody GenreInfo o) {
        List<GenreInfo> genres = new ArrayList<>(ReadDb.readGenreInfo("SELECT * FROM GENRE").values());
        return !genres.isEmpty()
                ? new ResponseEntity<>(genres, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // вывод списка жанров
    @GetMapping(value = "/genres")
    public ResponseEntity<List<Genre>> read(@RequestBody Genre o) {
        List<Genre> genres = new ArrayList<>(ReadDb.readGenre("SELECT * FROM GENRE").values());
        return !genres.isEmpty()
                ? new ResponseEntity<>(genres, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // вывод списка книг по id заданного автора
    @GetMapping(value = "/books_by_author_id/{id}")
    public ResponseEntity<List<Book>> readBooksByAuthor(@PathVariable(name = "id") int id) {
        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS WHERE AUTHOR=?", id).values());
        return !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // вывод списка книг по id заданного жанра
    @GetMapping(value = "/books_by_genres_id/{id}")
    public ResponseEntity<List<Book>> readBooksByGenres(@PathVariable(name = "id") int id) {
        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS WHERE GENRE=?", id).values());
        return !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // вывод списка книг по имени заданного автора
    @GetMapping(value = "/books_by_author_name/{id}")
    public ResponseEntity<List<Book>> readBooksByAuthorName(@PathVariable(name = "id") String id) {
        int idName = Db.getId("AUTHORS", id);
        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS WHERE AUTHOR=?", idName).values());
        return !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // вывод списка книг по названию заданного жанра
    @GetMapping(value = "/books_by_genre_name/{id}")
    public ResponseEntity<List<Book>> readBooksByGenreName(@PathVariable(name = "id") String id) {
        int idName = Db.getId("GENRE", id);
        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS WHERE GENRE=?", idName).values());
        return !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // поиск книг по фрагменту имени автора
    @GetMapping(value = "/books_search_by_fragment_author_name/{id}")
    public ResponseEntity<List<Book>> searchBooksByFragment(@PathVariable(name = "id") String id) {
        List<Book> books = new ArrayList<>(ReadDb.readBook("SELECT * FROM BOOKS").values());

        List<Book> result = books.stream()
                .filter(s -> (s.getAuthor()==null)? false : s.getAuthor().getFio().contains(id))
                .collect(Collectors.toList());

        return !result.isEmpty()
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // удаление книги по id
    @DeleteMapping(value = "/books_delete_by_id")
    public ResponseEntity<?> delBook(@RequestBody Id o) {
        Db.makeRequest("DELETE FROM BOOKS WHERE BOOKS_ID =? ", o.getIdBook());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // удаление автора по id
    @DeleteMapping(value = "/authors_delete_by_id")
    public ResponseEntity<?> delAuthor(@RequestBody Id o) {
        Db.makeRequest("DELETE FROM AUTHORS WHERE AUTHORS_ID =? ", o.getIdAuthor());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // удаление жанра по id
    @DeleteMapping(value = "/genres_delete_by_id")
    public ResponseEntity<?> delGenre(@RequestBody Id o) {
        Db.makeRequest("DELETE FROM GENRE WHERE GENRE_ID = ?", o.getIdGenre());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // удаление книги по названию
    @DeleteMapping(value = "/books_delete_by_name")
    public ResponseEntity<?> delBookName(@RequestBody Id o) {
        Db.makeRequest("DELETE FROM BOOKS WHERE TITLE = ?", o.getNameBook());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // удаление автора по названию
    @DeleteMapping(value = "/authors_delete_by_name")
    public ResponseEntity<?> delAuthorName(@RequestBody Id o) {
        Db.makeRequest("DELETE FROM AUTHORS WHERE FIO = ?", o.getNameAuthor());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // удаление жанра по названию
    @DeleteMapping(value = "/genres_delete_by_name")
    public ResponseEntity<?> delGenreName(@RequestBody Id o) {
        Db.makeRequest("DELETE FROM GENRE WHERE GENRE = ?", o.getNameGenre());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

