package edu.javeriana.reactiva.tallerreactiva;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class StreamConfig {
    @Bean
    public Sinks.Many<Long> notasStream(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }
}
