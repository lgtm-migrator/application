package org.fornever.example.model

import javax.persistence.Column
import javax.persistence.Entity

import org.fornever.base.annotations.BusinessObject
import org.fornever.base.model.BaseBusinessObject

/**
 * An example entity
 * 
 * @author Theo Sun
 *
 */
@BusinessObject
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
