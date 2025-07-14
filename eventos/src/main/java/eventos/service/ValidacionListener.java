package eventos.service;


import eventos.dto.ValidacionOrganizadorDTO;
import eventos.entity.Evento;
import eventos.enums.ValidacionOrganizadorEstado;
import eventos.repository.EventoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidacionListener {

    @Autowired
    private EventoRepository eventoRepository;

    @RabbitListener(queues = "eventos.validar.organizador")
    public void recibirRespuestaValidacion(ValidacionOrganizadorDTO respuesta) {
        if ("RESPUESTA".equals(respuesta.getAccion())) {
            List<Evento> eventos = eventoRepository.findByOrganizadorId(respuesta.getUsuarioId());

            Evento evento = eventos.stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            evento.setValidacionEstado(respuesta.isValido() ?
                    ValidacionOrganizadorEstado.VALIDO :
                    ValidacionOrganizadorEstado.INVALIDO);

            eventoRepository.save(evento);
        }
    }
}
