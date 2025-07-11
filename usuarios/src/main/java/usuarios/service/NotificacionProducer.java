package usuarios.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usuarios.dto.NotificacionDTO;

@Service
public class NotificacionProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void enviarNotificacion(String mensaje, String tipo) {
        try {
            NotificacionDTO dto = new NotificacionDTO(mensaje, tipo);
            rabbitTemplate.convertAndSend("notificacionesEventos.cola", objectMapper.writeValueAsString(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}