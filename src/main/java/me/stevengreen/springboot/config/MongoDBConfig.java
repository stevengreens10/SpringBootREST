package me.stevengreen.springboot.config;

import me.stevengreen.springboot.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Config for MongoDB. All of the necessary configuration is done through the annotations.
 * Additional configuration for Mongo is found it resources/application.properties
 *
 * @author Steven Green
 */
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {
}
