package TGDI.API.DTOS.Cliente;

import jakarta.validation.constraints.NotNull;

public record TransacaoDto(
        @NotNull
        String id,
        @NotNull
        String id_empresa,
        @NotNull
        Double valor

) {
}
