package com.ums.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ums.presentation",
        "com.ums.application",
        "com.ums.infrastructure"
})
// Important to detect JPA Entities
@EntityScan(basePackages = "com.ums.infrastructure.persistence.entities")
// Necessary to make beans of JpaRepositories
@EnableJpaRepositories(basePackages = "com.ums.infrastructure.persistence.repository")
public class UserManagementServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(UserManagementServiceApplication.class);

    }
}
