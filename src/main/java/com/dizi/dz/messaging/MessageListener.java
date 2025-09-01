package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final String QUEUE_NAME = "dizi-cloud.order.queue";
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @JmsListener(destination = QUEUE_NAME)
    public void onMessage(DiziOrder order) {
        logger.info("Received JMS message: {}", order);
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveOrder(DiziOrder order) {
        logger.info("Received Rabbit message: {}", order);
    }
}
