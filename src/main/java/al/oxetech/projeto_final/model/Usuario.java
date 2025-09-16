package al.oxetech.projeto_final.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;


    private String senha;

    private Role role;


    public Usuario(Object clienteId, String nomeCliente, String mail, String senha, Object o) {
    }

    public Usuario() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() { return email; }

    public Role getRole() { return  role; }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email =  email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
