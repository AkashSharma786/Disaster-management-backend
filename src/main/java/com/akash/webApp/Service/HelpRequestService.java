package com.akash.webApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.webApp.Model.users.HelpRequest;
import com.akash.webApp.Model.users.RoleEnum;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Repository.HelpRequestRepo;
import com.akash.webApp.Repository.UsersRepo;

@Service
public class HelpRequestService {
    @Autowired
    HelpRequestRepo helpRequestRepo;
    @Autowired
    UsersRepo usersRepo;


    public List<HelpRequest> getRequestByUser(Integer userId){
        
        Optional<UsersModel> user = usersRepo.findById(userId);
        if(user.isEmpty()  && user.get().getRole().getName() == RoleEnum.RESIDENT)
            return new ArrayList<HelpRequest>();
        return helpRequestRepo.findByUser(user.get());

    }

    public List<HelpRequest> getAllRequests(){
        return helpRequestRepo.findAll();
    }

    public void addRequests(Integer userId, String message){
        Optional<UsersModel> user = usersRepo.findById(userId);
        if(user.isEmpty())
            return;

        helpRequestRepo.save(new HelpRequest(user.get(), message));

    }

    public void deleteRequest(Integer requestId){
        Optional<HelpRequest> request = helpRequestRepo.findById(requestId);
        if(request.isEmpty())
            return;

        helpRequestRepo.delete(request.get());
    }
    
}
