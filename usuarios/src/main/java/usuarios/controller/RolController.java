package usuarios.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usuarios.dto.ResponseDto;
import usuarios.dto.RolDTO;
import usuarios.service.RolService;


@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping
    public ResponseEntity<ResponseDto> crearRol(@Valid @RequestBody RolDTO dto) {
        return ResponseEntity.ok(rolService.crearRol(dto));
    }
}