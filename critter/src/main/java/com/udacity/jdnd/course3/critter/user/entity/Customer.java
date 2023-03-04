package com.udacity.jdnd.course3.critter.user.entity;

import java.util.List;

import javax.persistence.*;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("2")
public class Customer extends Users {

	@Column(nullable = true) // to allow adding employee without it
	private String phoneNumber;

	@Column(nullable = true)
	private String notes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, targetEntity = Pet.class)
	private List<Pet> pets;

}
