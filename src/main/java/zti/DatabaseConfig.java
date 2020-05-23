package zti;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ComponentScan(basePackages = "zti")
public class DatabaseConfig {

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:postgresql://ec2-3-234-109-123.compute-1.amazonaws.com:5432/d3bl0eamvt3oqr?user=nrclayyaikkhav&password=c92ed7e47edb70192a0811cf89a79cf54a44abd545f96b7d3d2df7f0e3cd470e&sslmode=require");
        basicDataSource.setUsername("nrclayyaikkhav");
        basicDataSource.setPassword("c92ed7e47edb70192a0811cf89a79cf54a44abd545f96b7d3d2df7f0e3cd470e");

        return basicDataSource;
    }

}