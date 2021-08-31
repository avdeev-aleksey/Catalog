package com.test.catalog.db;

public class ParametersDb {
    private static String login = "postgres";
    private static String password = "postgres";
    private static String nameDb = "catalogdb";
    private static String port = "5432";

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static String getNameDb() {
        return nameDb;
    }

    public static String getPort() {
        return port;
    }

}
