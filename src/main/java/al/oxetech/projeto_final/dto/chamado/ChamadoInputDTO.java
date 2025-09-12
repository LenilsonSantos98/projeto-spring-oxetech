package al.oxetech.projeto_final.dto.chamado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChamadoInputDTO {

@NotBlank
    private String descricao;

@NotNull
    private Long clienteId;
}
