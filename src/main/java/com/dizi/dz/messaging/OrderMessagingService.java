package com.dizi.dz.messaging;

import com.dizi.dz.entity.DiziOrder;

public interface OrderMessagingService {

    void sendOrder(DiziOrder order);

    DiziOrder receiveOrder();
}
