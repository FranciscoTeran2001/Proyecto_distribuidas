package notificaciones.service;

import notificaciones.dto.NotificacionDTO;
import notificaciones.entity.Notificacion;
import notificaciones.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository repository;

    public List<Notificacion> listarTodas() {
        return repository.findAll();
    }

    public void guardar(NotificacionDTO dto) {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setTipo(dto.getTipo());
        notificacion.setFecha(LocalDateTime.now());
        repository.save(notificacion);
    }

}
