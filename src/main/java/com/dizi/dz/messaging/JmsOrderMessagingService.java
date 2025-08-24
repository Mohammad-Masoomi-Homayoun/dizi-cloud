package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService {

    private JmsTemplate jms;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    public void sendOrder(DiziOrder order) {
        jms.convertAndSend("orders", order);
    }
}
