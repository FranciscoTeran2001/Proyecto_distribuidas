package usuarios.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange for user validations
    @Bean
    public DirectExchange usuariosExchange() {
        return new DirectExchange("usuarios.exchange");
    }

    // Queue for organizer validation requests
    @Bean
    public Queue validarOrganizadorQueue() {
        return new Queue("usuarios.validar.organizador", true);
    }

    // Binding between exchange and queue
    @Bean
    public Binding bindingValidarOrganizador() {
        return BindingBuilder.bind(validarOrganizadorQueue())
                .to(usuariosExchange())
                .with("usuarios.validar.organizador");
    }
}
