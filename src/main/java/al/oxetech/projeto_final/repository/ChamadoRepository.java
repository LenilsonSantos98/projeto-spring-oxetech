package al.oxetech.projeto_final.repository;

import al.oxetech.projeto_final.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository <Chamado, Long> {
    List<Chamado> findByClienteId(Long clienteId);
}
