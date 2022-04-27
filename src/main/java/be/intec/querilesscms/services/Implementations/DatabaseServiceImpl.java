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
import java.util.Optional;

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

    @Override
    public void deleteBeerById(Long id) {
        beerRepository.deleteById(id);
    }

    @Override
    public void deleteBrewerById(Long id) {
        brewerRepository.deleteById(id);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void saveBeer(Beer beer) {
        beerRepository.save(beer);
    }

    @Override
    public void saveBrewer(Brewer brewer) {
        brewerRepository.save(brewer);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Beer> findBeerByName(String name) {
        return beerRepository.findByName(name);
    }

    @Override
    public Optional<Brewer> findBrewerByName(String name) {
        return brewerRepository.findByName(name);
    }

    @Override
    public Optional<Category> findCategoryByName(String title) {
        return categoryRepository.findByTitle(title);
    }

}
