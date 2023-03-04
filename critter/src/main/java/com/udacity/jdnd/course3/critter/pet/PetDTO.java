package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;

import lombok.*;

/**
 * Represents the form that pet request and response data takes. Does not map to
 * the database directly.
 */
@Setter
@Getter
@ToString
public class PetDTO {
	private Long id;
	private PetType type;
	private String name;
	private Long ownerId;
	private LocalDate birthDate;
	private String notes;
}
