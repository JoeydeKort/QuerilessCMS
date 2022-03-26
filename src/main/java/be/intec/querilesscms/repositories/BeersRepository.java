package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Beers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeersRepository extends JpaRepository<Beers, Long> {
}
