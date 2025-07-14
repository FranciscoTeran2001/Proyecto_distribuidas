package eventos.controller;

import eventos.dto.EventoDTO;
import eventos.entity.Evento;
import eventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody EventoDTO eventoDTO) {
        Evento eventoCreado = eventoService.crearEvento(eventoDTO);
        return ResponseEntity.ok(eventoCreado);
    }
}
