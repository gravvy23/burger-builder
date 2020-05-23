package com.example.burgerbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
public class BurgerBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurgerBuilderApplication.class, args);
	}
	// @Autowired
	// private DataSource dataSource;
	
	// public static void main(String[] args) throws Exception {
    //     AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BurgerBuilderApplication.class.getPackage().getName());
    // }

    // @PostConstruct
    // public void myRealMainMethod() throws SQLException {
    //     Statement stmt = dataSource.getConnection().createStatement();
	//     stmt.executeUpdate("DROP TABLE IF EXISTS users");
    //     stmt.executeUpdate("CREATE TABLE users ( id primary key, name varchar, surname varchar, mail varchar)");
    // }
}
