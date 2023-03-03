package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.user.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}