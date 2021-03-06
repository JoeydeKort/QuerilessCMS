package be.intec.querilesscms.services.interfaces;

import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.Category;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface DatabaseService {

    List<Beer> findAllBeers();
    List<Brewer> findAllBrewers();
    List<Category> findAllCategory();

    Optional<Beer> findBeerById(Long id);
    Optional<Brewer> findBrewerById(Long id);
    Optional<Category> findCategoryById(Long id);

    List<Beer> getBeerByKeyword(String keyword);
    List<Brewer> getBrewerByKeyword(String keyword);
    List<Category> getCategoryByKeyword(String keyword);

    void deleteBeerById(Long id);
    void deleteBrewerById(Long id);
    void deleteCategoryById(Long id);

    void saveBeer(Beer beer);
    void saveBrewer(Brewer brewer);
    void saveCategory(Category category);

    Optional<Beer> findBeerByName(String name);
    Optional<Brewer> findBrewerByName(String name);
    Optional<Category> findCategoryByName(String title);

    List<String> getLogData() throws FileNotFoundException;
}
