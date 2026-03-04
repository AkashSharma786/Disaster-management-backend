package com.akash.webApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.akash.webApp.Model.location.District;
import com.akash.webApp.Model.users.Role;
import com.akash.webApp.Model.users.RoleEnum;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Repository.DistrictRepo;
import com.akash.webApp.Repository.RoleRepo;
import com.akash.webApp.Repository.UsersRepo;

import reactor.core.publisher.Flux;

@Component
public class RegistrationService {

    public BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12) ;

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private DistrictRepo districtRepo;

    public String registerUser(UsersModel user) {
        // Here you would typically save the user details to a database
        // For simplicity, we are just printing the details and returning a success message
        if(user.getFirstName() == null 
            || user.getLastName() == null
            || user.getEmail() == null 
            || user.getPassword() == null 
            || user.getDistrict() == null
            || user.getPhoneNumber() == null 
            || user.getRole() == null    ) {
            return "Invalid registration data";
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

       

        usersRepo.save(user);

        System.out.println("Registering user: " + user.getFirstName() + " "+ user.getLastName() + ", " + user.getEmail());
        return "User registered successfully";
    }

    public List<UsersModel> getAllUsers() {
        return usersRepo.findAll();
    }

    public List<UsersModel> getResponders() {
        Role role = roleRepo.findByName(RoleEnum.RESPONDENT).get();
        return usersRepo.findByRole(role);
        
    }

    public List<District> getDistricts() {
        return districtRepo.findAll();
    }

}
