import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ValidationHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> erros = new HashMap<>();
		
		
		ex.getBindingResult().getAllErrors().forEach((erro) -> {
			String nomeCampo = ((FieldError)erro).getField();
			String mensagem = erro.getDefaultMessage();
			
			erros.put(nomeCampo, mensagem);			
		});
		
		return new ResponseEntity<Object>(erros, HttpStatus.BAD_REQUEST);
	}
}
