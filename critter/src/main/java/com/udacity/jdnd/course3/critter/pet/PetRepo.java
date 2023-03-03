package com.udacity.jdnd.course3.critter.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {

}
