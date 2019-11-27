package org.fornever.example.model

import javax.persistence.Column
import javax.persistence.Entity

import org.fornever.base.annotations.BusinessObject
import org.fornever.base.model.BaseBusinessObject

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * An example entity
 * 
 * @author Theo Sun
 *
 */
@BusinessObject
@Entity
@AutoClone
@EqualsAndHashCode
@ToString
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
