package com.yogesh.example.SpringRest.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yogesh.example.SpringRest.bean.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

}
