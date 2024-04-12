package TGDI.API.Controller;

import TGDI.API.DTOS.Cliente.CadastroClienteDTO;
import TGDI.API.DTOS.Cliente.DetalhamentoClienteDTO;
import TGDI.API.DTOS.Cliente.TransacaoDto;
import TGDI.API.DTOS.Empresa.CadastroEmpresaDTO;
import TGDI.API.DTOS.Empresa.DetalhamentoClienteEmpresa;
import TGDI.API.DTOS.Empresa.DetalhamentoEmpresaDTO;
import TGDI.API.Entities.Cliente;
import TGDI.API.Entities.Empresa;
import TGDI.API.Repositories.ClienteRepository;
import TGDI.API.Repositories.EmpresaRepository;
import TGDI.API.Service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EmpresaRepository repositoryEmpresa;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarCliente(@RequestBody @Valid CadastroClienteDTO dados, UriComponentsBuilder uribuilder){

        var cliente = new Cliente(dados);
        cliente.setSaldo(100.0) ;
        var cpfValido = cliente.validaCpf(cliente.getCPF());
        var uri = uribuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        if(cpfValido != null){
            cliente.setCPF(cpfValido);
            repository.save(cliente);
            return ResponseEntity.created(uri).body(new DetalhamentoClienteDTO(cliente));
        }else{
            return ResponseEntity.badRequest().body("Insira um cpf valido");
        }

    }



    @PutMapping("/deposito")
    @Transactional
    public ResponseEntity deposito(@RequestBody @Valid TransacaoDto transacao) throws Exception {
        double valorDaTaxa = 0.0;
        var cliente = repository.getReferenceById(Long.valueOf(transacao.id()));
        var empresa = repositoryEmpresa.getReferenceById(cliente.getId_Empresa());
        var valor = transacao.valor();
        valorDaTaxa = valor * empresa.getTaxa();
        valor += valorDaTaxa;
        if (cliente.getSaldo() >= valor) {
            cliente.deposito(valor);
            empresa.receberDeposito(valor);
            notificationService.sendNotification(cliente, "transação de R$: "+ valor+ " realizada com sucesso");
            notificationService.sendNotification(empresa, "transação de R$: "+ valor+ " recebida com sucesso");
            return ResponseEntity.ok(new DetalhamentoClienteEmpresa(empresa, cliente));
        } else {
            return ResponseEntity.badRequest().body("Cliente sem saldo para transferencia");
        }
    }

    @PutMapping("/saque")
    @Transactional
    public ResponseEntity saque(@RequestBody @Valid TransacaoDto transacao) throws Exception{

        var cliente = repository.getReferenceById(Long.valueOf(transacao.id()));
        var empresa = repositoryEmpresa.getReferenceById(cliente.getId_Empresa());
        var valor = transacao.valor();
        if (empresa.getSaldo() >= valor) {
            cliente.saque(valor);
            empresa.mandarSaque(valor);
            notificationService.sendNotification(cliente, "transação de R$: "+ valor+ " recebida com sucesso");
            notificationService.sendNotification(empresa, "transação de R$: "+ valor+ " realizada com sucesso");

            return ResponseEntity.ok(new DetalhamentoClienteEmpresa(empresa, cliente));
        } else {
            return ResponseEntity.badRequest().body("Empresa sem saldo para transferencia");
        }


    }





}
