package com.udacity.jdnd.course3.critter.user.entity;

import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.*;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("1")
public class Employee extends User {

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

}
