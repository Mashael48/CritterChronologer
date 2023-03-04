package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.utils.Mapper;

@Service
public class UserService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private PetService petService;

	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

		Customer customer = Mapper.convertCustomerDTOToCustomer(customerDTO);
		customer.setPets(petService.findByIds(customerDTO.getPetIds()));

		customerRepo.save(customer);
		return customerDTO;
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
		employeeRepo.save(employee);

		return employeeDTO;
	}

	public EmployeeDTO getEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);

		if (employee.isEmpty())
			throw new NotFoundException();

		return Mapper.convertEmployeeToEmployeeDTO(employee.get());
	}

	public Object setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);

		if (employee.isEmpty())
			throw new NotFoundException();

		employee.get().setDaysAvailable(daysAvailable);
		return employeeRepo.save(employee.get());
	}

	public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
		List<Employee> employees = findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());
		return Mapper.convertEmployeeToEmployeeDTO(employees);
	}

	private List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
		List<Employee> availableEmployees = new LinkedList<>();
		List<Employee> employees = this.employeeRepo.findEmployeesByDaysAvailable(date.getDayOfWeek());

		for (Employee employee : employees) {
			if (employee.getSkills().containsAll(skills)) {
				availableEmployees.add(employee);
			}
		}
		return availableEmployees;
	}
}
