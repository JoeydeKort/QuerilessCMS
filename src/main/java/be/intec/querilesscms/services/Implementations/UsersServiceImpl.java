package be.intec.querilesscms.services.Implementations;

import be.intec.querilesscms.models.Users;
import be.intec.querilesscms.repositories.UsersRepository;
import be.intec.querilesscms.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {


    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public void save(Users users) {
        usersRepository.save(users);

    }

}
