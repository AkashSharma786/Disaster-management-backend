package com.akash.webApp.Model;

import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{

    
    private UsersModel user;
    public UserPrincipal(UsersModel user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){

       return List.of(new SimpleGrantedAuthority("ROLE_"+ user.getRole().getName().toString()));
       
    };

  public String getPassword(){
    return user.getPassword();

   };

   public String getUsername(){
    return ( user.getEmail() != null)? user.getEmail() : ""+user.getPhoneNumber();

   };

   public boolean isAccountNonExpired() {
      return true;
   }

   public boolean isAccountNonLocked() {
      return true;
   }

   public boolean isCredentialsNonExpired() {
      return true;
   }

   public boolean isEnabled() {
      return true;
   }

    
}
