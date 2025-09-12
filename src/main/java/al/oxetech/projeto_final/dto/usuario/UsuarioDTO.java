package al.oxetech.projeto_final.dto.usuario;

import al.oxetech.projeto_final.model.Role;
import al.oxetech.projeto_final.model.Usuario;
import lombok.*;

/**
 * Retornar informações do usuário
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private Role role;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.role = usuario.getRole();
    }

}
