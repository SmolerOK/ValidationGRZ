package ru.artsec.ValidationGrzModuleV3.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.artsec.ValidationGrzModuleV3.model.ConfigurationModel;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class ConnectionDatabase {
    private final static Logger log = LoggerFactory.getLogger(ConnectionDatabase.class);
    int count;
    ObjectMapper mapper = new ObjectMapper();
    File mqttConfig = new File("ValidatedConfig.json");
    ConfigurationModel configurationModel;

    {
        try {
            configurationModel = mapper.readValue(mqttConfig, ConfigurationModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection connectionDB;
    public ConnectionDatabase() throws SQLException, IOException {
    }


    public Connection connected() {
        log.info("Подключение к базе данных... Попытка: " + ++count);

        try {
            if ( connectionDB == null || connectionDB.isClosed()) {
                connectionDB = DriverManager.getConnection(
                        "jdbc:firebirdsql://" + configurationModel.getDatabaseIp() + ":" +
                                configurationModel.getDatabasePort() + "/" +
                                configurationModel.getDatabasePath() + "?encoding=WIN1251",
                        configurationModel.getDatabaseLogin(),
                        configurationModel.getDatabasePassword()
                );

            log.info("Информация о подключении к базе данных. " +
                    "LOGIN: " + configurationModel.getDatabaseLogin() + ", " +
                    "PASSWORD: " + configurationModel.getDatabasePassword() + ", " +
                    "IP: " + configurationModel.getDatabaseIp() + ", " +
                    "PORT: " + configurationModel.getDatabasePort() + ", " +
                    "ПУТЬ: " + configurationModel.getDatabasePath());
            }
            if (!connectionDB.isClosed()) {
                log.info("Соединение с базой данных произошло успешно!");
            }

            return connectionDB;
        } catch (Exception ex) {
            log.error("Ошибка: " + ex);
        }
        return null;
    }




}
