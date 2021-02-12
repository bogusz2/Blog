package boguszGroup.Blog.security.service;

import boguszGroup.Blog.security.model.UserDto;

import boguszGroup.Blog.security.model.User;
import boguszGroup.Blog.security.repository.RoleRepository;
import boguszGroup.Blog.security.repository.UserRepository;
//import boguszGroup.Blog.security.errors.UserAlreadyExistException
import com.microsoft.sqlserver.jdbc.SQLServerException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import sun.jvmstat.monitor.MonitoredVm;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public Optional<User> registerNewUserAccount(final UserDto accountDto) {
        return Optional.of(accountDto)
                .filter(this::isUniqueAccount)//todo throw new UserAlreadyExistException
                .map(this::userDtoMapToUser)
                .map(userRepository::save);
    }

    private boolean isUniqueAccount(final UserDto user) {
        return Boolean.logicalAnd(emailNotExists(user.getEmail()), usernameNotExists(user.getUsername()));
    }

    private boolean emailNotExists(final String email) {
        return userRepository.findByEmail(email) == null;
    }

    private boolean usernameNotExists(final String username) {
        return userRepository.findByUsername(username) == null;
    }

    private User userDtoMapToUser(UserDto userDto) {
        User user =  new User();
        user.setUsername(userDto.getUsername());
        user.setPassword((passwordEncoder.encode(userDto.getPassword())));
        user.setEmail(userDto.getEmail());
        return user;
    }
}
