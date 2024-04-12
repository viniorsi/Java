package TGDI.API.Controller;

import TGDI.API.DTOS.Empresa.CadastroEmpresaDTO;
import TGDI.API.DTOS.Empresa.DetalhamentoEmpresaDTO;
import TGDI.API.Entities.Empresa;
import TGDI.API.Repositories.EmpresaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

            @Autowired
            private EmpresaRepository repository;
         @Transactional
         @PostMapping
        public ResponseEntity cadastrarEmpresa(@RequestBody @Valid CadastroEmpresaDTO dados, UriComponentsBuilder uribuilder){

            var empresa = new Empresa(dados);
            empresa.setSaldo(1000.0) ;
            var cnpjValido = empresa.validaCnpj(empresa.getCNPJ());
             var uri = uribuilder.path("/empresas/{id}").buildAndExpand(empresa.getId()).toUri();
            if(cnpjValido != null){
                empresa.setCNPJ(cnpjValido);
                repository.save(empresa);
                return ResponseEntity.created(uri).body(new DetalhamentoEmpresaDTO(empresa));
            }else{
                return ResponseEntity.badRequest().body("Insira um Cnpj valido");
            }

        }




}
