package be.intec.querilesscms.services.interfaces;

import be.intec.querilesscms.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<User> findAllUsers();

    void saveUser(User user);

    Optional<User> findUserById(Long id);

    User findByUserName(String userName);

    void deleteById(Long id);
}
