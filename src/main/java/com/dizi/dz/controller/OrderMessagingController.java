package com.dizi.dz.controller;

import com.dizi.dz.entity.DiziOrder;
import com.dizi.dz.messaging.OrderMessagingService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderMessagingController {

    @Resource(name = "jms")
    private OrderMessagingService jmsOrderMessagingService;
    @Resource(name = "rabbit")
    private OrderMessagingService rabbitOrderMessagingService;
    @Resource(name = "kafka")
    private OrderMessagingService kafkaOrderMessagingService;

    @PostMapping("/send")
    public void sendOrderViaJms(@RequestBody DiziOrder order) {
        jmsOrderMessagingService.sendOrder(order);
    }

    @PostMapping("/send/rabbit")
    public void sendOrderViaRabbit(@RequestBody DiziOrder order) {
        rabbitOrderMessagingService.sendOrder(order);
    }

    @PostMapping("/send/kafka")
    public void sendOrderViaKafka(@RequestBody DiziOrder order) {
        kafkaOrderMessagingService.sendOrder(order);
    }

    @GetMapping("/receive")
    public DiziOrder receiveOrder() {
        return jmsOrderMessagingService.receiveOrder();
    }

    @GetMapping("/receive/rabbit")
    public DiziOrder receiveOrderFromRabbit() {
        return rabbitOrderMessagingService.receiveOrder();
    }

    @GetMapping("/receive/kafka")
    public DiziOrder receiveOrderFromKafka() {
        return kafkaOrderMessagingService.receiveOrder();
    }
}
