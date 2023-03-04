package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;

public interface PetRepo extends JpaRepository<Pet, Long> {

	List<Pet> findAllByIdIn(List<Long> ids);

	List<Pet> findByCustomerId(Long customer);
}
