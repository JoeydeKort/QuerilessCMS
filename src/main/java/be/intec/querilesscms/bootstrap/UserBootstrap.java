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
            roles.add(Role.admin());

            nikola.setRoles(roles);

            usersServiceImpl.saveUser(nikola);

            User justin = new User();
            justin.setFirstName("Justin");
            justin.setLastName("Bieber");
            justin.setUsername("justi");
            justin.setEmail("just@in.be");
            justin.setPassCode("1234");
            justin.setCity("Brussels");
            justin.setAddress("Main street 22");
            justin.setZip("1000");

            Set<Role> roles1 = new HashSet<>();
            roles1.add(Role.user());

            justin.setRoles(roles1);

            usersServiceImpl.saveUser(justin);

            User marie = new User();
            marie.setFirstName("Marie");
            marie.setLastName("Curie");
            marie.setUsername("macu");
            marie.setEmail("mar@ie.be");
            marie.setPassCode("1111");
            marie.setCity("Brussels");
            marie.setAddress("Other street 2");
            marie.setZip("1240");

            Set<Role> roles2 = new HashSet<>();
            roles1.add(Role.editor());

            marie.setRoles(roles2);

            usersServiceImpl.saveUser(marie);

        }

    }

}
