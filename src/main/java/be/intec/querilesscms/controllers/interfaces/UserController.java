package be.intec.querilesscms.controllers.interfaces;

import be.intec.querilesscms.models.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

public interface UserController {

    String profile(Model model, HttpSession session);

    String deleteProfile(@PathVariable Long id);

    String login(Principal principal);

    String showRegistrationForm(Model model);

    String registerUserAccount(@Valid User user, BindingResult bindingResult, Model model);

}
