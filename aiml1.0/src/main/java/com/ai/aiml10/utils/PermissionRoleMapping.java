package com.ai.aiml10.utils;

import com.ai.aiml10.enums.Permissions;
import com.ai.aiml10.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ai.aiml10.enums.Permissions.*;
import static com.ai.aiml10.enums.Role.*;

public class PermissionRoleMapping {

    private final static Map<Role , Set<Permissions>> roleSetMap = Map.of(

            OPERATOR , Set.of(ADD_TEST_REPORTS),
            ANALYST , Set.of(VIEW_TEST_REPORTS , VIEW_BIOLOGICAL_PASSPORT , VIEW_ATHLETES ) ,
            INVESTIGATOR , Set.of(VIEW_INVESTIGATORS , VIEW_TEST_REPORTS , VIEW_BIOLOGICAL_PASSPORT , VIEW_ATHLETES ) ,
            ADMIN , Set.of(VIEW_INVESTIGATORS , VIEW_TEST_REPORTS , VIEW_BIOLOGICAL_PASSPORT , VIEW_ATHLETES ,ADD_TEST_REPORTS , ADD_ATHLETES)

    );

    public static Set<SimpleGrantedAuthority> getAuthorities(Role role){
        return roleSetMap.get(role)
                .stream().map(
                        permissions -> new SimpleGrantedAuthority(permissions.name())
                ).collect(Collectors.toSet());
    }
}
