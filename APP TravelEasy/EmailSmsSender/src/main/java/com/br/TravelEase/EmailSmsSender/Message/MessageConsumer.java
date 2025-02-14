package com.br.TravelEase.EmailSmsSender.Message;

import com.br.TravelEase.EmailSmsSender.Config.RabbitMQConfig;
import com.br.TravelEase.EmailSmsSender.Email.EmailService;
import com.br.TravelEase.EmailSmsSender.Email.EmailSmsRequest;
import com.br.TravelEase.EmailSmsSender.Email.VerificationType;
import com.br.TravelEase.EmailSmsSender.SMS.SMSService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @Autowired
    private EmailService emailService;
    @Autowired
    private SMSService smsService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveEmailMessage(EmailSmsRequest emailMessage) {
        try {
            if (VerificationType.EMAIL.equals(emailMessage.verificationtype())) {
                emailService.enviarEmailTexto(
                        emailMessage.email(),
                        emailMessage.verificiationCode(),
                        "Welcome to TravelEase!"
                );
            }else {
                String destinationNumber = "+55" + emailMessage.ddd() + emailMessage.tel();
                smsService.sendSMS(destinationNumber, "Seu codigo de verificação é: " + emailMessage.verificiationCode());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("nao conseguiu ler a mensagem" + e);
        }


    }

}
