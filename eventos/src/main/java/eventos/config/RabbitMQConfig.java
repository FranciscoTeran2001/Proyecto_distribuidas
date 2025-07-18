package eventos.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange for eventos
    @Bean
    public DirectExchange eventosExchange() {
        return new DirectExchange("eventos.exchange");
    }

    // Queue for validating organizers (outgoing requests)
    @Bean
    public Queue validarOrganizadorQueue() {
        return new Queue("eventos.validar.organizador", true);
    }

    // Binding between exchange and queue
    @Bean
    public Binding bindingValidarOrganizador() {
        return BindingBuilder.bind(validarOrganizadorQueue())
                .to(eventosExchange())
                .with("eventos.validar.organizador");
    }
}
