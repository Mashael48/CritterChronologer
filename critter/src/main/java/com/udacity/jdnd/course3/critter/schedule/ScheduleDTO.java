package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.*;

/**
 * Represents the form that schedule request and response data takes. Does not
 * map to the database directly.
 */
@Setter
@Getter
@ToString
public class ScheduleDTO {
	private Long id;
	private List<Long> employeeIds;
	private List<Long> petIds;
	private LocalDate date;
	private Set<EmployeeSkill> activities;
}
