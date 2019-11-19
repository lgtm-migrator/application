package org.fornever.example.model

import javax.persistence.Column
import javax.persistence.Entity

import org.fornever.base.model.BaseBusinessObject

@Entity
class ExampleHouse extends BaseBusinessObject<ExampleHouseState> {

	@Column
	Double area;

	@Column(length=255)
	String address;

	@Column(length=120)
	String city;

	@Column
	Double price;
}
