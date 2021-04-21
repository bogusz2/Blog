package boguszGroup.Blog.security.repository;


import boguszGroup.Blog.security.model.Privilege;
import boguszGroup.Blog.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
