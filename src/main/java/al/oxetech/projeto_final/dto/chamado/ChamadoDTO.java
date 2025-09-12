package al.oxetech.projeto_final.dto.chamado;

import al.oxetech.projeto_final.dto.usuario.UsuarioDTO;
import al.oxetech.projeto_final.model.Chamado;
import al.oxetech.projeto_final.model.Status;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoDTO {
    private Long id;
    private String descricao;
    private Status status;
    private UsuarioDTO cliente;
    private UsuarioDTO responsavel;


    public ChamadoDTO(Chamado chamado){
        this.id = chamado.getId();
        this.descricao = chamado.getDescricao();
        this.status = chamado.getStatus();
        this.cliente = new UsuarioDTO(chamado.getCliente());
        this.responsavel = new UsuarioDTO(chamado.getResponsavel());
    }

}
