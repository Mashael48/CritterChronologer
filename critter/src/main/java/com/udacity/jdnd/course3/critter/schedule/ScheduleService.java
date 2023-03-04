package com.udacity.jdnd.course3.critter.schedule;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeRepo;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.utils.Mapper;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepo scheduleRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private PetRepo petRepo;

	public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {

		if (scheduleDTO.getEmployeeIds() == null && scheduleDTO.getPetIds() == null) {
			throw new NotFoundException();
		}

		List<Employee> employees = employeeRepo.findAllByIdIn(scheduleDTO.getEmployeeIds());
		List<Pet> pets = petRepo.findAllByIdIn(scheduleDTO.getPetIds());

		if (employees.size() != scheduleDTO.getEmployeeIds().size() || pets.size() != scheduleDTO.getPetIds().size()) {
			throw new NotFoundException();
		}

		Schedule schedule = Mapper.convertScheduleDTOToSchedule(scheduleDTO, employees, pets);
		return Mapper.convertScheduleToScheduleDTO(scheduleRepo.save(schedule));
	}

	public List<ScheduleDTO> getAllSchedules() {
		List<Schedule> schedules = scheduleRepo.findAll();
		return Mapper.convertScheduleToScheduleDTO(schedules);
	}

	public List<ScheduleDTO> getScheduleForPet(Long petId) {
		List<Schedule> schedules = scheduleRepo.getByPetsId(petId);
		return Mapper.convertScheduleToScheduleDTO(schedules);
	}

	public List<ScheduleDTO> getScheduleForEmployee(Long employeeId) {
		List<Schedule> schedules = scheduleRepo.getByEmployeesId(employeeId);
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
			schedules.addAll(scheduleRepo.getByPetsId(pet.getId()));
		}

		return Mapper.convertScheduleToScheduleDTO(schedules);
	}

}
