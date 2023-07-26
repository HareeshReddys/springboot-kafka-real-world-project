package com.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

//    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

            private KafkaTemplate <String, String> KafkaTemplate;

    public WikimediaChangesProducer(org.springframework.kafka.core.KafkaTemplate<String, String> kafkaTemplate) {
        KafkaTemplate = kafkaTemplate;
    }

    public void sandMessage() throws InterruptedException {

        String topic = "Wikimedia_recentchange";

        // to read real time stream data from wikimedia, we use event Source

        EventHandler eventHandler = new WikimediaChangesHandler(KafkaTemplate, topic);
        String url= "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder= new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource= builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }
}
