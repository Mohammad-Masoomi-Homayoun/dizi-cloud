package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final String TOPIC_NAME = "dizi-cloud.orders.topic";
//    private static final String QUEUE_NAME = "dizi-cloud.orders.queue";
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

//    @JmsListener(destination = QUEUE_NAME)
//    public void onMessage(DiziOrder order) {
//        logger.info("Received JMS message: {}", order);
//    }
//
//    @RabbitListener(queues = QUEUE_NAME)
//    public void receiveOrder(DiziOrder order) {
//        logger.info("Received Rabbit message: {}", order);
//    }

    @KafkaListener(topics = TOPIC_NAME, groupId = "dz-group")
    public void listen(DiziOrder order) {
        logger.info("Received Kafka message: {}", order);
    }

    @KafkaListener(topics = TOPIC_NAME, groupId = "dz-group")
    public void handle(DiziOrder order, ConsumerRecord<String, DiziOrder> dzRecord) {
        logger.info("Received from partition {} with timestamp {}", dzRecord.partition(), dzRecord.timestamp());
    }
}
