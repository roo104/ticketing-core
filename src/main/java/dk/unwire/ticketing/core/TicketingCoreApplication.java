package dk.unwire.ticketing.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "dk.unwire.ticketing.core",
        "dk.unwire.ticketing.spring.rest"
})
public class TicketingCoreApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TicketingCoreApplication.class, args);
    }
}
