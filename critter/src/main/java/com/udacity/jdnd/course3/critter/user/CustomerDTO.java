package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import lombok.*;

/**
 * Represents the form that customer request and response data takes. Does not
 * map to the database directly.
 */
@Setter
@Getter
@ToString
public class CustomerDTO {
	private Long id;
	private String name;
	private String phoneNumber;
	private String notes;
	private List<Long> petIds;
}
