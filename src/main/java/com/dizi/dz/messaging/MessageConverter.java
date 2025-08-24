package com.dizi.dz.messaging;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.jms.support.converter.MessageConversionException;

public interface MessageConverter {
    Message toMessage(Object object, Session session) throws JMSException, MessageConversionException;

    Object fromMessage(Message message);
}