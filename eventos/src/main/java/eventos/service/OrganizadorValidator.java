package eventos.service;

import eventos.dto.ValidacionOrganizadorDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizadorValidator {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean validarOrganizador(Long usuarioId) {
        ValidacionOrganizadorDTO request = new ValidacionOrganizadorDTO();
        request.setUsuarioId(usuarioId);
        request.setAccion("VALIDAR");

        // Envía y recibe respuesta síncrona (puede ser asíncrona con @RabbitListener)
        ValidacionOrganizadorDTO response = (ValidacionOrganizadorDTO) rabbitTemplate.convertSendAndReceive(
                "eventos.exchange",
                "usuarios.validar.organizador", // Cola en microservicio usuarios
                request
        );

        return response != null && response.isValido();
    }
}
