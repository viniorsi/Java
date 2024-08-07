package med.voll.api.domain.Paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizaPaciente(
        @NotNull
        Long id,
        String telefone,
        String email,
        DadosEndereco endereco

) {
}
