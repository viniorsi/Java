package com.br.TravelEase.EmailSmsSender.Email;

import com.br.TravelEase.EmailSmsSender.html.HtmlTemplateService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private HtmlTemplateService htmlTemplateService;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmailTexto(String destinatario,String verificationCode, String assunto) {

        try {

            String mensagemHtml = htmlTemplateService.getTemplateContent(1L);

            EmailMessage emailMessage = getEmailMessage(destinatario,verificationCode, mensagemHtml);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(emailMessage.getTo());
            helper.setSubject(emailMessage.getSubject());
            helper.setText(emailMessage.getBody(), true);

            for (int i = 0; i < emailMessage.getAttachments().size(); i++) {
                String caminhoImagem = emailMessage.getAttachments().get(i);
                File file = new File(caminhoImagem);
                if (file.exists()) {
                    helper.addInline("imagemInline" + i, file);
                } else {
                    return "Erro ao tentar enviar email: Imagem não encontrada";
                }
            }


            javaMailSender.send(mimeMessage);
            return "Email enviado";
        } catch (Exception e) {
            return "Erro ao tentar enviar email: " + e.getLocalizedMessage();
        }
    }

    private static EmailMessage getEmailMessage(String user,String verificationCode, String mensagemHtml) {
        List<String> caminhosImagems = Arrays.asList(
                "src/Assets/email/TravelEase.png",
                "src/Assets/email/ImagemTravelEaseEmail.jpg"
        );
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(user);
        emailMessage.setSubject("Welcome to TravelEase!");
        emailMessage.setBody(mensagemHtml.replace("1 2 3 4 5 6", verificationCode));
        emailMessage.setAttachments(caminhosImagems);
        return emailMessage;
    }
}
