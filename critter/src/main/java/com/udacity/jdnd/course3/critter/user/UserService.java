package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.utils.Mapper;

@Service
public class UserService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	public CustomerDTO getCustomerById(Long customerId) {
		return Mapper
				.convertCustomerToCustomerDTO(customerRepo.findById(customerId).orElseThrow(NotFoundException::new));
	}

	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

		Customer customer = Mapper.convertCustomerDTOToCustomer(customerDTO);
		return Mapper.convertCustomerToCustomerDTO(customerRepo.save(customer));
	}

	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerRepo.findAll();
		return Mapper.convertCustomerToCustomerDTO(customers);
	}

	public CustomerDTO getOwnerByPet(Long petId) {
		Optional<Customer> customer = customerRepo.findByPetsId(petId);

		if (customer.isEmpty())
			throw new NotFoundException();

		return Mapper.convertCustomerToCustomerDTO(customer.get());
	}

	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee = Mapper.convertEmployeeDTOToEmployee(employeeDTO);
		return Mapper.convertEmployeeToEmployeeDTO(employeeRepo.save(employee));
	}

	public EmployeeDTO getEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);

		if (employee.isEmpty())
			throw new NotFoundException();

		return Mapper.convertEmployeeToEmployeeDTO(employee.get());
	}

	public boolean setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);

		if (employee.isEmpty())
			throw new NotFoundException();

		employee.get().setDaysAvailable(daysAvailable);
		employeeRepo.save(employee.get());

		return true;
	}

	public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
		List<Employee> employees = findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());
		return Mapper.convertEmployeeToEmployeeDTO(employees);
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

	public List<EmployeeDTO> findAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		return Mapper.convertEmployeeToEmployeeDTO(employees);
	}

}
