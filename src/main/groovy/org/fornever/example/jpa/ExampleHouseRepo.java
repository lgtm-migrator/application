package org.fornever.example.jpa;

import org.fornever.example.model.ExampleHouse;
import org.springframework.data.repository.CrudRepository;

public interface ExampleHouseRepo extends CrudRepository<ExampleHouse, Long> {

}
