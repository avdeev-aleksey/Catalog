package com.test.catalog;

import com.test.catalog.db.CreateDb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringRestApplication {

    public static void main(String[] args) {

        try {
            CreateDb.makeNewDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        SpringApplication.run(SpringRestApplication.class, args);
    }

}