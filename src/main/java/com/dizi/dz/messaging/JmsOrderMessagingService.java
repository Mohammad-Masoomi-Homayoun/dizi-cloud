package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService {

    private JmsTemplate jmsTemplate;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        // advantages of constructor injection:
            // fail-fast
            // special business logic
            // not using reflection because of avoid overhead in injection time but in creation time we use reflection anyhow (Objenesis library)
            // keyword for more searches: CGLIB
            // autowired not using reflection at injection time not creation time
            // with construction injection we have a immutable component which will not change after creation
    }

    public void sendOrder(DiziOrder order) {
        jmsTemplate.convertAndSend("orders", order);
    }
}
