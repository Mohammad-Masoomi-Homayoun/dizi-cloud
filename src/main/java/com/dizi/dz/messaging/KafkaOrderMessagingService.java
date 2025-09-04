package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;
import jakarta.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("kafka")
public class KafkaOrderMessagingService implements OrderMessagingService {

    @Resource
    private KafkaTemplate<String, DiziOrder> kafkaTemplate;

    @Override
    public void sendOrder(DiziOrder order) {
        kafkaTemplate.send("dizi-cloud.orders.queue", order);
        kafkaTemplate.sendDefault(order);
    }

    @Override
    public DiziOrder receiveOrder() {
        return null;
    }
}
