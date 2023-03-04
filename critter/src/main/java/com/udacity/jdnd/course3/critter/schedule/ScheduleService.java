package com.udacity.jdnd.course3.critter.schedule;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.utils.Mapper;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepo scheduleRepo;

	@Autowired
	private CustomerRepo customerRepo;

	public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
		Schedule schedule = Mapper.convertScheduleDTOToSchedule(scheduleDTO);
		scheduleRepo.save(schedule);

		return scheduleDTO;
	}

	public List<ScheduleDTO> getAllSchedules() {
		List<Schedule> schedules = scheduleRepo.findAll();
		return Mapper.convertScheduleToScheduleDTO(schedules);
	}

	public List<ScheduleDTO> getScheduleForPet(Long petId) {
		List<Schedule> schedules = scheduleRepo.getScheduleByPetsId(petId);
		return Mapper.convertScheduleToScheduleDTO(schedules);
	}

	public List<ScheduleDTO> getScheduleForEmployee(Long employeeId) {
		List<Schedule> schedules = scheduleRepo.getScheduleByEmployeesId(employeeId);
		return Mapper.convertScheduleToScheduleDTO(schedules);
	}

	public List<ScheduleDTO> getScheduleForCustomer(Long customerId) {
		Optional<Customer> optionalCustomer = customerRepo.findById(customerId);

		if (optionalCustomer.isEmpty()) {
			throw new NotFoundException();
		}

		Customer customer = optionalCustomer.get();
		List<Pet> pets = customer.getPets();
		List<Schedule> schedules = new ArrayList<>();

		for (Pet pet : pets) {
			schedules.addAll(scheduleRepo.getScheduleByPetsId(pet.getId()));
		}

		return Mapper.convertScheduleToScheduleDTO(schedules);
	}

}
