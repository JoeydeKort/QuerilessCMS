package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.User;
import be.intec.querilesscms.models.UserMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Testing succes scenario find by username for the users")
    void itShouldSuccessfullyFindUserByUsername() {

        User user = new UserMock();
        userRepository.save(user);

        Optional<User> expected = userRepository.findByUsername("JoJo");

        assertThat(expected).isPresent();

    }

    @Test
    @DisplayName("Testing fail scenario find by username for the users")
    void itShouldFailFindUserByUsername() {

        User user = new UserMock();
        userRepository.save(user);

        Optional<User> expected = userRepository.findByUsername("NonExistingUsername");

        assertThat(expected).isNotPresent();

    }

    @Test
    @DisplayName("Testing succes scenario find by email for the users")
    void itShouldSuccessfullyFindUserByEmail() {

        User user = new UserMock();
        userRepository.save(user);

        Optional<User> expected = userRepository.findByEmail("joe@mail.be");

        assertThat(expected).isPresent();

    }

    @Test
    @DisplayName("Testing fail scenario find by email for the users")
    void itShouldFailFindUserByEmail() {

        User user = new UserMock();
        userRepository.save(user);

        Optional<User> expected = userRepository.findByEmail("NonExisting@mail.be");

        assertThat(expected).isNotPresent();

    }

}