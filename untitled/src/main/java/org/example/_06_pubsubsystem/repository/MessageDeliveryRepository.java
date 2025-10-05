package org.example._06_pubsubsystem.repository;

import org.example._06_pubsubsystem.domain.DeliveryStatus;
import org.example._06_pubsubsystem.domain.MessageDelivery;

import java.util.List;

public interface MessageDeliveryRepository {

    MessageDelivery save(MessageDelivery delivery);
    List<MessageDelivery> findPendingBySubscriber(String subscriberId);
    void updateDeliveryStatus(String deliveryId, DeliveryStatus status);
    void deleteById(String deliveryId);

}
