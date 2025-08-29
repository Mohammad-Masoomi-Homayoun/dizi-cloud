package com.dizi.dz.controller;

import com.dizi.dz.entity.DiziOrder;
import com.dizi.dz.messaging.JmsOrderMessagingService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/orders")
public class OrderMessagingController {

    @Resource
    private JmsOrderMessagingService jmsOrderMessagingService;

    private Long idCounter = 0L;

    @PostMapping("/send")
    public void sendOrderViaJms(@RequestBody DiziOrder order) {

        order.setId(idCounter++);
        order.setPlacedAt(new Date());

        jmsOrderMessagingService.sendOrderViaJms(order);
    }

    @PostMapping("/send/rabbit")
    public void sendOrderViaRabbit(@RequestBody DiziOrder order) {

        order.setId(idCounter++);
        order.setPlacedAt(new Date());
        jmsOrderMessagingService.sendOrderViaRabbit(order);
    }

    @GetMapping("/receive")
    public DiziOrder receiveOrder() {
        return jmsOrderMessagingService.receiveOrder();
    }
}
