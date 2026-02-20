package com.akash.webApp.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class StateOrUt {
        @Id
        private Integer id;
        @Column(nullable = false)
        private String stateOrUtName; 

        public StateOrUt(){
                
        }
        public StateOrUt(String stateOrUtName, Integer id) {
                this.stateOrUtName = stateOrUtName;
                this.id = id;

        }

        public Integer getId() {
                return this.id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public void setStateOrUtName(String stateOrUtName) {
                this.stateOrUtName = stateOrUtName;
        }

        public String getStateOrUtName() {
                return stateOrUtName;
        }

}
