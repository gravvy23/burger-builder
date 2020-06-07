package com.example.burgerbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Burger Builder REST API
 */
@SpringBootApplication
public class BurgerBuilderApplication {

    /**
     * Entry point of the application
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(BurgerBuilderApplication.class, args);
	}
}
