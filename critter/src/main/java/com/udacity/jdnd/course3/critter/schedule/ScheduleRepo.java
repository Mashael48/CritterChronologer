package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

}
