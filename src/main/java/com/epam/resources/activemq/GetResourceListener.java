package com.epam.resources.activemq;

import com.epam.resources.api.ResourceController;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class GetResourceListener {
    @Autowired
    private ResourceController resourceController;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "${spring.activemq.queues.resource-get}", containerFactory = "queueListenerFactory")
    @SendTo("${spring.activemq.queues.resource-content}")
    @Retryable(value = Exception.class)
    public byte[] receiveMessageFromQueue(Message jsonMessage) throws JMSException {
        ActiveMQObjectMessage message = (ActiveMQObjectMessage) jsonMessage;
        Long resourceId = (Long) message.getObject();
        log.info("Received Get resource, id: " + resourceId + " from queue");
        return resourceController.getResourceById(resourceId);
    }
}
