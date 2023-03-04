package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);

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
		return (customer.isPresent()) ? Mapper.convertCustomerToCustomerDTO(customer.get()) : null;
	}

	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		employeeRepo.save(employee);

		return employeeDTO;
	}

	public EmployeeDTO getEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);

		if (employee.isEmpty())
			return null;

		EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(employee, dto);

		return dto;
	}

	public Object setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);

		if (employee.isEmpty())
			return null;

		employee.get().setDaysAvailable(daysAvailable);
		return employeeRepo.save(employee.get());
	}

	public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
		List<Employee> employees = findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());
		return Mapper.convertEmployeeToEmployeeDTO(employees);
	}

	private List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
		List<Employee> availableEmployees = new ArrayList<>();
		List<Employee> employees = this.employeeRepo.findEmployeesByDaysAvailable(date.getDayOfWeek());

		for (Employee employee : employees) {
			if (employee.getSkills().containsAll(skills)) {
				availableEmployees.add(employee);
			}
		}
		return availableEmployees;
	}
}
