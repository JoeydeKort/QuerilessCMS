package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void itShouldSuccessfullyFindUserByUsername() {

        User user = new User();
        user.setUsername("joe");
        user.setEmail("joe@mail.be");
        userRepository.save(user);

        Optional<User> expected = userRepository.findByUsername("joe");

        assertThat(expected).isPresent();

    }

    @Test
    void itShouldFailFindUserByUsername() {

        User user = new User();
        user.setUsername("joe");
        user.setEmail("joe@mail.be");
        userRepository.save(user);

        Optional<User> expected = userRepository.findByUsername("NonExistingUsername");

        assertThat(expected).isNotPresent();

    }

    @Test
    void itShouldSuccessfullyFindUserByEmail() {

        User user = new User();
        user.setUsername("joe");
        user.setEmail("joe@mail.be");
        userRepository.save(user);

        Optional<User> expected = userRepository.findByEmail("joe@mail.be");

        assertThat(expected).isPresent();


    }

    @Test
    void itShouldFailFindUserByEmail() {

        User user = new User();
        user.setUsername("joe");
        user.setEmail("joe@mail.be");
        userRepository.save(user);

        Optional<User> expected = userRepository.findByEmail("NonExisting@mail.be");

        assertThat(expected).isNotPresent();

    }

}