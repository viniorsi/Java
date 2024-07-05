package com.viniorsi.TravelEase.Service.User;

import com.viniorsi.TravelEase.Domain.User.DTO.DTOUserDetails;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Enums.StatusEnum;
import com.viniorsi.TravelEase.Domain.UserVerification.Entity.UserVerification;
import com.viniorsi.TravelEase.Repository.User.UserRespository;
import com.viniorsi.TravelEase.Repository.UserVerification.UserVerificationRepository;
import com.viniorsi.TravelEase.Service.Email.EmailService;
import com.viniorsi.TravelEase.Utils.EncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserVerificationRepository userVerificationRepository;

    @Autowired
    private EncryptDecrypt encryptDecrypt;

    public DTOUserDetails createUser(User userData) throws Exception {
        var userExists = userRepository.findByCpf(userData.getCpf());

        if (userExists == null) {
            userData.setPassword(encryptDecrypt.passwordEncoder().encode(userData.getPassword()));
            userRepository.save(userData);
            UserVerification userVerification = new UserVerification(userData);

            var secret = EncryptDecrypt.getSecretKey();

            String mensagemHtml = "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Bem-vindo ao TravelEase</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            background-color: #f4f4f4;\n" +
                    "        }\n" +
                    "        .container {\n" +
                    "            width: 100%;\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-color: #ffffff;\n" +
                    "            padding: 20px;\n" +
                    "            box-shadow: 0 0 10px rgba(0,0,0,0.1);\n" +
                    "        }\n" +
                    "        .header {\n" +
                    "            text-align: center;\n" +
                    "            padding: 10px 0;\n" +
                    "        }\n" +
                    "        .header img {\n" +
                    "            width: 100px;\n" +
                    "        }\n" +
                    "        .header h1 {\n" +
                    "            margin: 0;\n" +
                    "            color: #333333;\n" +
                    "        }\n" +
                    "        .hero {\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px 0;\n" +
                    "        }\n" +
                    "        .hero img {\n" +
                    "            width: 50%;\n" +
                    "            height: auto;\n" +
                    "            border-radius: 10px;\n" +
                    "        }\n" +
                    "        .content {\n" +
                    "            padding: 20px;\n" +
                    "            color: #555555;\n" +
                    "            line-height: 1.6;\n" +
                    "        }\n" +
                    "        .content h2 {\n" +
                    "            color: #333333;\n" +
                    "        }\n" +
                    "        .promotion {\n" +
                    "            text-align: center;\n" +
                    "            margin: 20px 0;\n" +
                    "            padding: 20px;\n" +
                    "            background-color: #f9f9f9;\n" +
                    "            border-radius: 10px;\n" +
                    "            border-color: black;" +
                    "        }\n" +
                    "        .promotion h3 {\n" +
                    "            color: #d9534f;\n" +
                    "        }\n" +
                    "        .promotion h2{" +
                    "           color:#d9534f;\n" +
                    "             }" +
                    ".verification-code {\n" +
                    "  display: flex;\n" +
                    "  justify-content: space-between;" +
                    "}\n" +
                    "}" +
                    "        .footer {\n" +
                    "            text-align: center;\n" +
                    "            padding: 10px 0;\n" +
                    "            color: #999999;\n" +
                    "            font-size: 14px;\n" +
                    "        }\n" +
                    "        .footer a {\n" +
                    "            color: #d9534f;\n" +
                    "            text-decoration: none;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"header\">\n" +
                    "            <img src=\"cid:imagemInline0\" alt=\"TravelEase Logo\">\n" +
                    "            <h1>Bem-vindo ao TravelEase</h1>\n" +
                    "        </div>\n" +
                    "        <div class=\"hero\">\n" +
                    "            <img src=\"cid:ImagemInline1\" alt=\"Imagem de Destino\">\n" +
                    "        </div>\n" +
                    "        <div class=\"content\">\n" +
                    "            <h2>Olá, Viajante!</h2>\n" +
                    "            <p>Estamos entusiasmados em tê-lo conosco. No TravelEase, nossa missão é tornar suas viagens inesquecíveis. Explore destinos incríveis, descubra promoções exclusivas e planeje a viagem dos seus sonhos com facilidade.</p>\n" +
                    "            <div class=\"promotion\">\n" +
                    "                <h3>Aqui esta seu Codigo de verificação:" +
                    "               <h2>1 2 3 4 5 6</h2>\n" +
                    "            </div>\n" +
                    "            <p>Se precisar de qualquer ajuda, não hesite em nos contatar. Estamos aqui para garantir que sua experiência seja perfeita.</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"footer\">\n" +
                    "            <p>TravelEase, Rua das Viagens, 123, São Paulo - SP</p>\n" +
                    "            <p>© 2024 TravelEase. Todos os direitos reservados.</p>\n" +
                    "            <p><a href=\"https://www.travelease.com/unsubscribe\">Cancelar inscrição</a></p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";

            List<String> caminhosImagems = Arrays.asList(
                    "D:\\User\\Desktop\\git\\Java\\TravelEase\\src\\Assets\\TravelEase.png",
                    "D:\\User\\Desktop\\git\\Java\\TravelEase\\src\\Assets\\ImagemTravelEaseEmail.jpg"
            );

            emailService.enviarEmailTexto(
                    userData.getEmail(),
                    "Welcome to TravelEase!",
                    mensagemHtml.replace("1 2 3 4 5 6", userVerification.getVerificationCode()),
                    caminhosImagems
            );

            userVerification.setVerificationCode(EncryptDecrypt.encrypt(userVerification.getVerificationCode(), secret));
            userVerificationRepository.save(userVerification);
            return new DTOUserDetails(userData, userVerification);
        } else {
            throw new Exception("Usuario ja existente");
        }
    }

    public User codeVerification(String cpf, String verificationCode) throws Exception {

        User user = userRepository.findByCpf(cpf);

        if (user == null) {
            throw new Exception("Usuario não existe");
        }

        try {
            var secret = EncryptDecrypt.getSecretKey();
            UserVerification userVerification = userVerificationRepository.findByUser(user);
            if (EncryptDecrypt.decrypt(userVerification.getVerificationCode(), secret).matches(verificationCode) && userVerification.getExpirationDate().isBefore(userVerification.getExpirationDate().plusHours(2))) {
                user.setStatus(StatusEnum.A);
                userRepository.save(user);
                return user;
            } else {
                throw new Exception("Codigo de verificação invalido ou expirado");
            }

        } catch (Exception e) {
            throw new Exception("Usuario não existe");
        }
    }
}
