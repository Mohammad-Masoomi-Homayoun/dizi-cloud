package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "dizi-cloud.order.queue")
    public void onMessage(DiziOrder order) {
        System.out.println("Received message: " + order);
    }
}
