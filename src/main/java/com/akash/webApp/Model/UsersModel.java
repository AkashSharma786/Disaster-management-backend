package com.akash.webApp.Model;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.UniqueConstraint;


@Entity
public class UsersModel {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    @Column(nullable = false)
    private int id;

    private String firstName;
    private String lastName;

    @Column(unique =  true)
    private String email;
 
    
   

   
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "lgd_code", nullable = false)
    private District district;


    private String password;
    @Column(unique =  true)
    private Long phoneNumber;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public UsersModel(String firstName, String lastName, String email, District district,
            String password, Long phoneNumber, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.district = district;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UsersModel() {
    }

    public UsersModel(Long phoneNumber, String email, String password) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }



    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
         this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
         this.lastName = lastName;
    }
    


    

    public Long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
