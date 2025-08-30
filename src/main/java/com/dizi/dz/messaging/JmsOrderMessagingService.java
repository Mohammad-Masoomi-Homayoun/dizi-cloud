package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("jms")
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(DiziOrder order) {
        jmsTemplate.convertAndSend(order, this::addOrderSource);
    }

    @Override
    public DiziOrder receiveOrder() {
        return (DiziOrder) jmsTemplate.receiveAndConvert();
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
