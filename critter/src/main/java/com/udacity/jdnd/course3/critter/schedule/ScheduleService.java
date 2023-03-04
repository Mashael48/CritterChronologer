package com.udacity.jdnd.course3.critter.schedule;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeRepo;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;

@Service
@Transactional
public class ScheduleService {

	@Autowired
	private ScheduleRepo scheduleRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private PetRepo petRepo;

	public Schedule getScheduleLists(List<Long> employeesIds, List<Long> petsIds) {

		List<Employee> employees = employeeRepo.findAllByIdIn(employeesIds);
		List<Pet> pets = petRepo.findAllByIdIn(petsIds);

		Schedule schedule = new Schedule();
		schedule.setEmployees(employees);
		schedule.setPets(pets);

		return schedule;
	}

	public Schedule createSchedule(Schedule schedule) {
		return scheduleRepo.save(schedule);
	}

	public List<Schedule> getAllSchedules() {
		return scheduleRepo.findAll();
	}

	public List<Schedule> getScheduleForPet(Long petId) {
		return scheduleRepo.getByPetsId(petId);
	}

	public List<Schedule> getScheduleForEmployee(Long employeeId) {
		return scheduleRepo.getByEmployeesId(employeeId);
	}

	public List<Schedule> getScheduleForCustomer(Long customerId) {
		Optional<Customer> customer = customerRepo.findById(customerId);

		if (customer.isEmpty()) {
			throw new NotFoundException();
		}

		List<Pet> pets = customer.get().getPets();
		List<Schedule> schedules = new LinkedList<>();

		for (Pet pet : pets) {
			schedules.addAll(scheduleRepo.getByPetsId(pet.getId()));
		}

		return schedules;
	}

}
