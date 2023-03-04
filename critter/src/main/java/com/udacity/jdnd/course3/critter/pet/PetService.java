package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.utils.Mapper;

@Service
public class PetService {

	@Autowired
	private PetRepo petRepo;

	@Autowired
	private CustomerRepo customerRepo;

	public PetDTO savePet(PetDTO petDTO) {

		if (petDTO.getOwnerId() == null) { // Pets customer_id not nullable, thus null not accepted
			throw new NotFoundException();
		}

		Optional<Customer> customer = customerRepo.findById(petDTO.getOwnerId());

		if (customer.isEmpty())
			throw new NotFoundException();

		Pet pet = Mapper.convertPetDTOToPet(petDTO);
		pet.setCustomer(customer.get());

		return Mapper.convertPetToPetDTO(petRepo.save(pet));
	}

	public PetDTO getPet(Long petId) {
		Optional<Pet> pet = petRepo.findById(petId);

		if (pet.isEmpty())
			throw new NotFoundException();

		return Mapper.convertPetToPetDTO(pet.get());
	}

	public List<PetDTO> getPets() {
		List<Pet> pets = petRepo.findAll();
		return Mapper.convertPetToPetDTO(pets);
	}

	public List<PetDTO> getPetsByOwner(Long ownerId) {
		List<Pet> pets = petRepo.findByCustomerId(ownerId);
		return Mapper.convertPetToPetDTO(pets);
	}

}
