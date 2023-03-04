package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.user.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	List<Employee> findAllByIdIn(List<Long> ids);

	List<Employee> findByDaysAvailable(DayOfWeek day);
}
