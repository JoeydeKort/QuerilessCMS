package be.intec.querilesscms.controllers.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface UserController {

    String profile(Model model, HttpSession session);

    String deleteProfile(@PathVariable Long id);

    String login(Principal principal);

}
