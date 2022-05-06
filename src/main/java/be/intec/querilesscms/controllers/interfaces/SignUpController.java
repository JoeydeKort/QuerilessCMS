package be.intec.querilesscms.controllers.interfaces;

import be.intec.querilesscms.models.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface SignUpController {

    String showRegistrationForm(Model model);

    String registerUserAccount(@Valid User user, BindingResult bindingResult, Model model);
}
