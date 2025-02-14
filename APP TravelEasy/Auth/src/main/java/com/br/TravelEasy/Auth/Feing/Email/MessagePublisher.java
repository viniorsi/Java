package com.br.TravelEasy.Auth.Feing.Email;

import com.br.TravelEasy.Auth.infra.RabbiMq.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishEmail(EmailSmsRequest emailMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "email.send", emailMessage);
    }

}
