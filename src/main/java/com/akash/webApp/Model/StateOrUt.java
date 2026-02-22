package com.akash.webApp.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class StateorUt {
        @Id
        private Integer id;
        @Column(nullable = false)
        private String stateorUtName; 

        public StateorUt(){
                
        }
        public StateorUt(String stateorUtName, Integer id) {
                this.stateorUtName = stateorUtName;
                this.id = id;

        }

        public Integer getId() {
                return this.id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public void setStateorUtName(String stateorUtName) {
                this.stateorUtName = stateorUtName;
        }

        public String getStateorUtName() {
                return stateorUtName;
        }

}
