package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Set;

import lombok.Data;

/**
 * Represents the form that employee request and response data takes. Does not
 * map to the database directly.
 */
@Data
public class EmployeeDTO {
	private Long id;
	private String name;
	private Set<EmployeeSkill> skills;
	private Set<DayOfWeek> daysAvailable;
}
