package al.oxetech.projeto_final.controller;

//import al.oxetech.projeto_final.dto.admin.AtualizarRoleDTO;

import al.oxetech.projeto_final.dto.usuario.UsuarioDTO;
import al.oxetech.projeto_final.model.Role;
import al.oxetech.projeto_final.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/usuarios")
public class AdminController {
    private  final UsuarioService usuarioService;

    public AdminController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PatchMapping("/{id}/atualizar-role")
    public ResponseEntity<UsuarioDTO> atualizarRole( @PathVariable Long id, @RequestParam Role novaRole){
        UsuarioDTO usuarioAtualizado = usuarioService.atualizarRole(id, novaRole);
        return ResponseEntity.ok(usuarioAtualizado);
    }
}
