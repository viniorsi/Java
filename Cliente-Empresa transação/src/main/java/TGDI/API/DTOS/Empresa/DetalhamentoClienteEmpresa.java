package TGDI.API.DTOS.Empresa;

import TGDI.API.Entities.Cliente;
import TGDI.API.Entities.Empresa;

public record DetalhamentoClienteEmpresa(Long id_cliente, Double saldo,String emailC, Long id_empresa, Double SaldoE, String emailE) {


    public DetalhamentoClienteEmpresa(Empresa empresa, Cliente cliente) {

        this(cliente.getId(), cliente.getSaldo(), cliente.getEmail(), empresa.getId(), empresa.getSaldo(), empresa.getEmail());


    }
}
