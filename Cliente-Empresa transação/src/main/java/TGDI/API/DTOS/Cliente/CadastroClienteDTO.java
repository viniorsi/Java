package TGDI.API.DTOS.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroClienteDTO(
        @NotBlank
        @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$", message = "O campo deve ser um cpf valido")
        String CPF,
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        Double saldo,
        @NotNull
        Long id_Empresa)
{
}
