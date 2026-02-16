package com.akash.webApp.Model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)

    private RoleEnum name;

    @Column(nullable = false)
    private String description;

    public Role(){}

    public Role(RoleEnum role, String description){
        this.name = role;
        this.description = description;
    }


    public RoleEnum getName(){
        return name;
    }

    public void setName(RoleEnum name){
        this.name = name;
    }

     public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

     public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }


   



    
}
