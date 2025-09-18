package al.oxetech.projeto_final.controller;


import al.oxetech.projeto_final.dto.chamado.ChamadoDTO;
import al.oxetech.projeto_final.dto.chamado.ChamadoInputDTO;
import al.oxetech.projeto_final.dto.usuario.UsuarioDTO;
import al.oxetech.projeto_final.model.Chamado;
import al.oxetech.projeto_final.model.Role;
import al.oxetech.projeto_final.model.Status;
import al.oxetech.projeto_final.model.Usuario;
import al.oxetech.projeto_final.service.ChamadoService;
import al.oxetech.projeto_final.service.JwtService;
import al.oxetech.projeto_final.service.UsuarioService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    public ChamadoController(ChamadoService chamadoService, UsuarioService usuarioService, JwtService jwtService){

        this.chamadoService = chamadoService;
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }
    @PostMapping
    public ResponseEntity<ChamadoDTO> criarChamado(@RequestBody @Valid ChamadoInputDTO dto, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ","");
        Claims claims = jwtService.validarToken(token);
        Long id = Long.parseLong(claims.getSubject());
        dto.setClienteId(id);

        ChamadoDTO novoChamado = chamadoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoChamado);
    }
    @GetMapping
    public List<ChamadoDTO> listarTodos(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ","");
        Claims claims = jwtService.validarToken(token);
        Long id = Long.parseLong(claims.getSubject());

        UsuarioDTO usuario = usuarioService.buscarPorId(id);

        if(usuario.getRole() == Role.CLIENTE){
            return  chamadoService.listarPorCliente(id);
        } else {
            return chamadoService.listarTodos();
        }

    }

    @PatchMapping("{chamadoId}/atribuir/{suporteId}")
    public ResponseEntity<ChamadoDTO> atribuirChamado(@PathVariable Long chamadoId, @PathVariable Long suporteId){
        ChamadoDTO chamadoAtualizado = chamadoService.atribuirChamado(chamadoId, suporteId);
        return ResponseEntity.ok(chamadoAtualizado);
    }

    @PatchMapping("/{chamadoId}/finalizar")
    public ResponseEntity<ChamadoDTO> finalizarChamdo(@PathVariable Long chamadoId){
        ChamadoDTO chamadoFinalizado = chamadoService.finalizarChamado(chamadoId);
        return  ResponseEntity.ok(chamadoFinalizado);
    }

    @PatchMapping("/{chamadoId}/reabrir")
    public ResponseEntity<ChamadoDTO> reabrirChamado(@PathVariable Long chamadoId){
        ChamadoDTO chamadoReaberto = chamadoService.reabrirChamado(chamadoId);
        return ResponseEntity.ok(chamadoReaberto);
    }

}
