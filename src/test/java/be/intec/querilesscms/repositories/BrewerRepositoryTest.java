package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.BrewerMock;
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
class BrewerRepositoryTest {

    @Autowired
    private BrewerRepository brewerRepository;

    @BeforeEach
    void setUp() {
        brewerRepository.deleteAll();
    }

    @Test
    @DisplayName("Testing succes scenario finding Brewer by name ")
    void itShouldSuccessfullyFindBrewerByName() {

        Brewer brewer = new BrewerMock();
        brewerRepository.save(brewer);

        Optional <Brewer> expected = brewerRepository.findByName("nameBrewerMock");

        assertThat(expected).isPresent();

    }

    @Test
    @DisplayName("Testing fail scenario finding Brewer by name ")
    void itShouldFailFindBrewerByName() {

        Brewer brewer = new BrewerMock();
        brewerRepository.save(brewer);

        Optional <Brewer> expected = brewerRepository.findByName("NonExistingBrewer");

        assertThat(expected).isNotPresent();

    }

    @Test
    @DisplayName("Testing succes scenario search method Brewer")
    void itShouldSuccessfullySearchBrewerDB() {

        Brewer brewer01 = new Brewer();
        brewer01.setName("Halve maan");
        brewer01.setTurnover(10000L);

        Brewer brewer02 = new Brewer();
        brewer02.setName("Chimay");
        brewer02.setTurnover(10000L);

        Brewer brewer03 = new Brewer();
        brewer03.setName("La Trappe");
        brewer03.setTurnover(50000L);

        brewerRepository.save(brewer01);
        brewerRepository.save(brewer02);
        brewerRepository.save(brewer03);

        List<Brewer> expectedResult =  brewerRepository.searchBrewerDB("10000");

        assertEquals(2, expectedResult.size());

    }

    @Test
    @DisplayName("Testing fail scenario search method Brewer")
    void itShouldFailSearchBrewerDB() {

        Brewer brewer01 = new Brewer();
        brewer01.setName("Halve maan");
        brewer01.setTurnover(10000L);

        Brewer brewer02 = new Brewer();
        brewer02.setName("Chimay");
        brewer02.setTurnover(10000L);

        Brewer brewer03 = new Brewer();
        brewer03.setName("La Trappe");
        brewer03.setTurnover(50000L);

        brewerRepository.save(brewer01);
        brewerRepository.save(brewer02);
        brewerRepository.save(brewer03);

        List<Brewer> expectedResult =  brewerRepository.searchBrewerDB("TurnoverNotInTheList");

        assertNotEquals(2, expectedResult.size());
    }

}