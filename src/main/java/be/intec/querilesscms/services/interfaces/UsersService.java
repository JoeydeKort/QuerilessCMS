package be.intec.querilesscms.services.interfaces;

import be.intec.querilesscms.models.Users;

import java.util.List;

public interface UsersService {

    List<Users> findAll();

    void save(Users users);

}
