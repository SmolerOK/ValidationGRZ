package ru.artsec.ValidationGrzModuleV3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.artsec.ValidationGrzModuleV3.service.ValidationServiceImplementation;

import java.sql.SQLException;

@SpringBootApplication
public class ValidationGRZApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ValidationGRZApplication.class);

    ApplicationContext applicationContext;

    public ValidationGRZApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) throws SQLException {
        log.info("Начало работы программы.");
        SpringApplication.run(ValidationGRZApplication.class, args);
    }


    @Override
    public void run(String... args) {
        try {
            ValidationServiceImplementation validationServiceImpl =
                    applicationContext.getBean("validationServiceImplementation", ValidationServiceImplementation.class);
            validationServiceImpl.getConnectionMqttClient();
        } catch (Exception ex) {
            log.error("Ошибка: " + ex.getMessage());
        }
    }
}
