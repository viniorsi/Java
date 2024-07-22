package com.viniorsi.TravelEase.Service.Email;

import com.viniorsi.TravelEase.Domain.Email.EmailMessage;
import com.viniorsi.TravelEase.Infra.RabbiMq.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveEmailMessage(EmailMessage emailMessage) {
        emailService.enviarEmailTexto(
                emailMessage.getTo(),
                emailMessage.getSubject(),
                emailMessage.getBody(),
                emailMessage.getAttachments()
        );
    }

}
