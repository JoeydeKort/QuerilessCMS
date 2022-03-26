package be.intec.querilesscms.services;

import be.intec.querilesscms.repositories.RolesRepository;
import be.intec.querilesscms.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;

    @Autowired
    public UserService(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

}
