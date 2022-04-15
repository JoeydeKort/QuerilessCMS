package be.intec.querilesscms.services.Implementations;

import be.intec.querilesscms.models.Role;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.repositories.UserRepository;
import be.intec.querilesscms.services.interfaces.UsersService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;

    public UsersServiceImpl(BCryptPasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Optional<User> findByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        user.setPassCode(encoder.encode(user.getPassCode()));

        if(user.getRoles() != null && user.getRoles().isEmpty()) {
            user.setRoles(Set.of(Role.user()));
        }

        userRepository.save(user);
    }

    @Override
    public void addRole(User user, Role role) {

        if(user.getRoles().isEmpty()) {
            user.setRoles(Set.of(Role.user()));
        } else {
            user.getRoles().add(role);
        }

    }

    @Override
    public void removeRole(User user, Role role) {

        user.getRoles().remove(role);

    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<Role> roles = new HashSet<Role>();

        if(user.getRoles() != null && !user.getRoles().isEmpty()) {

            roles.addAll(user.getRoles());

        } else {
            roles.add(Role.guest());

        }

        List<GrantedAuthority> gaList = new ArrayList<>();

        for(Role role : roles) {
            gaList.add(new GrantedAuthority() {

                @Override
                public String getAuthority() {
                    return "ROLE_" + role.getTitle();
                }

            });

        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassCode(), gaList);

    }

}
