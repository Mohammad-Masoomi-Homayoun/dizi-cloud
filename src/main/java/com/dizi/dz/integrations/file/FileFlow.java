package com.dizi.dz.integrations.file;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
public class FileFlow {

    private static final String PATH = "/Users/nlmxm4200/Documents/programming/workspace/dizi-cloud/src/main/resources";

    @Bean
    @ServiceActivator(inputChannel = "trashChannel")
    public MessageHandler fileWriter() {
        return msg -> System.out.println("Message discarded: " + msg);
    }

    @Bean
    public IntegrationFlow fileWriterFlow() {
        return IntegrationFlow
                .from(MessageChannels.direct("textInChannel"))
                .<String, Integer>transform(Integer::parseInt)
                .<Integer>filter(i -> i % 2 == 0,
                        e -> e.discardChannel("trashChannel"))
                .<Integer, String>transform(Object::toString)
                .handle(Files
                        .outboundAdapter(new File(PATH))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))
                .get();
    }

}
