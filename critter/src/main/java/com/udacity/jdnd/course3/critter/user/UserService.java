package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;

@Service
@Transactional
public class UserService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	public Customer getCustomerById(Long customerId) {
		return customerRepo.findById(customerId).orElseThrow(NotFoundException::new);
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getOwnerByPet(Long petId) {
		Optional<Customer> customer = customerRepo.findByPetsId(petId);

		if (customer.isEmpty())
			throw new NotFoundException();

		return customer.get();
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	public Employee getEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);

		if (employee.isEmpty())
			throw new NotFoundException();

		return employee.get();
	}

	public boolean setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);

		if (employee.isEmpty())
			throw new NotFoundException();

		employee.get().setDaysAvailable(daysAvailable);
		employeeRepo.save(employee.get());

		return true;
	}

	public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
		return findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());
	}

	private List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
		List<Employee> availableEmployees = new LinkedList<>();
		List<Employee> employees = employeeRepo.findByDaysAvailable(date.getDayOfWeek());

		for (Employee employee : employees) {
			if (employee.getSkills().containsAll(skills)) {
				availableEmployees.add(employee);
			}
		}
		return availableEmployees;
	}

	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}

}
