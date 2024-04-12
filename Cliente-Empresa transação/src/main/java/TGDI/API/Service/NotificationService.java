package TGDI.API.Service;


import TGDI.API.DTOS.Notificacao.NotificacaoDTO;
import TGDI.API.Entities.Cliente;
import TGDI.API.Entities.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(Object destinatario, String mensagem) throws Exception{
            String email = null;

                    if(destinatario instanceof Cliente) {
                        email = ((Cliente) destinatario).getEmail();
                    } else if (destinatario instanceof Empresa){
                        email =((Empresa) destinatario).getEmail();
                    }

        NotificacaoDTO notificacao = new NotificacaoDTO(email, mensagem);
        ResponseEntity<String> respostaNotificacao= restTemplate.postForEntity("https://webhook.site/abff89c8-750e-4068-bd82-e4909396db4a", notificacao , String.class);

        if(!(respostaNotificacao.getStatusCode() == HttpStatus.OK)){
            System.out.println("erro com as notificaçoes");
            throw new Exception("Serviço de pagamento está fora do ar");
        }



        }

}
