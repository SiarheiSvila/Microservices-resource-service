package com.epam.resources.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Slf4j
@Component
public class ResourceCreatedQueueProducer {

    @Autowired
    private JmsTemplate queueJmsTemplate;

    @Value("${spring.activemq.queues.resource-created}")
    private String queue;

    @Retryable(value = Exception.class)
    public void sendResourceCreatedToQueue(Long message) {
        log.info("Sending message " + message + " to  queue - " + queue);
        queueJmsTemplate.convertAndSend(queue, message);
    }
}
