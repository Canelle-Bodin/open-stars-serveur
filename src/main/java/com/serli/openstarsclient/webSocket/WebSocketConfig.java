package com.serli.openstarsclient.webSocket;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.setApplicationDestinationPrefixes("/chat", "/app")
//                .enableSimpleBroker("/topic", "/telescope");

        config.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/telescope");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("ws", "/socket")
//                .setAllowedOrigins("*")
//                .withSockJS();

        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://192.168.86.105:8082");
            }
        };
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        logger.info("Client with username {} disconnected", event.getUser());
    }

}
