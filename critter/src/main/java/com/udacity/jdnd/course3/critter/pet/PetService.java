package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;

@Service
public class PetService {

	@Autowired
	private PetRepo petRepo;

	public List<Pet> findByIds(List<Long> petsIds) {
		return petRepo.findAllById(petsIds);
	}

}
