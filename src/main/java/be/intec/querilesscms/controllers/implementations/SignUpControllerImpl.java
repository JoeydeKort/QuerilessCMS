package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.SignUpController;
import be.intec.querilesscms.models.Role;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Validated
@Controller
public class SignUpControllerImpl implements SignUpController {

    private final UsersServiceImpl usersServiceImpl;

    public SignUpControllerImpl(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;

    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }

    @PostMapping("/signup")
    public String registerUserAccount(@Valid User user,
                                      BindingResult bindingResult,
                                      Model model) {

        if (usersServiceImpl.findByUserName(user.getUsername()).isPresent()) {
            bindingResult.rejectValue("username","error.user","There is already a user registered with this username.");
        }

        if (usersServiceImpl.findByEmail(user.getEmail()).isPresent()) {
            bindingResult.rejectValue("email","error.user","There is already a user registered with this email.");
        }

        if(!bindingResult.hasErrors()) {

            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());

            Set<Role> role = new HashSet<>();
            role.add(Role.user());
            user.setRoles(role);

            usersServiceImpl.saveUser(user);
        }

        return "/signup";

    }

}
