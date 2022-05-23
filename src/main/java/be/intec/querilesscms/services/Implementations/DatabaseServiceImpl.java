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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
    public Optional<Beer> findBeerById(Long id) {
        return beerRepository.findById(id);
    }

    @Override
    public Optional<Brewer> findBrewerById(Long id) {
        return brewerRepository.findById(id);
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }


    @Override
    public List<Beer> getBeerByKeyword(String keyword) {
        return beerRepository.searchBeerDB(keyword);
    }

    @Override
    public List<Brewer> getBrewerByKeyword(String keyword) {
        return brewerRepository.searchBrewerDB(keyword);
    }

    @Override
    public List<Category> getCategoryByKeyword(String keyword) {
        return categoryRepository.searchCategoryDB(keyword);
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
    public void saveBrewer(Brewer brewer) { brewerRepository.save(brewer); }

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

    @Override
    public List<String> getLogData() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/main/resources/application-log.log"));
        ArrayList<String> resultLoggerDataList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            resultLoggerDataList.add(scanner.nextLine());
        }

        return resultLoggerDataList;
    }

}
