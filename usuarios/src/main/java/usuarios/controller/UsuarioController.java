package usuarios.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usuarios.dto.LoginDTO;
import usuarios.dto.ResponseDto;
import usuarios.dto.UsuarioRegistroDTO;
import usuarios.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ResponseDto> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO dto) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> obtenerUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRegistroDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> eliminarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.eliminarUsuario(id));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(usuarioService.login(loginDTO));
    }
}
