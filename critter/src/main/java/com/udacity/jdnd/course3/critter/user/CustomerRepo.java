package com.udacity.jdnd.course3.critter.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.user.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	Optional<Customer> findByPetsId(Long petId);
}
