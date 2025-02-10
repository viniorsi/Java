package com.br.TravelEase.EmailSmsSender.Listener

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.io.File

@Service
class EmailService {



    private val javaMailSender: JavaMailSender? = null

    @Value("\${spring.mail.username}")
    private val remetente: String? = null

    fun enviarEmailTexto(
        destinatario: String,
        assunto: String,
        mensagemHtml: String,
        caminhosImagens: MutableList<String>
    ): String {
        try {
            val mimeMessage = javaMailSender!!.createMimeMessage()
            val helper = MimeMessageHelper(mimeMessage, true, "UTF-8")

            helper.setFrom(remetente!!)
            helper.setTo(destinatario)
            helper.setSubject(assunto)
            helper.setText(mensagemHtml, true)

            for (i in caminhosImagens.indices) {
                val caminhoImagem = caminhosImagens.get(i)
                val file = File(caminhoImagem)
                if (file.exists()) {
                    helper.addInline("imagemInline" + i, file)
                } else {
                    return "Erro ao tentar enviar email: Imagem não encontrada"
                }
            }


            javaMailSender.send(mimeMessage)
            return "Email enviado"
        } catch (e: Exception) {
            return "Erro ao tentar enviar email: " + e.getLocalizedMessage()
        }
    }
}