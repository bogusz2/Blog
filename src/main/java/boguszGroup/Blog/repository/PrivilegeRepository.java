package boguszGroup.Blog.repository;


import boguszGroup.Blog.model.Privilege;
import boguszGroup.Blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

    Collection<Privilege> findAllByRolesIn(Collection<Role> roles);

}
