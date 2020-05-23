package zti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;

@Component
public class DemoApplication {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApplication.class.getPackage().getName());
    }

    @PostConstruct
    public void myRealMainMethod() throws SQLException {
        Statement stmt = dataSource.getConnection().createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS ingredients");
        stmt.executeUpdate("CREATE TABLE ingredients ( id serial primary key, name varchar, price decimal(5,2))");
        stmt.executeUpdate("INSERT INTO ingredients (name, price) VALUES ('salad', 0.50)");
        stmt.executeUpdate("INSERT INTO ingredients (name, price) VALUES ('cheese', 0.40)");
        stmt.executeUpdate("INSERT INTO ingredients (name, price) VALUES ('meat', 1.30)");
        stmt.executeUpdate("INSERT INTO ingredients (name, price) VALUES ('bacon', 0.70)");
        stmt.executeUpdate("INSERT INTO ingredients (name, price) VALUES ('tomato', 0.50)");
        stmt.executeUpdate("INSERT INTO ingredients (name, price) VALUES ('cucumber', 0.40)");
        stmt.executeUpdate("INSERT INTO ingredients (name, price) VALUES ('onion', 0.40)");
    }
}