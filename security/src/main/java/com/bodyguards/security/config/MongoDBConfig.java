package com.bodyguards.security.config;

import com.bodyguards.security.Report;
import com.bodyguards.security.repository.ReportsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = ReportsRepository.class)
@Configuration
public class MongoDBConfig {
}
