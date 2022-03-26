package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Brewers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrewersRepository extends JpaRepository<Brewers, Long> {
}
