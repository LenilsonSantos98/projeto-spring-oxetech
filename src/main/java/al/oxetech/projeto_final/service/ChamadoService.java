package al.oxetech.projeto_final.service;

import al.oxetech.projeto_final.dto.chamado.ChamadoDTO;
import al.oxetech.projeto_final.dto.chamado.ChamadoInputDTO;
import al.oxetech.projeto_final.model.Chamado;
import al.oxetech.projeto_final.model.Status;
import al.oxetech.projeto_final.model.Usuario;
import al.oxetech.projeto_final.repository.ChamadoRepository;
import al.oxetech.projeto_final.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetBacked;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {
    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

    public ChamadoService(ChamadoRepository chamadoRepository, UsuarioRepository usuarioRepository){
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ChamadoDTO salvar(ChamadoInputDTO dto){
        Usuario cliente = usuarioRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Chamado novoChamado = new Chamado();
        novoChamado.setDescricao(dto.getDescricao());
        novoChamado.setCliente(cliente);
        novoChamado.setStatus(Status.ABERTO);

        return new ChamadoDTO(chamadoRepository.save(novoChamado));


    }

    public List<ChamadoDTO> listarTodos(){
        return chamadoRepository.findAll()
                .stream()
                .map(ChamadoDTO::new)
                .collect(Collectors.toList());
    }


}
