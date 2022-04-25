package be.intec.querilesscms.services.Implementations;


import be.intec.querilesscms.repositories.BeerRepository;
import be.intec.querilesscms.repositories.BrewerRepository;
import be.intec.querilesscms.repositories.CategoryRepository;
import be.intec.querilesscms.services.interfaces.DatabaseService;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService  {

    private final BeerRepository beerRepository;
    private final BrewerRepository brewerRepository;
    private final CategoryRepository categoryRepository;


    public DatabaseServiceImpl(BeerRepository beerRepository, BrewerRepository brewerRepository, CategoryRepository categoryRepository) {
        this.beerRepository = beerRepository;
        this.brewerRepository = brewerRepository;
        this.categoryRepository = categoryRepository;
    }


}
