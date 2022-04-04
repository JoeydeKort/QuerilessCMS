package be.intec.querilesscms.repositories;

import be.intec.querilesscms.models.Brewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrewerRepository extends JpaRepository<Brewer, Long> {
}
