package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service("rabbit")
public class RabbitOrderMessagingService implements OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(DiziOrder order) {
        rabbitTemplate.convertAndSend(order);

//        MessageConverter converter = rabbitTemplate.getMessageConverter();
//        MessageProperties props = new MessageProperties();
//        props.setHeader("X_ORDER_SOURCE", "WEB");
//        Message message = converter.toMessage(order, props);
//        rabbitTemplate.send("tacocloud.order", message);

//        rabbitTemplate.convertAndSend(order,
//                new MessagePostProcessor() {
//                    @Override
//                    public Message postProcessMessage(Message message) throws AmqpException {
//                        MessageProperties props = message.getMessageProperties();
//                        props.setHeader("X_ORDER_SOURCE", "WEB");
//                        return message;
//                    }
//        });
    }

    @Override
    public DiziOrder receiveOrder() {
        return null;
    }
}
