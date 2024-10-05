package com.ai.aiml10.entity;

import com.ai.aiml10.enums.Role;
import com.ai.aiml10.utils.PermissionRoleMapping;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Document(collection = "UserDetails")
public class UserEntity implements UserDetails {

    @Id
    private String id ;

    @Indexed(unique = true)
    private String email ;

    private String password ;

    private String name ;

    private Set<Role> roles ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role -> {
                    Set<SimpleGrantedAuthority> permissions = PermissionRoleMapping.getAuthorities(role);
                    authorities.addAll(permissions);
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
                }
                );
        return authorities ;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
