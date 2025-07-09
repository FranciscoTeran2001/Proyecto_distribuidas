package eventos.service;

import eventos.dto.EventoDTO;

import eventos.entity.Evento;

import eventos.enums.EstadoEvento;
import eventos.enums.ValidacionOrganizadorEstado;
import eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private OrganizadorValidator organizadorValidator;

    @Transactional
    public Evento crearEvento(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescripcion(dto.getDescripcion());
        evento.setFechaHora(dto.getFechaHora());
        evento.setUbicacion(dto.getUbicacion());
        evento.setTipo(dto.getTipo());
        evento.setEstado(EstadoEvento.ACTIVO);
        evento.setOrganizadorId(dto.getOrganizadorId());
        evento.setValidacionEstado(ValidacionOrganizadorEstado.PENDIENTE); // Ahora accesible

        Evento eventoGuardado = eventoRepository.save(evento);
        organizadorValidator.validarOrganizador( dto.getOrganizadorId());

        return eventoGuardado;
    }
}