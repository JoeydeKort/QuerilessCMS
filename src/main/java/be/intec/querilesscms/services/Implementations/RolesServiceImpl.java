package be.intec.querilesscms.services.Implementations;

import be.intec.querilesscms.repositories.RoleRepository;
import be.intec.querilesscms.services.interfaces.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    private RoleRepository roleRepository;

    @Autowired
    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
