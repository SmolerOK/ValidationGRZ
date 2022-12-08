package ru.artsec.ValidationGrzModuleV3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidationServiceImplementation {

    private final static Logger log = LoggerFactory.getLogger(ValidationServiceImplementation.class);
    final MqttServices mqttServices;

    public ValidationServiceImplementation(MqttServices mqttServices) {
        this.mqttServices = mqttServices;
    }

    public void getConnectionMqttClient() throws InterruptedException {
        mqttServices.getConnection();
    }


}
