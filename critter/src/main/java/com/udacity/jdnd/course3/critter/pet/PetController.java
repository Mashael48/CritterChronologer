package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.utils.Mapper;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetService petService;

	@PostMapping
	public PetDTO savePet(@RequestBody PetDTO petDTO) {

		if (petDTO.getOwnerId() == null) { // Pets customer_id not nullable, thus null not accepted
			throw new NotFoundException();
		}

		Pet pet = Mapper.convertPetDTOToPet(petDTO);
		return Mapper.convertPetToPetDTO(petService.savePet(pet, petDTO.getOwnerId()));
	}

	@GetMapping("/{petId}")
	public PetDTO getPet(@PathVariable Long petId) {
		return Mapper.convertPetToPetDTO(petService.getPet(petId));
	}

	@GetMapping
	public List<PetDTO> getPets() {
		return Mapper.convertPetToPetDTO(petService.getPets());
	}

	@GetMapping("/owner/{ownerId}")
	public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
		return Mapper.convertPetToPetDTO(petService.getPetsByOwner(ownerId));
	}
}
