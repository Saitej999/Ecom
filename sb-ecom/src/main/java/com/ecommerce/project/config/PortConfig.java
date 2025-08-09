package com.ecommerce.project.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class PortConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        String port = System.getenv("PORT");
        if (port != null && !port.isEmpty() && !port.equals("$PORT")) {
            try {
                int portNumber = Integer.parseInt(port);
                factory.setPort(portNumber);
                System.out.println("Setting port to: " + portNumber);
            } catch (NumberFormatException e) {
                System.out.println("Invalid PORT environment variable: " + port + ", using default 8080");
                factory.setPort(8080);
            }
        } else {
            System.out.println("No valid PORT environment variable found, using default 8080");
            factory.setPort(8080);
        }
    }
}
