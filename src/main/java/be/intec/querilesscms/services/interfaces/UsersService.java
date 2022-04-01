package be.intec.querilesscms.services.interfaces;

import be.intec.querilesscms.models.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<Users> findAllUsers();

    void saveUser(Users users);

    Optional<Users> findUserById(Long id);

    void deleteById(Long id);
}
