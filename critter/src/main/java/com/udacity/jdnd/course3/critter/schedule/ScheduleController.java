package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.utils.Mapper;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping
	public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

		if (scheduleDTO.getEmployeeIds() == null && scheduleDTO.getPetIds() == null) {
			throw new NotFoundException();
		}

		Schedule scheduleLists = scheduleService.getScheduleLists(scheduleDTO.getEmployeeIds(),
				scheduleDTO.getPetIds());
		Schedule schedule = Mapper.convertScheduleDTOToSchedule(scheduleDTO, scheduleLists.getEmployees(),
				scheduleLists.getPets());

		if (schedule.getEmployees().size() != scheduleDTO.getEmployeeIds().size()
				|| schedule.getPets().size() != scheduleDTO.getPetIds().size()) {
			throw new NotFoundException();
		}

		return Mapper.convertScheduleToScheduleDTO(scheduleService.createSchedule(schedule));
	}

	@GetMapping
	public List<ScheduleDTO> getAllSchedules() {
		return Mapper.convertScheduleToScheduleDTO(scheduleService.getAllSchedules());
	}

	@GetMapping("/pet/{petId}")
	public List<ScheduleDTO> getScheduleForPet(@PathVariable Long petId) {
		return Mapper.convertScheduleToScheduleDTO(scheduleService.getScheduleForPet(petId));
	}

	@GetMapping("/employee/{employeeId}")
	public List<ScheduleDTO> getScheduleForEmployee(@PathVariable Long employeeId) {
		return Mapper.convertScheduleToScheduleDTO(scheduleService.getScheduleForEmployee(employeeId));
	}

	@GetMapping("/customer/{customerId}")
	public List<ScheduleDTO> getScheduleForCustomer(@PathVariable Long customerId) {
		return Mapper.convertScheduleToScheduleDTO(scheduleService.getScheduleForCustomer(customerId));
	}
}
