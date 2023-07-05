package com.mani.springbootbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mani.springbootbackend.enitity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
