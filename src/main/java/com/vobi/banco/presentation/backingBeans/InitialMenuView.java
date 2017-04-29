package com.vobi.banco.presentation.backingBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@ManagedBean
@ViewScoped
public class InitialMenuView implements Serializable {

	private static final long serialVersionUID = 2985639918423438706L;
	
	private String usuario;
	
	public String getUsuario() {
		 this.usuario=getPrincipal();
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	private String getPrincipal(){
	        String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();
	            UserDetails userDetails=(UserDetails)principal;
	            for (GrantedAuthority auth : userDetails.getAuthorities()) {
	                System.out.println(auth.getAuthority());
	            }
	        } else {
	            userName = principal.toString();
	        }
	        return userName;
	   }
	

}
