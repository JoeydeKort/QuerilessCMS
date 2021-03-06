package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.AdminController;
import be.intec.querilesscms.models.Role;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.DatabaseServiceImpl;
import be.intec.querilesscms.services.Implementations.EmailServiceImpl;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import be.intec.querilesscms.utils.LoggerDataPDFExporter;
import be.intec.querilesscms.utils.UserPDFExporter;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminControllerImpl implements AdminController {

    private final UsersServiceImpl usersServiceImpl;
    private final DatabaseServiceImpl databaseServiceImpl;
    private final EmailServiceImpl emailServiceImpl;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AdminControllerImpl(UsersServiceImpl usersServiceImpl, DatabaseServiceImpl databaseServiceImpl, EmailServiceImpl emailServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
        this.databaseServiceImpl = databaseServiceImpl;
        this.emailServiceImpl = emailServiceImpl;
    }

    @Override
    @GetMapping("/admin")
    public String showUsers(Model model) {

        List<User> users = usersServiceImpl.findAllUsers();
        model.addAttribute("users", users);

        log.info("Admin accessed administrator page");

        return "admin/admin";
    }

    @Override
    @RequestMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        usersServiceImpl.deleteById(id);

        log.info("Admin deleted user with user-ID: " + id);

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

            this.emailServiceImpl.sendMessage(
                    user.getEmail(),
                    "Welcome to Queriless!",
                    "Here is your account information: " + '\n' +
                            "Username: " + user.getUsername() + '\n' +
                            "Email address: " + user.getEmail() + '\n' +
                            "First name: " + user.getFirstName() + '\n' +
                            "Last name: " + user.getLastName() + '\n' +
                            "Address: " + user.getAddress() + " " + user.getCity() + " " + user.getZip() + '\n' +
                            "Thank you for joining Queriless! " + '\n' +
                            "Log in on Queriless here -> " + " http://localhost:7777/login");

            log.info("Admin added new user with user ID: " + user.getId() + " || Username: " + user);
            log.info("Admin added a new user and mail is send to: " + user);

        }

        return "admin/adduser";

    }

    @Override
    @GetMapping("/admin/export/pdf")
    public void exportUserToPDF(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<User> listOfUsers = usersServiceImpl.findAllUsers();
        UserPDFExporter exporter = new UserPDFExporter(listOfUsers);
        exporter.export(response);

        log.info("Admin created PDF File of the users");

    }

    @Override
    @GetMapping("/admin/export-logdata/pdf")
    public void exportLogDataToPDF(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=logdata_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<String> listOfLogData = databaseServiceImpl.getLogData();
        LoggerDataPDFExporter exporter = new LoggerDataPDFExporter(listOfLogData);
        exporter.export(response);

        log.info("Admin created PDF File of the log data");

    }

    @Override
    @GetMapping("/edit-user/{id}")
    public String showUpdateUserForm(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("user", usersServiceImpl.findUserById(id).get());

        return "admin/update-user";
    }

    @Override
    @RequestMapping("/update-user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {

        usersServiceImpl.saveUser(user);

        this.emailServiceImpl.sendMessage(
                user.getEmail(),
                "Contact information updated",
                "Here is your new account information: " + '\n' +
                        "Username: " + user.getUsername() + '\n' +
                        "Email address: " + user.getEmail() + '\n' +
                        "First name: " + user.getFirstName() + '\n' +
                        "Last name: " + user.getLastName() + '\n' +
                        "Address: " + user.getAddress() + " " + user.getCity() + " " + user.getZip() + '\n' +
                        "Log in on Queriless here -> " + " http://localhost:7777/login");

        log.info("Admin updated user: " + user);
        log.info("Admin updated user and mail is send to: " + user);

        return "redirect:/admin";

    }

}
