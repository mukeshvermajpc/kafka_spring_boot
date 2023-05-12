package com.java.kafka;

import com.java.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    public static final Logger LOGGER= LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "myGroup")
    public void consume(String message){
        LOGGER.info(String.format("Message received ->%s",message));
    }
    @KafkaListener(topics = "${spring.kafka.topic-json.name}",groupId = "myGroup")
    public void consume(User user){
        LOGGER.info(String.format("Message received ->%s",user.toString()));
    }
}
