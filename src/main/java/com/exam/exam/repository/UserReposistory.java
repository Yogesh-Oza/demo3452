package com.exam.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam.model.User;

public interface UserReposistory  extends JpaRepository<User, Long>{

}
