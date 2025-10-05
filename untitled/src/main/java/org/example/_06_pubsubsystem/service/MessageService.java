package org.example._06_pubsubsystem.service;

import org.example._06_pubsubsystem.repository.MessageDeliveryRepository;

public class MessageService {

    private MessageDeliveryRepository messageDeliveryRepository;

    public MessageService(MessageDeliveryRepository messageDeliveryRepository) {
        this.messageDeliveryRepository = messageDeliveryRepository;
    }

    public void acknowledgeMessage(String messageId,String subscriberId){
        // TODO: Find delivery record and update status
        System.out.println("Acknowledged message " + messageId + " by subscriber " + subscriberId);
        // messageDeliveryRepository.updateDeliveryStatus(deliveryId, DeliveryStatus.ACKNOWLEDGED);
    }

}
