package com.test.catalog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Db {


    // выполнение запроса к базе данных
    public static void makeRequest(String sql, int parameter1, int parameter2) {

        try {
            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, parameter1);
                stmt.setInt(2, parameter2);
                stmt.executeUpdate();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // выполнение запроса к базе данных
    public static void makeRequest(String sql, int parameter) {

        try {
            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;
            try {

                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, parameter);
                stmt.executeUpdate();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // выполнение запроса к базе данных
    public static void makeRequest(String sql, String parameter) {

        try {
            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;
            try {

                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, parameter);
                stmt.executeUpdate();
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //получение Id по имени книги, автора,жанра
    public static int getId(String nameTable, String nameParameter) {

        int resultId = 0;

        try {
            CreateConnectionDb.connect();
            Connection con = CreateConnectionDb.conn;
            try {

                String sql = "";

                switch (nameTable) {
                    case "BOOKS":
                        sql = "SELECT * FROM BOOKS WHERE TITLE =?";
                        break;
                    case "AUTHORS":
                        sql = "SELECT * FROM AUTHORS WHERE FIO =?";
                        break;
                    case "GENRE":
                        sql = "SELECT * FROM GENRE WHERE GENRE =?";
                        break;
                }

                if (sql.equals("")) {
                    return 0;
                }

                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, nameParameter);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {

                    switch (nameTable) {
                        case "BOOKS":
                            resultId = rs.getInt("BOOKS_ID");
                            break;
                        case "AUTHORS":
                            resultId = rs.getInt("AUTHORS_ID");
                            break;
                        case "GENRE":
                            resultId = rs.getInt("GENRE_ID");
                            break;
                    }
                }
                stmt.close();

            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultId;
    }
}
