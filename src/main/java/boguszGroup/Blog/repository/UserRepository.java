package boguszGroup.Blog.repository;

import boguszGroup.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    @Modifying
//    @Query("UPDATE Material m SET m.inventory_count = ?2 WHERE m.id = ?1")
    @Query("update User u set u.enabled=true where u.id = ?1")
    void updateUser( Long id);
}
