package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.AdminController;
import be.intec.querilesscms.models.Role;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import be.intec.querilesscms.utils.UserPDFExporter;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminControllerImpl implements AdminController {

    private final UsersServiceImpl usersServiceImpl;

    public AdminControllerImpl(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @Override
    @GetMapping("/admin")
    public String showUsers(Model model) {

        List<User> users = usersServiceImpl.findAllUsers();
        model.addAttribute("users", users);

        return "admin/admin";
    }

    @Override
    @RequestMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        usersServiceImpl.deleteById(id);

        return "redirect:/admin";

    }

    @Override
    @GetMapping("/adduser")
    public String showAddUserForm(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "admin/adduser";
    }

    @Override
    @PostMapping("/adduser")
    public String addNewUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (usersServiceImpl.findByUserName(user.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "error.user", "There is already a user registered with this username.");
        }

        if (usersServiceImpl.findByEmail(user.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with this email.");
        }

        if (!bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "User has been successfully added.");
            model.addAttribute("user", new User());

            Set<Role> role = new HashSet<>();
            role.add(Role.user());
            user.setRoles(role);

            usersServiceImpl.saveUser(user);
        }

        return "admin/adduser";

    }

    @GetMapping("/admin/export/pdf")
    public void exportUserToPDF(HttpServletResponse response)  throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<User> listOfUsers = usersServiceImpl.findAllUsers();
        UserPDFExporter exporter = new UserPDFExporter(listOfUsers);
        exporter.export(response);

    }


    @Override
    @GetMapping("/edit-user/{id}")
    public String showUpdateUserForm(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("user", usersServiceImpl.findUserById(id).get());

        return "admin/update-user";
    }



    @RequestMapping ("/update-user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {

        usersServiceImpl.saveUser(user);

        return "redirect:/admin";

    }

}
