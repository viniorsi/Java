package TGDI.API.DTOS.Cliente;

import TGDI.API.Entities.Cliente;

public record DetalhamentoClienteDTO(
           Long id,
         String CPF,
         String nome,
         String email,
         Double saldo,
         Long id_Empresa) {
    public DetalhamentoClienteDTO(Cliente cliente) {

        this(cliente.getId(), cliente.getCPF(), cliente.getNome(), cliente.getEmail(), cliente.getSaldo(), cliente.getId_Empresa());

    }
}
