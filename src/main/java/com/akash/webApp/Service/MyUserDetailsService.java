package com.akash.webApp.Service;


import com.akash.webApp.Model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.akash.webApp.Model.UsersModel;
import com.akash.webApp.Repository.UsersRepo;


@Service
public class MyUserDetailsService implements UserDetailsService {

   
    UsersModel user;

    @Autowired
    UsersRepo usersRepo;
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{


        if(username.contains("@"))
        {
            user = usersRepo.findByEmail(username);
        }
        else
        {
            String digits = username.replaceAll("\\D", "");

            if(!digits.isEmpty()){
                Long phoneNumber = Long.parseLong(digits);
                user = usersRepo.findByPhoneNumber(phoneNumber);
            }

        }

        System.out.println(user);

        if(user == null)
        {
            throw new UsernameNotFoundException(" Username not found" + username);
        }
        

       
        return new UserPrincipal(user);



        
    }
    
}
