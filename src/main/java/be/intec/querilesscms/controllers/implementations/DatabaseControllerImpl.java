package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.DatabaseController;
import be.intec.querilesscms.services.interfaces.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatabaseControllerImpl implements DatabaseController  {

    private final DatabaseService databaseService;

    public DatabaseControllerImpl(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/dbmenu")
    public String showDbmenu() {

        return "dbmenu/dbmenu";

    }


}
