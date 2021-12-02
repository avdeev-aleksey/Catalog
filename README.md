Описание приложения
=================
Конфигурирование базы данных

Хранение данных приложения осуществляется в базе данных Postgres, запущенной локально
Для конфигурирования подключения к базе необходимо отредактировать класс ParametersDb (пакет - com.test.catalog.db)
   
    private static String login = "postgres";
    private static String password = "postgres";
    private static String nameDb = "catalogdb";
    private static String port = "5432";


где указать логин, пароль, порт подключения к базе, само имя базы "catalogdb" можно не менять
При первом запуске приложения будет создана база catalogdb и в ней три таблицы (BOOKS, AUTHORS, GENRE)


Приложение реализовано в виде rest сервиса(для тестирования я использовал программу Postman)
Параметры rest запросов


Post запросы
=================
создание книги
http://localhost:8080/create_book
{
"title":"книга1",
"year":1923,
"isbn":1234567,
"publishing":"издательство1"
}
------------------------------------------------------------------------------------------------------------
создание автора
http://localhost:8080/create_author
{
"fio":"автор1",
"year":1985
}
------------------------------------------------------------------------------------------------------------
создание жанра
http://localhost:8080/create_genre
{
"genre":"жанр1"
}
------------------------------------------------------------------------------------------------------------

Get запросы
=================

 вывод списка книг
http://localhost:8080/books
------------------------------------------------------------------------------------------------------------
 вывод списка книг отсортированный по авторам
http://localhost:8080/books_sorted_by_author
------------------------------------------------------------------------------------------------------------
 вывод списка книг отсортированный по жанрам
http://localhost:8080/books_sorted_by_genre
------------------------------------------------------------------------------------------------------------
 вывод списка авторов
http://localhost:8080/authors
------------------------------------------------------------------------------------------------------------
 вывод списка авторов с указанием кол-ва его книг и в каких жанрах
http://localhost:8080/authors_info"
------------------------------------------------------------------------------------------------------------
 вывод списка жанров с указанием кол-ва книг и авторов в них
http://localhost:8080/genres_info
------------------------------------------------------------------------------------------------------------
 вывод списка жанров
http://localhost:8080/genres
------------------------------------------------------------------------------------------------------------
 вывод списка книг по id заданного автора
http://localhost:8080/books_by_author_id/{id}

например  http://localhost:8080/books_by_author_id/3
------------------------------------------------------------------------------------------------------------
 вывод списка книг по id заданного жанра
http://localhost:8080/books_by_genres_id/{id}

например http://localhost:8080/books_by_genres_id/2

------------------------------------------------------------------------------------------------------------
 вывод списка книг по имени заданного автора
http://localhost:8080/books_by_author_name/{id}

например http://localhost:8080/books_by_genres_id/автор1

------------------------------------------------------------------------------------------------------------
 вывод списка книг по названию заданного жанра
http://localhost:8080/books_by_genre_name/{id}

например http://localhost:8080/books_by_genre_name/жанр3

------------------------------------------------------------------------------------------------------------
 поиск книг по фрагменту имени автора
http://localhost:8080/books_search_by_fragment_author_name/{id}

например http://localhost:8080/books_search_by_fragment_author_name/авт
------------------------------------------------------------------------------------------------------------


Put запросы
=================
привязка автора к книге по id
http://localhost:8080/book_set_author
{
"idBook":2,
"idAuthor":1
}
------------------------------------------------------------------------------------------------------------
// привязка жанра к книге по id
http://localhost:8080/book_set_genre
{
"idBook":3,
"idGenre":3
}
------------------------------------------------------------------------------------------------------------
// привязка автора к книге по имени
http://localhost:8080/book_set_author_by_name
{
"nameBook":"книга3",
"nameAuthor":"автор4"
}
------------------------------------------------------------------------------------------------------------
// привязка жанра к книге по названию жанра
http://localhost:8080/book_set_genre_by_name
{
"nameBook":"книга4",
"nameGenre":"жанр3"
}

------------------------------------------------------------------------------------------------------------

Delete запросы
=================
 удаление книги по id
http://localhost:8080/books_delete_by_id
{
"idBook":4
}
------------------------------------------------------------------------------------------------------------
 удаление автора по id
http://localhost:8080/authors_delete_by_id
{
"idAuthor":5
}
------------------------------------------------------------------------------------------------------------
 удаление жанра по id
http://localhost:8080/genres_delete_by_id
{
"idGenre":5
}
------------------------------------------------------------------------------------------------------------
 удаление книги по названию
http://localhost:8080/books_delete_by_name
{
"nameBook":"книга55"
}
------------------------------------------------------------------------------------------------------------
 удаление автора по названию
http://localhost:8080/authors_delete_by_name
{
"nameAuthor":"автор3"
}
------------------------------------------------------------------------------------------------------------
 удаление жанра по названию
http://localhost:8080/genres_delete_by_name
{
"nameGenre":"жанр3"
}
------------------------------------------------------------------------------------------------------------

