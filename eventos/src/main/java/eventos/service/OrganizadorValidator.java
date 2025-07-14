package eventos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import eventos.dto.ValidacionOrganizadorDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizadorValidator {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper mapper;

    public boolean validarOrganizador(Long usuarioId) {
        try {
            ValidacionOrganizadorDTO request = new ValidacionOrganizadorDTO();
            request.setUsuarioId(usuarioId);
            request.setAccion("VALIDAR");

            // Convertir a JSON
            String jsonRequest = mapper.writeValueAsString(request);

            // Enviar y esperar respuesta como String
            String jsonResponse = (String) rabbitTemplate.convertSendAndReceive(
                    "usuarios.exchange",
                    "usuarios.validar.organizador",
                    jsonRequest
            );

            // Convertir respuesta JSON a DTO
            if (jsonResponse != null) {
                ValidacionOrganizadorDTO response = mapper.readValue(jsonResponse, ValidacionOrganizadorDTO.class);
                return response.isValido();
            }

        } catch (Exception e) {
            e.printStackTrace(); // puedes manejarlo mejor con logs
        }

        return false;
    }

}
