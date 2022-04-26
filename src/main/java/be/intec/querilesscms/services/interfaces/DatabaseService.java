package be.intec.querilesscms.services.interfaces;

import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.Category;

import java.util.List;

public interface DatabaseService {

    List<Beer> findAllBeers();
    List<Brewer> findAllBrewers();
    List<Category> findAllCategory();

}
