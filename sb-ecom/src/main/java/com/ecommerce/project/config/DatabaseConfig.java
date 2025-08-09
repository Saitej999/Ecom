package com.ecommerce.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${DATABASE_URL:}")
    private String databaseUrl;

    @Bean
    @Primary
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "prod")
    public DataSource dataSource() {
        // Handle Render's PostgreSQL URL format
        String jdbcUrl = databaseUrl;
        
        if (databaseUrl != null && databaseUrl.startsWith("postgresql://")) {
            // Convert Render's postgres:// URL to JDBC format
            jdbcUrl = databaseUrl.replace("postgresql://", "jdbc:postgresql://");
            System.out.println("Converted DATABASE_URL to JDBC format: " + jdbcUrl.replaceAll(":[^:@]*@", ":***@"));
        }
        
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            throw new IllegalStateException("DATABASE_URL environment variable is required for production");
        }
        
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
