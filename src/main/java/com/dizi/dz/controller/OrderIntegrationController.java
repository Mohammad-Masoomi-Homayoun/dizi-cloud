package com.dizi.dz.controller;

import com.dizi.dz.integrations.OrderIntegrationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/orders/integrations")
public class OrderIntegrationController {

    private static Logger LOGGER = Logger.getLogger(OrderIntegrationController.class.getName());

    @Resource
    private OrderIntegrationService orderIntegrationService;

    @GetMapping("{name}/{data}")
    public String testIntegration(@PathVariable String name, @PathVariable String data) {
        LOGGER.info(String.format("Received request to write file: %s with data: %s", name, data));
        return orderIntegrationService.writeToFile(name, data);
    }
}
