package al.oxetech.projeto_final.dto.usuario;

import al.oxetech.projeto_final.model.Role;
import al.oxetech.projeto_final.model.Usuario;
import lombok.*;

/**
 * Retornar informações do usuário
 */

public class UsuarioDTO {
    private Long id;


    private String nome;
    private String email;
    private Role role;

    public UsuarioDTO(Usuario usuario) {
        if(usuario != null){
            this.id = usuario.getId();
            this.nome = usuario.getNome();
            this.email = usuario.getEmail();
            this.role = usuario.getRole();

        }
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
