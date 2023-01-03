package cl.espacio.ws.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringBootJpaMysqlSecurityApiRestfulApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate template;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaMysqlSecurityApiRestfulApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {

        template.execute("INSERT INTO USUARIOS (NAME, EMAIL, PASSWORD, CREATED, MODIFIED, IS_ACTIVE, LAST_LOGIN ) "
                + "VALUES ('Juan Pablo', 'ju.pablo@juan.org', 'hunter2', '2021-10-08 21:00:23', '2021-11-08 22:00:00', 1, '2021-11-08 22:00:00') ");

        } catch (Exception e ) {
            log.error("Error, {}", e.getMessage() );
        }


    }
}
