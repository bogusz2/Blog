package boguszGroup.Blog.service;


import boguszGroup.Blog.model.Role;
import boguszGroup.Blog.repository.PrivilegeRepository;
import boguszGroup.Blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        boguszGroup.Blog.model.User user = userRepository.findByUsername(username);
        return new User(user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return privilegeRepository.findAllByRolesIn(roles)
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toList());
    }
}
