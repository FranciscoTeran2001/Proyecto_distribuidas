package eventos.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange para eventos
    @Bean
    public DirectExchange eventosExchange() {
        return new DirectExchange("eventos.exchange");
    }

    // Cola para validar organizadores
    @Bean
    public Queue validarOrganizadorQueue() {
        return new Queue("eventos.validar.organizador", true);
    }

    // Binding entre exchange y cola
    @Bean
    public Binding bindingValidarOrganizador() {
        return BindingBuilder.bind(validarOrganizadorQueue())
                .to(eventosExchange())
                .with("eventos.validar.organizador");
    }
}
