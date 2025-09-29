package com.dizi.dz.integrations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

@Configuration
public class EmailOrderingFlow {

    @Bean
    public IntegrationFlow tacoOrderEmailFlow(EmailProperties emailProps,
                                              EmailToOrderTransformer emailToOrderTransformer,
                                              OrderSubmitMessageHandler orderSubmitHandler) {
        return IntegrationFlow
                .from(Mail.imapInboundAdapter(emailProps.getImapUrl()),
                        e -> e.poller(Pollers.fixedDelay(emailProps.getPollRate()))) // comp 1: inbound adapter
                .transform(emailToOrderTransformer) // comp 2: transformer
                .handle(orderSubmitHandler) // comp 3: service activator
                .get();
    }
}
