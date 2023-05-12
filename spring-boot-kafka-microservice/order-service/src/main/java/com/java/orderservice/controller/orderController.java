package com.java.orderservice.controller;

import com.java.dto.Order;
import com.java.dto.OrderEvent;
import com.java.orderservice.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class orderController {
    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("Pending");
        orderEvent.setMessage("Order status is in pending state");
        orderEvent.setOrder(order);
        kafkaProducer.sendMessage(orderEvent);
        return "Order placed successfully";
    }
}
