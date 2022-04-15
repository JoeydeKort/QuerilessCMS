package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.SignUpController;
import be.intec.querilesscms.models.Role;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


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

    //FIXME
    @PostMapping("/signup")
    public String registerUserAccount(@Valid User user,
                                      BindingResult bindingResult,
                                      Model model) {

        // check if user exist
        // if exist error
        if (usersServiceImpl.findByEmail(user.getEmail()).isPresent()) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        // if not exist save user
        // success message
        model.addAttribute("user", new User());

        Set<Role> role = new HashSet<>();
        role.add(Role.user());
        user.setRoles(role);

        usersServiceImpl.saveUser(user);

        return "/signup";

    }

}
