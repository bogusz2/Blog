package boguszGroup.Blog.service;

import boguszGroup.Blog.model.Privilege;
import boguszGroup.Blog.model.Role;
import boguszGroup.Blog.model.User;
import boguszGroup.Blog.repository.PrivilegeRepository;
import boguszGroup.Blog.repository.RoleRepository;
import boguszGroup.Blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {//TODO przepisać hardcody do properties

    boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPublicPostsPrivilege
                = createPrivilegeIfNotFound("READ_PUBLIC_POSTS_PRIVILEGE");
        Privilege readPrivatePostsPrivilege
                = createPrivilegeIfNotFound("READ_PRIVATE_POSTS_PRIVILEGE");
        Privilege createPublicPostsPrivilege
                = createPrivilegeIfNotFound("CREATE_PUBLIC_POSTS_PRIVILEGE");
        Privilege createPrivatePostsPrivilege
                = createPrivilegeIfNotFound("CREATE_PRIVATE_POSTS_PRIVILEGE");
        Privilege readUsersInfoPrivilege
                = createPrivilegeIfNotFound("READ_USERS_INFO");
        Privilege enableUsersPrivilege
                = createPrivilegeIfNotFound("ENABLE_USERS");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPublicPostsPrivilege,
                readPrivatePostsPrivilege,
                createPublicPostsPrivilege,
                createPrivatePostsPrivilege,
                readUsersInfoPrivilege,
                enableUsersPrivilege
                );

        List<Privilege> userDefaultPrivileges = Arrays.asList(
                readPublicPostsPrivilege,
                createPrivatePostsPrivilege,
                createPublicPostsPrivilege
        );
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userDefaultPrivileges);

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setUsername("ADMIN");
        user.setPassword(passwordEncoder.encode("ADMIN"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
