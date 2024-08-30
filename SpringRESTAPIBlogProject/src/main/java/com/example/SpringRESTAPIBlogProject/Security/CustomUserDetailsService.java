package com.example.SpringRESTAPIBlogProject.Security;

import com.example.SpringRESTAPIBlogProject.Entity.User;
import com.example.SpringRESTAPIBlogProject.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail( userNameOrEmail,  userNameOrEmail).orElseThrow(() ->new UsernameNotFoundException("user not found with userOremail"+ userNameOrEmail));

        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),grantedAuthorities);

    }
}
