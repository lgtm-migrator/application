package org.fornever.example

import org.fornever.example.model.ExampleHouse
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ComponentScan(basePackageClasses=ExampleConfiguration.class)
@EnableJpaRepositories(basePackageClasses=ExampleConfiguration.class)
@EntityScan(basePackageClasses=ExampleHouse.class)
class ExampleConfiguration {
}
