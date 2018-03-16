package com.fpt.hr.recruitment.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fpt.hr.recruitment.persistence.dao.UserRepository;
import com.fpt.hr.recruitment.persistence.model.User;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String authentication) throws UsernameNotFoundException{
		String password = request.getParameter("j_password");
		CustomUserData customUserData = new CustomUserData();
		
		User user = userRepo.getUserByUsername(authentication);
		if(user != null){
			if(user.getPassword().equals(password)){
				customUserData.setAuthentication(true);
				customUserData.setPassword(user.getPassword());
				Collection<CustomRole> roles = new ArrayList<CustomRole>();
				CustomRole customRole = new CustomRole();
				customRole.setAuthority(user.getRole());
				roles.add(customRole);
				customUserData.setAuthorities(roles);
				return customUserData;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	private class CustomRole implements GrantedAuthority {
		String role = null;

		@Override
		public String getAuthority() {
			return role;
		}

		public void setAuthority(String roleName) {
			this.role = roleName;
		}

	}

}
