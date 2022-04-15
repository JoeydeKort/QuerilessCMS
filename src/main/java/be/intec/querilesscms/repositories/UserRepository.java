package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String userName);
    Optional<User> findByEmail(String email);

}
