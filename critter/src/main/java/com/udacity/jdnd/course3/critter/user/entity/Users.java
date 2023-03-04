package com.udacity.jdnd.course3.critter.user.entity;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import lombok.*;

@Setter
@Getter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "NVARCHAR(50)")
	@Nationalized
	private String name;

}
