package al.oxetech.projeto_final.service;

import al.oxetech.projeto_final.dto.chamado.ChamadoDTO;
import al.oxetech.projeto_final.dto.chamado.ChamadoInputDTO;
import al.oxetech.projeto_final.model.Chamado;
import al.oxetech.projeto_final.model.Role;
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

        if (cliente.getRole() != Role.CLIENTE){
            throw new IllegalArgumentException("Apenas clientes podem abrir chamados.");
        }

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

    public List<ChamadoDTO> listarPorCliente(Long clienteId){
        return chamadoRepository.findByClienteId(clienteId)
                .stream()
                .map(ChamadoDTO::new)
                .collect(Collectors.toList());
    }

    //Lógica para atribuir um chamado
    public ChamadoDTO atribuirChamado (Long chamadoId, Long suporteId){

        Chamado chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));

        Usuario suporte = usuarioRepository.findById(suporteId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de suporte não encontrado"));

        //atribui o responsável e atualiza o status
        chamado.setResponsavel(suporte);
        chamado.setStatus(Status.EM_ATENDIMENTO);

        return new ChamadoDTO(chamadoRepository.save(chamado));
    }

    public ChamadoDTO finalizarChamado (Long chamadoId){
        Chamado chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));
        chamado.setStatus(Status.CONCLUIDO);

        return  new ChamadoDTO(chamadoRepository.save(chamado));
    }

    public  ChamadoDTO reabrirChamado(Long chamadoId){
        Chamado chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));
        chamado.setStatus(Status.EM_ATENDIMENTO);

        return  new ChamadoDTO(chamadoRepository.save(chamado));
    }



}
