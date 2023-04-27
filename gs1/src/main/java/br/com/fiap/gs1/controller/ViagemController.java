import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller
public class ViagemController {
	@RequestMapping("/viagens")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("viagens/index");
		 
		//criar uma viagem para teste 
	//	ViagemModel v1 = new ViagemModel ();
		ProdutoModel monitor = new ProdutoModel("Monitor", new BigDecimal(300), 100, 1 );
		
		List<ProdutoModel> produtos = Arrays.asList(monitor);
		modelView.addObject("produtos", produtos);
			
		return modelView;		
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView modelView = new ModelAndView("viagens/create");
		
		//criar uma viagem para teste 
		//	ViagemModel v1 = new ViagemModel ();
		ProdutoModel monitor = new ProdutoModel("Monitor", new BigDecimal(300), 100, 1 );
		
		List<ViagemModel> viagens = Arrays.asList(v1);
		modelView.addObject("viagens", viagens);
			
		return modelView;		
	}
	
	
	
	
	@PostMapping("/create")
	public ResponseEntity<ViagemModel> create(@Valid @RequestBody ViagemModel model)
	{
		
		return new ResponseEntity(model, HttpStatus.CREATED);
	}
}
