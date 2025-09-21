package com.dizi.dz.integrations;

import com.dizi.dz.integrations.file.FileWriterGateway;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderIntegrationService {

    @Resource
    private FileWriterGateway fileWriterGateway;

    public String writeToFile(String filename, String data) {

        fileWriterGateway.writeToFile(filename, data);

        return "File write initiated";
    }
}
