package com.test.catalog.db;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDb {
    static String DATABASE_URL = "jdbc:postgresql://localhost:" + ParametersDb.getPort() + "/";
    static String JDBC_DRIVER = "org.postgresql.Driver";
    static String DATABASE_URL2 = "jdbc:postgresql://localhost:" + ParametersDb.getPort() + "/" + ParametersDb.getNameDb();
    static String USER = ParametersDb.getLogin();
    static String PASSWORD = ParametersDb.getPassword();

    public static void makeNewDb() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        // создание базы данный
        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            statement = connection.createStatement();

            String SQL = "CREATE DATABASE " + ParametersDb.getNameDb();
            statement.executeUpdate(SQL);
            System.out.println("База успешно создана");

        } catch (PSQLException e) {

            System.out.println("Ошибка при создании базы - база с таким именем уже существует");

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


        // создание таблиц
        try {

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL2, USER, PASSWORD);
            statement = connection.createStatement();

            String SQL = "CREATE TABLE BOOKS" +
                    "(BOOKS_ID SERIAL," +
                    "TITLE VARCHAR(150) NOT NULL," +
                    "BOOKS_YEAR INT NOT NULL," +
                    "ISBN INT NOT NULL," +
                    "PUBLISHING VARCHAR(150) NOT NULL," +
                    "AUTHOR INT," +
                    "GENRE INT," +
                    "PRIMARY KEY (BOOKS_ID))";

            statement.executeUpdate(SQL);


            SQL = "CREATE TABLE AUTHORS" +
                    "(AUTHORS_ID SERIAL," +
                    "FIO VARCHAR(150) NOT NULL," +
                    "AUTHOR_YEAR INT," +
                    "PRIMARY KEY (AUTHORS_ID))";

            statement.executeUpdate(SQL);


            SQL = "CREATE TABLE GENRE" +
                    "(GENRE_ID SERIAL," +
                    "GENRE VARCHAR(150) NOT NULL," +
                    "PRIMARY KEY (GENRE_ID))";

            statement.executeUpdate(SQL);


            System.out.println("Таблицы успешно созданы");
        } catch (PSQLException e) {

            System.out.println("Ошибка при создании таблицы - таблица(таблицы) с таким именем уже существуют");

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}