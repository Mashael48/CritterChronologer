package com.udacity.jdnd.course3.critter.user;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;

/**
 * Represents a request to find available employees by skills. Does not map to
 * the database directly.
 */
@Setter
@Getter
@ToString
public class EmployeeRequestDTO {
	private Set<EmployeeSkill> skills;
	private LocalDate date;
}
