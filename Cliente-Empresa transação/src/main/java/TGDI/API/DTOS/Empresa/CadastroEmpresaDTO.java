package TGDI.API.DTOS.Empresa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroEmpresaDTO(


        @Pattern(regexp = "(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)", message = "O campo deve ser um cnpj")
        String CNPJ,

        @NotBlank
        String nome,

        @NotBlank @Email
        String email,

        Double saldo,

        Double taxa
) {

}
