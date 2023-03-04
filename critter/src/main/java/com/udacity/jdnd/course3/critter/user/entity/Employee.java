package com.udacity.jdnd.course3.critter.user.entity;

import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.*;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.*;

@Entity
@Setter
@Getter
@ToString
@DiscriminatorValue("1")
public class Employee extends Users {

	@ElementCollection
//    @CollectionTable(name="skills")
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private Set<EmployeeSkill> skills;

	@ElementCollection
//    @CollectionTable(name="days_available")
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private Set<DayOfWeek> daysAvailable;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employee_schedule_id", nullable = true)
//	private Schedule schedule;

}
