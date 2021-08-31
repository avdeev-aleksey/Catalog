package com.test.catalog.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnectionDb {

    public static Connection conn;

    public static void connect() {

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:" + ParametersDb.getPort() + "/" + ParametersDb.getNameDb();
            String login = ParametersDb.getLogin();
            String password = ParametersDb.getPassword();
            conn = DriverManager.getConnection(url, login, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
