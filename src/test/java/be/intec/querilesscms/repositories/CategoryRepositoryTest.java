package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Category;
import be.intec.querilesscms.models.CategoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Testing succes scenario finding category by title ")
    void itShouldSuccessfullyFindCategoryByTitle() {

        Category category = new CategoryMock();

        categoryRepository.save(category);

        Optional<Category> expected = categoryRepository.findByTitle("mockTitle");

        assertThat(expected).isPresent();

    }

    @Test
    @DisplayName("Testing fail scenario finding category by title ")
    void itShouldFailFindCategoryByTitle() {

        Category category = new CategoryMock();

        categoryRepository.save(category);

        Optional<Category> expected = categoryRepository.findByTitle("NonExistingCategory");

        assertThat(expected).isNotPresent();

    }

    @Test
    @DisplayName("Testing succes scenario Search method for category")
    void itShouldSuccessfullySearchCategoryDB() {

        Category category01 = new Category();
        category01.setTitle("Trippel");
        category01.setSlug("Tri");

        Category category02 = new Category();
        category02.setTitle("Quadruple oak");
        category02.setSlug("Qua");

        Category category03 = new Category();
        category03.setTitle("Quadruple");
        category03.setSlug("Qua");

        categoryRepository.save(category01);
        categoryRepository.save(category02);
        categoryRepository.save(category03);

        List<Category> expectedResult = categoryRepository.searchCategoryDB("Qua");

        assertEquals(2, expectedResult.size());

    }

    @Test
    @DisplayName("Testing fail scenario Search method for category")
    void itShouldFailSearchCategoryDB() {

        Category category01 = new Category();
        category01.setTitle("Trippel");
        category01.setSlug("Tri");

        Category category02 = new Category();
        category02.setTitle("Quadruple oak");
        category02.setSlug("Qua");

        Category category03 = new Category();
        category03.setTitle("Quadruple");
        category03.setSlug("Qua");

        categoryRepository.save(category01);
        categoryRepository.save(category02);
        categoryRepository.save(category03);

        List<Category> expectedResult = categoryRepository.searchCategoryDB("Tri");

        assertNotEquals(2, expectedResult.size());

    }

}