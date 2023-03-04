package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.utils.Mapper;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into
 * separate user and customer controllers would be fine too, though that is not
 * part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/customer/{customerId}")
	public CustomerDTO getCustomerById(@PathVariable Long customerId) {
		return Mapper.convertCustomerToCustomerDTO(userService.getCustomerById(customerId));
	}

	@Autowired
	private UserService userService;

	@PostMapping("/customer")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		Customer customer = Mapper.convertCustomerDTOToCustomer(customerDTO);
		return Mapper.convertCustomerToCustomerDTO(userService.saveCustomer(customer));
	}

	@GetMapping("/customer")
	public List<CustomerDTO> getAllCustomers() {
		return Mapper.convertCustomerToCustomerDTO(userService.getAllCustomers());
	}

	@GetMapping("/customer/pet/{petId}")
	public CustomerDTO getOwnerByPet(@PathVariable Long petId) {
		return Mapper.convertCustomerToCustomerDTO(userService.getOwnerByPet(petId));
	}

	@PostMapping("/employee")
	public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = Mapper.convertEmployeeDTOToEmployee(employeeDTO);
		return Mapper.convertEmployeeToEmployeeDTO(userService.saveEmployee(employee));
	}

	@PostMapping("/employee/{employeeId}")
	public EmployeeDTO getEmployee(@PathVariable Long employeeId) {
		return Mapper.convertEmployeeToEmployeeDTO(userService.getEmployee(employeeId));
	}

	@PutMapping("/employee/{employeeId}")
	public boolean setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable Long employeeId) {
		return userService.setAvailability(daysAvailable, employeeId);
	}

	@GetMapping("/employee/availability")
	public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
		return Mapper.convertEmployeeToEmployeeDTO(userService.findEmployeesForService(employeeDTO));
	}

	@GetMapping("/employee")
	public List<EmployeeDTO> findEmployees() {
		return Mapper.convertEmployeeToEmployeeDTO(userService.findAllEmployees());
	}

}
