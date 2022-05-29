package be.intec.querilesscms.services.Implementations;

import be.intec.querilesscms.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UsersServiceImpl usersServiceImpl;

    @BeforeEach
    void setUp() {
        usersServiceImpl = new UsersServiceImpl(bCryptPasswordEncoder,userRepository);
    }

    @Test
    @DisplayName(" This test should pass when it finds all of the users")
    void canFindAllUsers() {

        usersServiceImpl.findAllUsers();

        verify(userRepository).findAll();

    }

    @Test
    @Disabled
    void canFindByUserName() {
    }

    @Test
    @Disabled
    void findByEmail() {
    }

    @Test
    @Disabled
    void findUserById() {
    }

    @Test
    @Disabled
    void deleteById() {
    }


    @Test
    @Disabled
    void saveUser() {

    }

    @Test
    @Disabled
    void addRole() {
    }

    @Test
    @Disabled
    void removeRole() {
    }

    @Test
    @Disabled
    void loadUserByUsername() {
    }

}