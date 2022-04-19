package be.intec.querilesscms.services.interfaces;

import be.intec.querilesscms.models.Role;
import be.intec.querilesscms.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<User> findAllUsers();

    void saveUser(User user);

    Optional<User> findUserById(Long id);

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    void deleteById(Long id);

    void addRole(User user, Role role);

    void removeRole(User user, Role role);

}
