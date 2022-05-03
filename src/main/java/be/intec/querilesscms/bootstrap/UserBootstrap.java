package be.intec.querilesscms.bootstrap;

import be.intec.querilesscms.models.*;
import be.intec.querilesscms.services.Implementations.DatabaseServiceImpl;
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
    private final DatabaseServiceImpl databaseServiceImpl;

    public UserBootstrap(UsersServiceImpl usersServiceImpl, DatabaseServiceImpl databaseServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
        this.databaseServiceImpl = databaseServiceImpl;
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
            roles2.add(Role.editor());

            marie.setRoles(roles2);

            usersServiceImpl.saveUser(marie);

        }

        if(databaseServiceImpl.findAllBeers().isEmpty()) {

            Beer beer01 = new Beer();
            beer01.setName("Chimay trippel");
            beer01.setAlcohol(9.0);
            beer01.setPrice(1.85);
            beer01.setStock(25L);
            beer01.setBrewer_id(1L);
            beer01.setCategory_id(1L);

            Beer beer02 = new Beer();
            beer02.setName("Straffe Hendrik 11");
            beer02.setAlcohol(11.0);
            beer02.setPrice(2.20);
            beer02.setStock(55L);
            beer02.setBrewer_id(2L);
            beer02.setCategory_id(2L);

            Beer beer03 = new Beer();
            beer03.setName("La Trappe dubbel");
            beer03.setAlcohol(7.0);
            beer03.setPrice(1.95);
            beer03.setStock(135L);
            beer03.setBrewer_id(3L);
            beer03.setCategory_id(3L);

            databaseServiceImpl.saveBeer(beer01);
            databaseServiceImpl.saveBeer(beer02);
            databaseServiceImpl.saveBeer(beer03);

        }

        if(databaseServiceImpl.findAllBrewers().isEmpty()) {

            Brewer brewer01 = new Brewer();
            brewer01.setName("Chimay");
            brewer01.setAddress("Route du Rond Point, 294");
            brewer01.setCity("Forges");
            brewer01.setZip("6464");
            brewer01.setTurnover(1_000_000L);

            brewer01.setBeers(databaseServiceImpl.findBeerById(1L).get());
            databaseServiceImpl.saveBrewer(brewer01);

            Brewer brewer02 = new Brewer();
            brewer02.setName("De Halve Maan");
            brewer02.setAddress("Walplein 26");
            brewer02.setCity("Brugge");
            brewer02.setZip("8000");
            brewer02.setTurnover(1_250_000L);

            brewer02.setBeers(databaseServiceImpl.findBeerById(2L).get());
            databaseServiceImpl.saveBrewer(brewer02);

            Brewer brewer03 = new Brewer();
            brewer03.setName("Abdij Onze Lieve Vrouw van Koningshoeven");
            brewer03.setAddress("Eindhovenseweg 3");
            brewer03.setCity("Berkel-Enschot");
            brewer03.setZip("5056 RP");
            brewer03.setTurnover(1_850_000L);

            brewer03.setBeers(databaseServiceImpl.findBeerById(3L).get());
            databaseServiceImpl.saveBrewer(brewer03);

        }

        if(databaseServiceImpl.findAllCategory().isEmpty()) {

            Category category01 = new Category();
            category01.setTitle("Trippel");
            category01.setSlug("tri");

            category01.setBeer(databaseServiceImpl.findBeerById(1L).get());
            databaseServiceImpl.saveCategory(category01);

            Category category02 = new Category();
            category02.setTitle("Quadrupel");
            category02.setSlug("Qua");

            category02.setBeer(databaseServiceImpl.findBeerById(2L).get());
            databaseServiceImpl.saveCategory(category02);

            Category category03 = new Category();
            category03.setTitle("Dubbel");
            category03.setSlug("dub");

            category03.setBeer(databaseServiceImpl.findBeerById(3L).get());
            databaseServiceImpl.saveCategory(category03);

        }

    }

}
