package com.udacity.jdnd.course3.critter.pet.entity;

import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.user.entity.Customer;

import lombok.Data;

@Entity
@Data
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Nationalized
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PetType type;
//
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "schedule_id")
//	private Schedule schedule;

	@Column(nullable = true)
	private Date dateOfBirth;

	@Column(nullable = true)
	private String notes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

}