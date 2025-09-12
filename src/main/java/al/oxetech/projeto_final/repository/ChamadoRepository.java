package al.oxetech.projeto_final.repository;

import al.oxetech.projeto_final.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository <Chamado, Long> {
}
