package al.oxetech.projeto_final.controller;


import al.oxetech.projeto_final.dto.chamado.ChamadoDTO;
import al.oxetech.projeto_final.dto.chamado.ChamadoInputDTO;
import al.oxetech.projeto_final.model.Chamado;
import al.oxetech.projeto_final.model.Status;
import al.oxetech.projeto_final.model.Usuario;
import al.oxetech.projeto_final.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService){
        this.chamadoService = chamadoService;
    }
    @PostMapping
    public ResponseEntity<ChamadoDTO> criarChamado(@RequestBody @Valid ChamadoInputDTO dto){
        ChamadoDTO novoChamado = chamadoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoChamado);
    }
    @GetMapping
    public void listar(@RequestParam(required = false)String nome, Pageable pageable){};
}
