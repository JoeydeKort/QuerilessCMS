package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void itShouldSuccessfullyFindByTitle() {

        Category category = new Category();
        category.setTitle("dubbel");

        categoryRepository.save(category);

        Optional<Category> expected = categoryRepository.findByTitle("dubbel");

        assertThat(expected).isPresent();

    }

    @Test
    void itShouldFailFindByTitle() {

        Category category = new Category();
        category.setTitle("dubbel");

        categoryRepository.save(category);

        Optional<Category> expected = categoryRepository.findByTitle("NonExistingCategory");

        assertThat(expected).isNotPresent();


    }

    @Test
    @Disabled
    void itShouldSuccessfullySearchCategoryDB() {

    }

    @Test
    @Disabled
    void itShouldFailSearchCategoryDB() {



    }

}