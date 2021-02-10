package boguszGroup.Blog.security.service;

import boguszGroup.Blog.security.model.UserDto;

import boguszGroup.Blog.security.model.User;
import boguszGroup.Blog.security.repository.RoleRepository;
import boguszGroup.Blog.security.repository.UserRepository;
//import boguszGroup.Blog.security.errors.UserAlreadyExistException
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import sun.jvmstat.monitor.MonitoredVm;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            LOGGER.info("There is an account with that email address: " + accountDto.getEmail());
            //todo throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }

        final User user = new User();

        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
//        user.setUsing2FA(accountDto.isUsing2FA());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
