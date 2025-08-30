package com.dizi.dz.messaging;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@Configuration
public class MessagingConfig {

//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
//        return messageConverter;
//    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
