package be.intec.querilesscms.services.Implementations;


import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.Category;
import be.intec.querilesscms.repositories.BeerRepository;
import be.intec.querilesscms.repositories.BrewerRepository;
import be.intec.querilesscms.repositories.CategoryRepository;
import be.intec.querilesscms.services.interfaces.DatabaseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public List<Beer> findAllBeers() {
        return beerRepository.findAll(Sort.by("name").ascending());
    }

    @Override
    public List<Brewer> findAllBrewers() {
        return brewerRepository.findAll();
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}
