package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.UserController;
import be.intec.querilesscms.models.Role;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.EmailServiceImpl;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserControllerImpl implements UserController {

    private final UsersServiceImpl usersServiceImpl;
    private final EmailServiceImpl emailServiceImpl;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public UserControllerImpl(UsersServiceImpl usersServiceImpl, EmailServiceImpl emailServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
        this.emailServiceImpl = emailServiceImpl;
    }

    @Override
    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {

        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = usersServiceImpl.findByUserName(username).get();

        if (usersServiceImpl.findByUserName(username).isPresent()) {

            session.setAttribute("user", user);
            model.addAttribute("user", user);

            log.info("Accessed profile page with username: " + user + " || " + user.getRoles());
        }

        return "profile";
    }

    @Override
    @RequestMapping("delete/{id}")
    public String deleteProfile(@PathVariable Long id) {

        usersServiceImpl.deleteById(id);

        log.info("Deleted profile with user ID: " + id);

        return "redirect:/login";

    }

    @Override
    @GetMapping("/login")
    public String login(Principal principal) {

        return "/login";
    }

    @Override
    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }

    @Override
    @PostMapping("/signup")
    public String registerUserAccount(@Valid User user,
                                      BindingResult bindingResult,
                                      Model model) {

        if (usersServiceImpl.findByUserName(user.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "error.user", "There is already a user registered with this username.");
        }

        if (usersServiceImpl.findByEmail(user.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with this email.");
        }

        if (!bindingResult.hasErrors()) {

            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());

            Set<Role> role = new HashSet<>();
            role.add(Role.user());
            user.setRoles(role);

            usersServiceImpl.saveUser(user);

            this.emailServiceImpl.sendMessage(
                    user.getEmail(),
                    "Welcome to Queriless!",
                    "Here is your account information: " + '\n' +
                            "Username: " + user.getUsername() + '\n' +
                            "Email address: " + user.getEmail() + '\n' +
                            "Password: " + "For password information contact the administrator" + '\n' +
                            "First name: " + user.getFirstName() + '\n' +
                            "Last name: " + user.getLastName() + '\n' +
                            "Address: " + user.getAddress() + " " + user.getCity() + " " + user.getZip() + '\n' +
                            "Thank you for joining Queriless! ");

            log.info("New user signed up with user ID: " + user.getId() + " || Username: " + user);
        }

        return "/signup";

    }

}
