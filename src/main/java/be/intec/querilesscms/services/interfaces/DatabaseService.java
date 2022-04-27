package be.intec.querilesscms.services.interfaces;

import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.Category;

import java.util.List;
import java.util.Optional;

public interface DatabaseService {

    List<Beer> findAllBeers();
    List<Brewer> findAllBrewers();
    List<Category> findAllCategory();
    void deleteBeerById(Long id);
    void deleteBrewerById(Long id);
    void deleteCategoryById(Long id);
    void saveBeer(Beer beer);
    void saveBrewer(Brewer brewer);
    void saveCategory(Category category);
    Optional<Beer> findBeerByName(String name);
    Optional<Brewer> findBrewerByName(String name);
    Optional<Category> findCategoryByName(String title);
}
