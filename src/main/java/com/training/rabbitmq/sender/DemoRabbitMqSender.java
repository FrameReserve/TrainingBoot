package com.training.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoRabbitMqSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String content) {
		System.out.println("Sender : " + content);
		this.rabbitTemplate.convertAndSend("hello", content);
	}
	
}
