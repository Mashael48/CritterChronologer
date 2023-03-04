package com.udacity.jdnd.course3.critter.schedule.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entity.Employee;

import lombok.*;

@Entity
@Setter
@Getter
@ToString
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(targetEntity = Employee.class)
	private List<Employee> employees;

	@ManyToMany(targetEntity = Pet.class)
	private List<Pet> pets;

	private LocalDate date;

	@ElementCollection(targetClass = EmployeeSkill.class)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Set<EmployeeSkill> activities;

}
