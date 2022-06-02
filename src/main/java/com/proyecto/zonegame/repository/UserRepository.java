package com.proyecto.zonegame.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.zonegame.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
