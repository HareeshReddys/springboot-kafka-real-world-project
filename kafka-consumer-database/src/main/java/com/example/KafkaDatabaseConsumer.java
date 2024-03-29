package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @KafkaListener(topics = "wikimedia_recentchange" ,
                           groupId = "myGroup")
    public void consume(String eventMessage){

        LOGGER.info(String.format("Event message recevied -> %s ", eventMessage));

    }
}
