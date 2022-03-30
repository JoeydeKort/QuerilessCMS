package be.intec.querilesscms.services.Implementations;

import be.intec.querilesscms.repositories.RolesRepository;
import be.intec.querilesscms.services.interfaces.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    private RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

}
