package com.java.kafka;

import com.java.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "stock")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("Message received ->%s", orderEvent.toString()));
    }
}
