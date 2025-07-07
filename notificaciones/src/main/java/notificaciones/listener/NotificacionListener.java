package notificaciones.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import notificaciones.dto.NotificacionDTO;
import notificaciones.service.NotificacionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacionListener {

    @Autowired
    private NotificacionService service;

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = "notificacionesEventos.cola")
    public void recibirMensaje(String mensaje) {
        try {
            NotificacionDTO dto = mapper.readValue(mensaje, NotificacionDTO.class);
            service.guardar(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
