package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Set;

import lombok.*;

/**
 * Represents the form that employee request and response data takes. Does not
 * map to the database directly.
 */
@Setter
@Getter
@ToString
public class EmployeeDTO {
	private Long id;
	private String name;
	private Set<EmployeeSkill> skills;
	private Set<DayOfWeek> daysAvailable;
}
