package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.UserController;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class UserControllerImpl implements UserController {

    private final UsersServiceImpl usersServiceImpl;

    public UserControllerImpl(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {

        Object principal = SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = usersServiceImpl.findByUserName(username).get();

        if (usersServiceImpl.findByUserName(username).isPresent()) {

        session.setAttribute("user", user);
        model.addAttribute("user", user);
        }

        return "profile";
    }

    @RequestMapping ("delete/{id}")
    public String deleteProfile(@PathVariable Long id){

        usersServiceImpl.deleteById(id);

        return "redirect:/login";

    }

    @GetMapping("/login")
    public String login(Principal principal) {

        if (principal != null) {
            return "redirect:/login";
        }
        return "/login";
    }

    @GetMapping("/dbmenu")
    public String dbmenu() {

        return "dbmenu/dbmenu";
    }

}
