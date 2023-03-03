package com.udacity.jdnd.course3.critter.user.entity;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", 
discriminatorType = DiscriminatorType.INTEGER)
public abstract class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Nationalized
	private String name;

}
