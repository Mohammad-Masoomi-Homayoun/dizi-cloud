package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService {

    private final JmsTemplate jmsTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate, RabbitTemplate rabbitTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderViaJms(DiziOrder order) {
        jmsTemplate.convertAndSend(order, this::addOrderSource);
    }

    public void sendOrderViaRabbit(DiziOrder order) {
        rabbitTemplate.convertAndSend(order);
    }

    public DiziOrder receiveOrder() {
        return (DiziOrder) jmsTemplate.receiveAndConvert();
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
