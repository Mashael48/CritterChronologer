package com.udacity.jdnd.course3.critter.utils;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;

public class Mapper {
	
	public static Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);

		return customer;
	}

	public static CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		List<Pet> pets = customer.getPets();
		List<Long> petIds = new LinkedList<>();

		if (pets != null) {
			petIds = pets.stream().map(Pet::getId).toList();
		}

		customerDTO.setPetIds(petIds);
		return customerDTO;
	}

	public static List<CustomerDTO> convertCustomerToCustomerDTO(List<Customer> customers) {
		return  customers.stream()
				.map(Mapper::convertCustomerToCustomerDTO)
				.toList();
	}
	

	public static Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);

		return employee;
	}
	
	public static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);
		return employeeDTO;
	}

	public static List<EmployeeDTO> convertEmployeeToEmployeeDTO(List<Employee> employees) {
		return employees.stream()
				.map(Mapper::convertEmployeeToEmployeeDTO)
				.toList();
	}
	
	public static Pet convertPetDTOToPet(PetDTO petDTO) {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDTO, pet);

		return pet;
	}

	public static PetDTO convertPetToPetDTO(Pet pet) {
		PetDTO petDTO = new PetDTO();
		BeanUtils.copyProperties(pet, petDTO);

		return petDTO;
	}
	
	public static List<PetDTO> convertPetToPetDTO(List<Pet> pet) {
		return pet.stream()
				.map(Mapper::convertPetToPetDTO)
				.toList();
	}
}
