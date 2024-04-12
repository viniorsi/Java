package TGDI.API.DTOS.Empresa;

import TGDI.API.Entities.Empresa;

public record DetalhamentoEmpresaDTO(Long id, String CNPJ, String nome,String email, Double saldo, Double taxa) {

    public DetalhamentoEmpresaDTO(Empresa empresa) {

        this(empresa.getId(), empresa.getCNPJ(), empresa.getNome(), empresa.getEmail(), empresa.getSaldo(), empresa.getTaxa());

    }


}
