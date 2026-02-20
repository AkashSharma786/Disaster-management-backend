package com.akash.webApp.Model;

import java.io.Serializable;

import org.hibernate.mapping.Join;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class District{
        
        @Id
        @Column(name = "lgd_code")
        private Integer lgdCode;

        @Column(nullable = false)
        private String name;

       

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(  referencedColumnName = "id", nullable = false)
        private StateOrUt stateOrUt;

        public District(){}
        public District(Integer lgdCode){
                this.lgdCode = lgdCode;
        }
        public District(String name, Integer lgdCode, StateOrUt stateOrUt) {
                this.name = name;
                this.lgdCode = lgdCode;
                this.stateOrUt = stateOrUt;
        }

        public void setStateOrUt(StateOrUt stateOrUt) {
                this.stateOrUt = stateOrUt;
        }

       

        public StateOrUt getStateOrUt() {
                return this.stateOrUt;
        }

       

        
        public Integer getLgdCode() {
                return lgdCode;
        }

        public void setLgdCode(Integer lgdCode) {
                this.lgdCode = lgdCode;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

}

