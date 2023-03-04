package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.UserService;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.utils.Mapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetService {

	@Autowired
	private PetRepo petRepo;

	@Autowired
	private UserService userService;

	public List<Pet> findByIds(List<Long> petsIds) {
		return petRepo.findAllById(petsIds);
	}

	public PetDTO savePet(PetDTO petDTO) {

		Customer customer = null;
		if (petDTO.getOwnerId() != null && petDTO.getOwnerId() != 0) {

			try {
				CustomerDTO customerDTO = this.userService.getOwnerByPet(petDTO.getOwnerId());
				customer = Mapper.convertCustomerDTOToCustomer(customerDTO);
			} catch (@SuppressWarnings("unused") Exception e) {
				log.warn("Customer id has not been provided");
			}
		}

		Pet pet = Mapper.convertPetDTOToPet(petDTO);
		pet.setCustomer(customer);

		return petDTO;
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
		List<Pet> pets = petRepo.findPetsByCustomerId(ownerId);
		return Mapper.convertPetToPetDTO(pets);
	}

}
