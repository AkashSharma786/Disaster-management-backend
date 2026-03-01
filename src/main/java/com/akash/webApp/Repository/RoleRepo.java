package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.akash.webApp.Model.users.Role;
import com.akash.webApp.Model.users.RoleEnum;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleEnum name);

    
}
