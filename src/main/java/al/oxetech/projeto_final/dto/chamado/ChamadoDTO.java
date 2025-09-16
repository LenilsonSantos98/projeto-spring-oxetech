package al.oxetech.projeto_final.dto.chamado;

import al.oxetech.projeto_final.dto.usuario.UsuarioDTO;
import al.oxetech.projeto_final.model.Chamado;
import al.oxetech.projeto_final.model.Status;
import lombok.*;


public class ChamadoDTO {
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UsuarioDTO getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioDTO cliente) {
        this.cliente = cliente;
    }

    public UsuarioDTO getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioDTO responsavel) {
        this.responsavel = responsavel;
    }

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
