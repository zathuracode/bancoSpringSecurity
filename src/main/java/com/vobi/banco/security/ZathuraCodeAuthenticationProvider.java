package com.vobi.banco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;

import com.vobi.banco.modelo.Usuarios;
import com.vobi.banco.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.banco.utilities.FacesUtils;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Component("zathuraCodeAuthenticationProvider")
public class ZathuraCodeAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private IBusinessDelegatorView businessDelegatorView;
	
	
    /**
     * Security Implementation
     */
    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        try {
			Usuarios usuarios=businessDelegatorView.validarUsuario(name, password);
			
			FacesUtils.putinSession("usuario",usuarios);
			
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_"+usuarios.getTiposUsuarios().getTusuNombre().trim()));
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
            //

            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);

            return auth;
	       
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
