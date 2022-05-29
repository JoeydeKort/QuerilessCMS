package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.BeerMock;
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
class BeerRepositoryTest {

    @Autowired
    private BeerRepository beerRepository;

    @BeforeEach
    void setUp() {
        beerRepository.deleteAll();
    }

    @Test
    @DisplayName("Testing succes scenario finding Beer by name ")
    void itShouldSuccessfullyFindBeerByName() {

        Beer beer = new BeerMock();
        beerRepository.save(beer);

        Optional <Beer> expected = beerRepository.findByName("nameBeerMock");

        assertThat(expected).isPresent();

    }

    @Test
    @DisplayName("Testing fail scenario finding Beer by name ")
    void itShouldFailFindBeerByName() {

        Beer beer = new BeerMock();
        beerRepository.save(beer);

        Optional <Beer> expected = beerRepository.findByName("NonExistingBeer");

        assertThat(expected).isNotPresent();

    }

    @Test
    @DisplayName("Testing succes scenario Search method for Beer")
    void itShouldSuccessfullySearchBeerDB() {

        Beer beer01 = new Beer();
        beer01.setName("Chimay rouge");

        Beer beer02 = new Beer();
        beer02.setName("Chimay bleu");

        Beer beer03 = new Beer();
        beer03.setName("Straffe Hendrik");

        beerRepository.save(beer01);
        beerRepository.save(beer02);
        beerRepository.save(beer03);

        List<Beer> expectedResult = beerRepository.searchBeerDB("Chimay");

        assertEquals(2, expectedResult.size());

    }

    @Test
    @DisplayName("Testing fail scenario Search method for Beer")
    void itShouldFailSearchBeerDB() {

        Beer beer01 = new Beer();
        beer01.setName("Chimay rouge");

        Beer beer02 = new Beer();
        beer02.setName("Chimay bleu");

        Beer beer03 = new Beer();
        beer03.setName("Straffe Hendrik");

        beerRepository.save(beer01);
        beerRepository.save(beer02);
        beerRepository.save(beer03);

        List<Beer> expectedResult = beerRepository.searchBeerDB("BeerNotInTheList");

        assertNotEquals(2, expectedResult.size());


    }
}