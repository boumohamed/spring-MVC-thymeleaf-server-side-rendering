package bouzri.me.springmvc.security.service;

import bouzri.me.springmvc.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDeatilsServiceImpl implements UserDetailsService {


    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = securityService.loadUserByUsername(username);
        /*
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getAppRoles().forEach(r -> {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(r.getRole());
            authorities.add(authority);
        } );
         */
        Collection<GrantedAuthority> authorities = appUser.getAppRoles()
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
        User user = new User(appUser.getUsername(), appUser.getPassword(), authorities);
        return user;
    }
}
