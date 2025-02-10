package com.br.TravelEase.EmailSmsSender.Listener

import com.br.TravelEase.EmailSmsSender.Config.RabbitMQConfig
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class EmailConsumer(
    private val emailService: EmailService
) {


    @RabbitListener(queues = [RabbitMQConfig.QUEUE_NAME])
    fun receiveEmailMessage(emailMessage: EmailMessage) {
        emailService.enviarEmailTexto(
            emailMessage.to,
            emailMessage.subject,
            emailMessage.body,
            emailMessage.attachments as MutableList<String>
        );
    }

}