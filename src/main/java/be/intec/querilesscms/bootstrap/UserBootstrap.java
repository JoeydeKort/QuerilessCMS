package be.intec.querilesscms.bootstrap;

import be.intec.querilesscms.models.Role;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UsersServiceImpl usersServiceImpl;

    public UserBootstrap(UsersServiceImpl userService) {
        this.usersServiceImpl = userService;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(usersServiceImpl.findAllUsers().isEmpty()) {

            User nikola = new User();
            nikola.setFirstName("Nikola");
            nikola.setLastName("Tesla");
            nikola.setUsername("niki");
            nikola.setEmail("tes@la.be");
            nikola.setPassCode("1234@Bcd");
            nikola.setCity("Tilburg");
            nikola.setAddress("2nd street 55");
            nikola.setZip("5021TP");


            Set<Role> roles = new HashSet<>();
            roles.add(Role.user());
            roles.add(Role.admin());
            roles.add(Role.editor());

            nikola.setRoles(roles);

            usersServiceImpl.saveUser(nikola);



        }


    }

}
