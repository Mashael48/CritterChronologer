package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.entity.Customer;

@Service
@Transactional
public class PetService {

	@Autowired
	private PetRepo petRepo;

	@Autowired
	private CustomerRepo customerRepo;

	public Pet savePet(Pet pet, Long ownerId) {

		Optional<Customer> customer = customerRepo.findById(ownerId);

		if (customer.isEmpty())
			throw new NotFoundException();

		pet.setCustomer(customer.get());
		return petRepo.save(pet);
	}

	public Pet getPet(Long petId) {
		Optional<Pet> pet = petRepo.findById(petId);

		if (pet.isEmpty())
			throw new NotFoundException();

		return pet.get();
	}

	public List<Pet> getPets() {
		return petRepo.findAll();
	}

	public List<Pet> getPetsByOwner(Long ownerId) {
		return petRepo.findByCustomerId(ownerId);
	}

}
